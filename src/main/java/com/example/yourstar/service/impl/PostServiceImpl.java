package com.example.yourstar.service.impl;

import com.example.yourstar.data.dao.PostDao;
import com.example.yourstar.data.dto.GetFeedViewDto;
import com.example.yourstar.data.dto.post.PostUpdateDto;
import com.example.yourstar.data.dto.post.PostWriteFormDto;
import com.example.yourstar.data.entity.PostEntity;
import com.example.yourstar.data.repository.PostRepository;
import com.example.yourstar.data.repository.UserRepository;
import com.example.yourstar.service.PostService;
import com.example.yourstar.service.exception.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    private PostDao postDao;

    private PostEntity postEntity;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, PostDao postDao,UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postDao = postDao;
        this.userRepository =userRepository;
    }
    @Override
    @Transactional
    public String writePost(PostWriteFormDto postWriteFormDto) {
        PostEntity postEntity = new PostEntity();
        System.out.println(postRepository.count());
        postEntity.setPostId(postRepository.count());
        System.out.println("서비스 setID찍기전 로그"+postWriteFormDto.getUserId());
        postEntity.setUserId(postWriteFormDto.getUserId());
        System.out.println("서비스 setID찍은후 로그"+postEntity.getUserId());
        postEntity.setPostTime(new Timestamp(System.currentTimeMillis()));
        postEntity.setContents(postWriteFormDto.getContents());
        postEntity.setLikeCount(0);
        postEntity.setViewCount(0);
        postEntity.setShareCount(0);
        postEntity.setCategory("default");

        System.out.println("서비스파트 들어옴");

        if (postWriteFormDto.getImageFile() != null && !postWriteFormDto.getImageFile().isEmpty()) {
            // 이미지 파일이 있을 경우, meta 필드에 이미지 데이터를 저장
            try {
                byte[] imageBytes = postWriteFormDto.getImageFile().getBytes();
                Blob imageBlob = new SerialBlob(imageBytes);
                postEntity.setMeta(imageBlob);
                System.out.println("SETMETA 성공");
            } catch (IOException e) {
                System.out.println("IOE 에러");
                // 예외 처리를 적절하게 수행합니다.
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("SQL 에러");
                // 예외 처리를 적절하게 수행합니다.
                e.printStackTrace();
            }
        }
        if (postWriteFormDto.getVideoFile() != null && !postWriteFormDto.getVideoFile().isEmpty()) {
            // 동영상 파일이 있을 경우, meta 필드에 동영상 데이터를 저장
            try {
                byte[] videoBytes = postWriteFormDto.getVideoFile().getBytes();
                Blob videoBlob = new SerialBlob(videoBytes);
                postEntity.setMeta(videoBlob);
            } catch (IOException e) {
                // 예외 처리를 적절하게 수행합니다.
                e.printStackTrace();
            } catch (SQLException e) {
                // 예외 처리를 적절하게 수행합니다.
                e.printStackTrace();
            }
        }

        try {
            System.out.println("저장 시도");
            postRepository.save(postEntity);
            return "success";
        } catch (Exception e) {
            System.out.println("저장 실패");
            e.printStackTrace();
            return "failed";
        }
    }
    @Override
    public String updatePost(PostUpdateDto postUpdateDto) {
        PostEntity postEntity = postRepository.getById(postUpdateDto.getPostId());

        if (postEntity == null) {
            return "failed";
        }

        postEntity.setContents(postUpdateDto.getContents());
        postEntity.setMeta(postUpdateDto.getMeta());
        try {
            postRepository.save(postEntity); //postEntity 정보를 업데이트하고 저장
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

    @Override //데이터베이스에서 글 삭제
    public String deletePost(long postId) {
        try {
            postRepository.deleteById(postId); //형변환 실패 방지를 위해 string 객체로 래핑
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

    @Override
    public PostEntity likePost(long postId) {
        //postId로 데이터베이스 서치
        PostEntity postEntity = postRepository.getById(postId);

        //조회한 게시물이 없을 시 오류
        if (postEntity == null) {
            throw new PostNotFoundException("게시물을 찾을 수 없습니다.");
        }

        //likeCount 1 증가
        postEntity.setLikeCount(postEntity.getLikeCount() + 1);

        //수정 정보 저장
        return postRepository.save(postEntity);
    }

    @Override
    public PostEntity unlikePost(long postId) {
        //postId로 데이터베이스 서치
        PostEntity postEntity = postRepository.getById(postId);

        //조회한 게시물이 없을 시 오류
        if (postEntity == null) {
            throw new PostNotFoundException("게시물을 찾을 수 없습니다.");
        }

        //likeCount 1 감소
        postEntity.setLikeCount(postEntity.getLikeCount() - 1);

        //수정 정보 저장
        return postRepository.save(postEntity);
    }

    @Override
    public PostEntity viewPost(long postId) {
        //postId로 데이터베이스 서치
        PostEntity postEntity = postRepository.getById(postId);

        //조회한 게시물이 없을 시 오류
        if (postEntity == null) {
            throw new PostNotFoundException("게시물을 찾을 수 없습니다.");
        }

        //viewCount 1 증가
        postEntity.setViewCount(postEntity.getViewCount() + 1);

        //수정 정보 저장
        return postRepository.save(postEntity);
    }

    @Override
    public PostEntity sharePost(long postId) {
        //postId로 데이터베이스 서치
        PostEntity postEntity = postRepository.getById(postId);

        //조회한 게시물이 없을 시 오류
        if (postEntity == null) {
            throw new PostNotFoundException("게시물을 찾을 수 없습니다.");
        }

        //shareCount 1 증가
        postEntity.setShareCount(postEntity.getShareCount() + 1);

        //수정 정보 저장
        return postRepository.save(postEntity);
    }

    @Override
    public PostEntity unsharePost(long postId) {
        // postId 에 해당하는 게시물을 데이터베이스에서 조회합니다.
        PostEntity postEntity = postRepository.getById(postId);

        //조회한 게시물이 없을 시 오류
        if (postEntity == null) {
            throw new PostNotFoundException("게시물을 찾을 수 없습니다.");
        }

        //shareCount 1 감소
        postEntity.setShareCount(postEntity.getShareCount() - 1);

        //수정 정보 저장
        return postRepository.save(postEntity);
    }

    @Override
    public List<GetFeedViewDto> getFeedView(String userId, int page) {
        PageRequest pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "postTime"));
        Page<PostEntity> postList = postRepository.findByUserNot(userRepository.getById(userId), pageable);

        List<PostEntity> postEntityList = postList.getContent();

        List<GetFeedViewDto> getFeedViewDtoList = postEntityList.stream()
                .map(postEntity -> {
                    GetFeedViewDto getFeedViewDto = new GetFeedViewDto();
                    getFeedViewDto.setUserId(postEntity.getUserId());
                    getFeedViewDto.setUserName(postEntity.getUser().getUserName());
                    if(postEntity.getUser().getUserProfileEntity().getUserProfile() != null){
                        getFeedViewDto.setUserProFileImg(Base64.getEncoder().encodeToString(postEntity.getUser().getUserProfileEntity().getUserProfile()));
                    }else {
                        getFeedViewDto.setUserProFileImg(null);
                    }
                    getFeedViewDto.setPostId(postEntity.getPostId());
                    getFeedViewDto.setContents(postEntity.getContents());
                    getFeedViewDto.setLikeCount(postEntity.getLikeCount());
                    getFeedViewDto.setPostTime((postEntity.getPostTime()));
                    try {
                        getFeedViewDto.setMeta(Base64.getEncoder().encodeToString(postEntity.getMeta().getBytes(1,(int)postEntity.getMeta().length())));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return getFeedViewDto;
                })
                .collect(Collectors.toList());
        return getFeedViewDtoList;
    }

}