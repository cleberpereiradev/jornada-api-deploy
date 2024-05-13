package com.jornada.api.gemini;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

public class GeminiRecords {

    public record GeminiRequest(List<Content> contents) {}
    public record Content(List<Part> parts) {}

    public sealed interface Part
        permits TextPart, InlineDataPart {
    }

    public record TextPart(String text) implements Part {}

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public record InlineDataPart(InlineData inlineData) implements Part {}

    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public record InlineData(String data, String mimeType) {}

    public record GeminiResponse(List<Candidate> candidates,
                                 PromptFeedback promptFeedback) {
        public record Candidate(Content content,
                                String finishReason,
                                int index,
                                List<SafetyRating> safetyRatings) {
            public record Content(List<TextPart> parts, String role) {}
        }
    }

    public record PromptFeedback(String promptFeedback) {}
    public record SafetyRating(String category, String probability) {}


}
