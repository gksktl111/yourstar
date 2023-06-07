package com.example.yourstar.controller;

import com.example.yourstar.data.dto.ChatMessageDto;
import com.example.yourstar.data.dto.IdNameImageDto;
import com.example.yourstar.data.dto.user.UserIdDto;
import com.example.yourstar.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@RestController
public class ChatController {
    private final SimpMessagingTemplate template;
    private final ChatService chatService; // 서비스 추가

    public ChatController(SimpMessagingTemplate template, ChatService chatService) {
        this.template = template;
        this.chatService = chatService;
    }

    // 이 메소드는 클라이언트가 "/app/chat/{receiver}"로 메시지를 보낼 때 호출됩니다.
            // {receiver} 변수(채팅 상대)는 DestinationVariable로 추출할 수 있습니다.
            // 해당 템플릿은 "/queue/messages/{receiver}"로 메시지를 전달합니다.
            // 프런트엔드에서는 "/queue/messages/[yourUsername]" 주소를 구독하면 메시지를 받을 수 있습니다.
    @MessageMapping("/chat/{receiver}")
    public void handleChatMessage(Authentication authentication, @DestinationVariable String receiver, ChatMessageDto message) {
        String destination = "/queue/messages/" + receiver; // 주소 설정
        message.setSender(authentication.getName()); // 메세지 전송하는 사람 설정
        message.setSentAt(new Timestamp(System.currentTimeMillis())); // 메시지 전송 시간 설정
        message.setReceiver(receiver); // 메세지 받는사람 설정
        chatService.saveChatMessage(message);  // 메시지 저장
        template.convertAndSend(destination, message); // destination 경로에 mwssages 전달(클라이언트에 메시지 전송)
    }

    @PostMapping("/past-messages")
    public List<ChatMessageDto> getPastMessages(@RequestBody UserIdDto otherPerson, Authentication authentication ) {
        log.info("채팅 유저(나) : {}",authentication.getName()); // 로그인한 유저(jwt로 구분)
        log.info("채팅 상대방 유저 : {}",otherPerson.getUserId()); // 상대방 유저
        return chatService.getMessagesBetweenUsers(otherPerson.getUserId(),authentication.getName());
    }

    @PostMapping("/makechatroom")
    public String makechatroom(Authentication authentication, UserIdDto userIdDto) {
        try {
            chatService.makeChatRoom(authentication.getName(),userIdDto.getUserId());
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }

    @PostMapping("/getchatroom")
    public List<IdNameImageDto> getchatroom(Authentication authentication ) {
            return chatService.getChatRoom(authentication.getName());
    }
}

