package com.jornada.api.gemini;

import com.jornada.api.gemini.GeminiRecords.GeminiRequest;
import com.jornada.api.gemini.GeminiRecords.GeminiResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("v1beta/models/")
public interface GeminiInterface {

    @PostExchange("{model}:generateContent")
    GeminiResponse getCompletion(
            @PathVariable String model,
            @RequestBody GeminiRequest request);

}
