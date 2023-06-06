package com.example.yourstar.service.impl;

import com.example.yourstar.data.dao.UserProfileDao;
import com.example.yourstar.data.dto.profile.GetUserProfileDto;
import com.example.yourstar.data.dto.profile.IdImageDto;
import com.example.yourstar.data.dto.profile.UpdateUserProfileDto;
import com.example.yourstar.data.entity.PostEntity;
import com.example.yourstar.data.entity.UserProfileEntity;
import com.example.yourstar.data.repository.FollowRepository;
import com.example.yourstar.data.repository.PostRepository;
import com.example.yourstar.data.repository.UserProfileRepository;
import com.example.yourstar.service.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class UserProfileServiceImpl implements UserProfileService {
    private UserProfileDao userProfileDao;
    private UserProfileRepository userProfileRepository;
    private FollowRepository followRepository;
    private PostRepository postRepository;
    @Autowired
    public UserProfileServiceImpl(UserProfileDao userProfileDao,UserProfileRepository userProfileRepository,PostRepository postRepository,FollowRepository followRepository){
        this.userProfileDao = userProfileDao;
        this.userProfileRepository = userProfileRepository;
        this.postRepository = postRepository;
        this.followRepository = followRepository;
    }
    @Override
    public String updateUserProfile(String userId, UpdateUserProfileDto updateUserProfileDto) {
        try{
            userProfileDao.updateUserProfile(userId, updateUserProfileDto);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }

    @Override
    public GetUserProfileDto getUserProfile(String userId,int page) {
        log.info("프로필 주인 : {}",userId);
        GetUserProfileDto userProfileDto = new GetUserProfileDto();
        UserProfileEntity userProfileEntity = userProfileRepository.getById(userId);
        log.info("[getFollowingCount] : {}",userProfileDao.getFollowingCounts(userId));
        log.info("[getFollowersCount] : {}",userProfileDao.getFollowerCounts(userId));

        userProfileDto.setProfileImage(Base64.getEncoder().encodeToString(userProfileEntity.getUserProfile()));
        userProfileDto.setUserName(userProfileEntity.getUserEntity().getUserName());
        userProfileDto.setIntroduce(userProfileEntity.getIntroduce());
        userProfileDto.setPostCount(postRepository.countByUserId(userId));
        userProfileDto.setFollowingCount(userProfileDao.getFollowingCounts(userId));
        userProfileDto.setFollowerCount(userProfileDao.getFollowerCounts(userId));
        Pageable pageable = PageRequest.of(page, 9, Sort.by("postTime").descending());
        Page<PostEntity> postPage = postRepository.findByUserIdOrderByPostTimeDesc(userId, pageable);
        List<PostEntity> postList = postPage.getContent();

        List<IdImageDto> idImageDTOList = postList.stream()
                .map(postEntity -> {
                    IdImageDto idImageDto = new IdImageDto();
                    // 필드들을 PostEntity에서 가져와서 IdImageDto로 매핑해줍니다.
                    idImageDto.setId(postEntity.getPostId());
                    try {
                        idImageDto.setImage(Base64.getEncoder().encodeToString(postEntity.getMeta().getBytes(1, (int) postEntity.getMeta().length())));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return idImageDto;
                })
                .collect(Collectors.toList());

        userProfileDto.setPost(idImageDTOList);
        return userProfileDto; // 수정 해야함
    }
}
