package com.example.yourstar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // 메시지 브로커를 구성합니다.
    // /app 접두사로 시작하는 요청은 메시지 브로커로 전달됩니다.
    // /queue 접두사로 시작하는 요청은 Simple 메시지 브로커로 생성되어 구독한 클라이언트로 전송됩니다.
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/queue");
    }

    // STOMP 엔드포인트 등록합니다.
    // 해당 엔드포인트에 연결하여 WebSocket을 시작할 수 있습니다.
    // SockJS는 기본적인 웹 소켓과 폴백 옵션을 포함한 서버에 연결할 수 있도록 도와줍니다.
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").withSockJS();
    }
}