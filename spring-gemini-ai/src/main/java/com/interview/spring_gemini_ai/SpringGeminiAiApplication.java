package com.interview.spring_gemini_ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.interview.spring_gemini_ai", "com.rag" })
public class SpringGeminiAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringGeminiAiApplication.class, args);
	}

}
