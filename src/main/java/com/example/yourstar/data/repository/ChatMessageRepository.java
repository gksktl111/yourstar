package com.example.yourstar.data.repository;

import com.example.yourstar.data.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatMessageRepository  extends JpaRepository<ChatMessageEntity,Long> {
    // sender와 receiver가 주어진 경우, 두 사용자 간의 채팅 내용(역순도 포함)을 시간순 정렬하여 가져옵니다.
    @Query("SELECT m FROM ChatMessageEntity m WHERE (m.sender = :sender AND m.receiver = :receiver) OR (m.sender = :receiver AND m.receiver = :sender) ORDER BY m.sentAt ASC")
    List<ChatMessageEntity> findBySenderAndReceiver(String sender, String receiver);
}
