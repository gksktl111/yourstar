package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.ChatRoomEntity;
import com.example.yourstar.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity,Long> {
    @Query("SELECT c FROM ChatRoomEntity c WHERE (c.user1 = :user OR c.user2 = :user) GROUP BY c.chatRoomId ORDER BY c.makeRoomTime DESC")
    List<ChatRoomEntity> findDistinctChatRoomsByUser(UserEntity user);
}
