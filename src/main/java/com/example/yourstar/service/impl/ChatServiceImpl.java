package com.example.yourstar.service.impl;

import com.example.yourstar.data.dto.ChatMessageDto;
import com.example.yourstar.data.dto.IdNameImageDto;
import com.example.yourstar.data.entity.ChatMessageEntity;
import com.example.yourstar.data.entity.ChatRoomEntity;
import com.example.yourstar.data.entity.UserEntity;
import com.example.yourstar.data.repository.ChatMessageRepository;
import com.example.yourstar.data.repository.ChatRoomRepository;
import com.example.yourstar.data.repository.UserRepository;
import com.example.yourstar.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class ChatServiceImpl implements ChatService {
    private ChatMessageRepository chatMessageRepository;
    private UserRepository userRepository;
    private ChatRoomRepository chatRoomRepository;

    public ChatServiceImpl(ChatMessageRepository chatMessageRepository,UserRepository userRepository,ChatRoomRepository chatRoomRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.userRepository = userRepository;
        this.chatRoomRepository = chatRoomRepository;
    }
    @Override
    public void saveChatMessage(ChatMessageDto message) {
        ChatMessageEntity entity = new ChatMessageEntity();
        entity.setSender(userRepository.getById(message.getSender()));
        entity.setReceiver(userRepository.getById(message.getReceiver()));
        entity.setContent(message.getContent());
        entity.setSentAt(message.getSentAt());

        chatMessageRepository.save(entity);
    }


    @Override
    public List<ChatMessageDto> getMessagesBetweenUsers(String sender, String receiver) {
        UserEntity senderEntity = userRepository.getById(sender);
        UserEntity receiverEntity = userRepository.getById(receiver);
        List<ChatMessageEntity> entities = chatMessageRepository.findBySenderAndReceiver(senderEntity, receiverEntity);
        return entities.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<IdNameImageDto> getChatRoom(String userId) {
        UserEntity userEntity = userRepository.getById(userId);

        List<ChatRoomEntity> chatRoomEntityListResult = chatRoomRepository.findDistinctChatRoomsByUser(userEntity);
        List<IdNameImageDto> IdNameImageDtoList = chatRoomEntityListResult.stream()
                .map(chatRoomEntity -> {
                    IdNameImageDto idNameImageDto = new IdNameImageDto();
                    if (!chatRoomEntity.getUser2().getUserId().equals(userId)){
                        log.info("userList in : {}",chatRoomEntity.getUser2());
                        idNameImageDto.setUserId(chatRoomEntity.getUser2().getUserId());
                        idNameImageDto.setName(chatRoomEntity.getUser2().getUserName());
                        if (chatRoomEntity.getUser2().getUserProfileEntity().getUserProfile() != null){
                            idNameImageDto.setImage(Base64.getEncoder().encodeToString(chatRoomEntity.getUser2().getUserProfileEntity().getUserProfile()));
                        } else {
                            idNameImageDto.setImage(null);
                        }
                        return idNameImageDto;
                    }
                    if (!chatRoomEntity.getUser1().getUserId().equals(userId)){
                        log.info("userList in : {}",chatRoomEntity.getUser1());
                        idNameImageDto.setUserId(chatRoomEntity.getUser1().getUserId());
                        idNameImageDto.setName(chatRoomEntity.getUser1().getUserName());
                        if (chatRoomEntity.getUser1().getUserProfileEntity().getUserProfile() != null){
                            idNameImageDto.setImage(Base64.getEncoder().encodeToString(chatRoomEntity.getUser1().getUserProfileEntity().getUserProfile()));
                        } else {
                            idNameImageDto.setImage(null);
                        }
                        return idNameImageDto;
                    }
                    return null;
                })
                .collect(Collectors.toList());
        return IdNameImageDtoList;
    }

    @Override
    public void makeChatRoom(String user1,String user2) {
        ChatRoomEntity chatRoomEntity = new ChatRoomEntity();
        chatRoomEntity.setUser1(userRepository.getById(user1));
        chatRoomEntity.setUser2(userRepository.getById(user2));
        chatRoomEntity.setMakeRoomTime(new Timestamp(System.currentTimeMillis()));
        chatRoomRepository.save(chatRoomEntity);
    }

    // 채팅 메시지 Entity를 Dto로 변환하는 도움 메소드
    private ChatMessageDto convertEntityToDto(ChatMessageEntity entity) {
        ChatMessageDto dto = new ChatMessageDto();
        dto.setSender(entity.getSender().getUserId());
        dto.setReceiver(entity.getReceiver().getUserId());
        dto.setContent(entity.getContent());
        dto.setSentAt(entity.getSentAt());
        return dto;
    }
}
