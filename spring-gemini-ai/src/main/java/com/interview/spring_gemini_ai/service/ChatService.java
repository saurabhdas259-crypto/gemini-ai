package com.interview.spring_gemini_ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service 
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }


    public Flux<String> chat(String prompt) {
        return chatClient
                .prompt(prompt)
                .stream()
                .content();
    }

}
