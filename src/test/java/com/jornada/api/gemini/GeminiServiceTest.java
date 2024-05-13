package com.jornada.api.gemini;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GeminiServiceTest {

    @Autowired
    private GeminiService geminiService;

    @Test
    void writeStory() {
        String text = geminiService.getCompletion("Quero um roteiro de viagem de 4 dias para Itapira.");
        assertNotNull(text);
        System.out.println(text);
    }
}
