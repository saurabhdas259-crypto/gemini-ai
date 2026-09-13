package com.interview.spring_gemini_ai;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.autoconfigure.exclude=org.springframework.ai.model.vertexai.autoconfigure.gemini.VertexAiGeminiChatAutoConfiguration")
class SpringGeminiAiApplicationTests {

	@Test
	void contextLoads() {
	}

}
