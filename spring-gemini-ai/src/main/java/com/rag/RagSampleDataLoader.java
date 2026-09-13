package com.rag;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component 
public class RagSampleDataLoader {

    private final VectorStore vectorStore;

    public RagSampleDataLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    // @PostConstruct 
    // public void loadSampleData() {
    //     List<String> sampleData = getSampleData();

    //     // chunks ->> embedding ->> vector store
    //     List<Document> documents =sampleData.stream()
    //             .map(Document::new)
    //             .toList();
    //     vectorStore.add(documents);
    // }

    private List<String> getSampleData() {
        return List.of(
                "Spring is a powerful framework for building Java applications.",
                "Spring Data provides easy integration with databases.",
                "Spring Security helps secure your applications.",
                "Spring Cloud enables building microservices and distributed systems."
        );
    }

    

}
