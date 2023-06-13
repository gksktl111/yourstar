package com.example.yourstar.data.dao;

import com.example.yourstar.data.entity.CommentEntity;
import com.example.yourstar.data.entity.PostEntity;
import com.example.yourstar.data.entity.UserEntity;

public interface CommentDao {
    CommentEntity writeComment(CommentEntity commentEntity); //게시글 작성된 것을 DB에 저장
    CommentEntity updateComment(PostEntity post, UserEntity user, CommentEntity comment, String texts); //엔티티에 해당하는 유저 게시글 수정
    CommentEntity deleteComment(long commentId); //해당하는 아이디의 포스트 삭제
}
