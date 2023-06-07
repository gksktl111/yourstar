package com.example.yourstar.service;

import com.example.yourstar.data.dto.ChatMessageDto;
import com.example.yourstar.data.dto.IdNameImageDto;

import java.util.List;

public interface ChatService {
    void saveChatMessage(ChatMessageDto message);  // 채팅 메시지를 저장하는 기능

    // 두 사용자(sender, receiver) 사이의 대화 내역(역순 포함)을 시간순으로 가져옵니다.
    List<ChatMessageDto> getMessagesBetweenUsers(String otherPerson, String receiver);

    List<IdNameImageDto> getChatRoom(String userId); // 채팅목록 가져오기

    void makeChatRoom(String user1, String user2); // 채팅방 만들기

    List<IdNameImageDto> getFollowList(String userId); // 팔로우 목록 가져오기
}
