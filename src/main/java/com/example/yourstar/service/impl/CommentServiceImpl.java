package com.example.yourstar.service.impl;

import com.example.yourstar.data.dao.CommentDao;
import com.example.yourstar.data.dto.comment.CommentUpdateDto;
import com.example.yourstar.data.dto.comment.CommentWriteFormDto;
import com.example.yourstar.data.entity.CommentEntity;
import com.example.yourstar.data.entity.CommentLikeEntity;
import com.example.yourstar.data.entity.UserEntity;
import com.example.yourstar.data.repository.CommentLikeRepository;
import com.example.yourstar.data.repository.CommentRepository;
import com.example.yourstar.data.repository.UserRepository;
import com.example.yourstar.service.CommentService;
import com.example.yourstar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private CommentDao commentDao;

    private CommentEntity commentEntity;

    private CommentLikeEntity commentLikeEntity;

    private UserRepository userRepository;

    private CommentLikeRepository commentLikeRepository;

    private UserService userService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommentDao commentDao, UserService userService) {
        this.commentRepository = commentRepository;
        this.commentDao = commentDao;
        this.userService = userService;
    }


    @Override
    @Transactional
    public String writeComment(CommentWriteFormDto commentWriteFormDto) {
        CommentEntity commentEntity = new CommentEntity();
        System.out.println(commentRepository.count());
        commentEntity.setCommentsId(commentRepository.count());
        System.out.println("서비스 setID찍기전 로그" + commentWriteFormDto.getUserId());
        commentEntity.setUserId(CommentWriteFormDto.getUserId());
        System.out.println("서비스 setID찍은후 로그" + commentEntity.getUserId());
        commentEntity.setCommentsTime(new Timestamp(System.currentTimeMillis()));
        commentEntity.setText(commentWriteFormDto.getText());
        commentEntity.setCommentsLikeCount(0);

        try {
            System.out.println("저장 시도");
            commentRepository.save(commentEntity);
            return "success";
        } catch (Exception e) {
            System.out.println("저장 실패");
            e.printStackTrace();
            return "failed";
        }
    }

    @Override
    public String updateComment(CommentUpdateDto commentUpdateDto) {
        CommentEntity commentEntity = commentRepository.getById(commentUpdateDto.getCommentsId());

        if (commentEntity == null) {
            return "failed";
        }

        commentEntity.setText(commentUpdateDto.getText());
        try {
            commentRepository.save(commentEntity); //postEntity 정보를 업데이트하고 저장
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

    @Override
    public String deleteComment(long commentsId) {
        try {
            commentRepository.deleteById(commentsId); //형변환 실패 방지를 위해 string 객체로 래핑
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }

    @Override
    public CommentEntity ToggleLikeComment(String userId, Long commentId) {
        // 0. username으로 UserEntity 찾기
        UserEntity user = userRepository.findByUserId(userId);

        // 주어진 사용자가 없는 경우 처리 - 필요한 경우 처리 (예: 예외 발생)
        if (user == null) {
            // 사용자를 찾을 수 없는 경우 처리
        }

        // 1. 주어진 commentId와 같은 CommentEntity 객체 찾기
        CommentEntity comment = commentRepository.findById(commentId).orElse(null);

        // 댓글이 있는지 확인 - 필요한 경우 처리 (예: 예외 발생)
        if (comment == null) {
            // 댓글을 찾을 수 없는 경우 처리
        }

        // 2. 주어진 사용자와 댓글의 기존 CommentLikeEntity 찾기
        CommentLikeEntity commentLike = null;

        if (comment != null) {
            commentLike = commentLikeRepository.findByUserAndComment(String.valueOf(user), comment.getPost());
        } else {
            System.out.println("해당 댓글을 찾을 수 없음");
        }

        // 3. commentLike가 존재하는지 확인; 존재하면 제거 (좋아요 취소); 없으면 새로 만들기 (좋아요)
        if (commentLike != null) {
            commentLikeRepository.delete(commentLike);
            comment.setCommentsLikeCount(comment.getCommentsLikeCount() - 1);
        } else {
            commentLike = new CommentLikeEntity();
            commentLike.setUser(user);
            assert comment != null;
            commentLike.setComment(comment.getPost());
            commentLikeRepository.save(commentLike);
            comment.setCommentsLikeCount(comment.getCommentsLikeCount() + 1);
        }
        return comment;
    }
}
