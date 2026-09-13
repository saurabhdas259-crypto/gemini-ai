package com.interview.spring_gemini_ai.controller;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/rag")
public class RagController {


    private static final String CONVERSATION_ID = null;

    private final ChatClient chatClient;

    private final VectorStore vectorStore;


    public RagController(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder.build();
        this.vectorStore = vectorStore;
    }

    @Value ("classpath:prompts/systemDataPrompt.st")
    private Resource template;

    @GetMapping ("/chat")
    public String chatWithRag(@RequestParam String query, @RequestHeader String userName) {

        SearchRequest searchRequest = SearchRequest.builder()
                .query(query)
                .topK(3)
                .similarityThreshold(0.5)
                .build();

        // A - Augumentation        
        List<Document> similarDocuments = vectorStore
        .similaritySearch(searchRequest);
        
        // extract the text from the similar documents
        List<String> similarTextStrings = similarDocuments.stream()
                .map(Document::getText)
                .toList();

        // B - Generation
       String response = chatClient.prompt()
                .system(promptSystemSpec -> promptSystemSpec
                    .text(template)
                    .param("documents", similarTextStrings))
                    .user(query)
                    .call()
                    .content();
        return  response;    
    }
}
