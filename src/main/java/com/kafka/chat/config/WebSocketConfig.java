package com.kafka.chat.config;

import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
}
