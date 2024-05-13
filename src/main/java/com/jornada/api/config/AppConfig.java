package com.jornada.api.config;

import com.jornada.api.gemini.GeminiInterface;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


@Configuration
public class AppConfig {

    private final Dotenv dotenv = Dotenv.load();

    @Bean
    public RestClient geminiRestClient(@Value("${GEMINI_URL}") String geminiUrl, @Value("${GOOGLE_API_KEY}") String googleApiKey) {
        String baseUrl = dotenv.get("GEMINI_URL");
        String apiKey = dotenv.get("GOOGLE_API_KEY");
        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("x-goog-api-key", apiKey)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .build();
    }

    @Bean
    public GeminiInterface geminiInterface (@Qualifier("geminiRestClient") RestClient restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(GeminiInterface.class);
    }
}
