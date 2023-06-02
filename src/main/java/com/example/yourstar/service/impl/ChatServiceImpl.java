package com.example.yourstar.service.impl;

import com.example.yourstar.data.dto.ChatMessageDto;
import com.example.yourstar.data.entity.ChatMessageEntity;
import com.example.yourstar.data.repository.ChatMessageRepository;
import com.example.yourstar.service.ChatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatServiceImpl(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }
    @Override
    public void saveChatMessage(ChatMessageDto message) {
        ChatMessageEntity entity = new ChatMessageEntity();
        entity.setSender(message.getSender());
        entity.setReceiver(message.getReceiver());
        entity.setContent(message.getContent());
        entity.setSentAt(message.getSentAt());

        chatMessageRepository.save(entity);
    }


    @Override
    public List<ChatMessageDto> getMessagesBetweenUsers(String sender, String receiver) {
        List<ChatMessageEntity> entities = chatMessageRepository.findBySenderAndReceiver(sender, receiver);
        return entities.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    // 채팅 메시지 Entity를 Dto로 변환하는 도움 메소드
    private ChatMessageDto convertEntityToDto(ChatMessageEntity entity) {
        ChatMessageDto dto = new ChatMessageDto();
        dto.setSender(entity.getSender());
        dto.setReceiver(entity.getReceiver());
        dto.setContent(entity.getContent());
        dto.setSentAt(entity.getSentAt());
        return dto;
    }
}
