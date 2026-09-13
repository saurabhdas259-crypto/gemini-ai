package com.rag;


import java.util.List;

import jakarta.annotation.PostConstruct;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


@Component
public class RagPdfDataLoader {

    private final VectorStore vectorStore;

    @Value ("classpath:Solutions Architect.pdf")
    private Resource pdfResource;

    public RagPdfDataLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void loadPdfData() {
        // Implement logic to load PDF data, extract text, and add to vector store
        // For example, you can use a PDF parsing library to extract text from PDFs
        // and then create Document objects to add to the vector store.

        TikaDocumentReader documentReader = new TikaDocumentReader(pdfResource);
        List<Document> documents = documentReader.get();

        TokenTextSplitter tokenTextSplitter = TokenTextSplitter.builder()
        .withChunkSize(800)
         .build();
         List<Document> splitDocuments = tokenTextSplitter.split(documents);
        vectorStore.add(splitDocuments);
    }
}
