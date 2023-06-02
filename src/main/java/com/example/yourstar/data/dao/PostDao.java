package com.example.yourstar.data.dao;

import com.example.yourstar.data.entity.PostEntity;
import com.example.yourstar.data.entity.UserEntity;

public interface PostDao {

    PostEntity writePost(PostEntity postEntity); //게시글 작성된 것을 DB에 저장
    PostEntity updatePost(PostEntity post, UserEntity user, String contents); //엔티티에 해당하는 유저 게시글 수정
    PostEntity deletePost(long postId); //해당하는 아이디의 포스트 삭제
    PostEntity likePost(PostEntity post, long likeCount); //해당하는 포스트의 좋아요 수 증가
    PostEntity unlikePost(PostEntity post, long likeCount); //해당하는 포스트의 좋아요 수 감소
    PostEntity viewPost(PostEntity post, long viewCount);
    PostEntity sharePost(PostEntity post, long shareCount);
    PostEntity unsharePost(PostEntity post, long shareCount);
}