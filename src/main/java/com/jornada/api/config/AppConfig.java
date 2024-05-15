package com.jornada.api.config;

import com.jornada.api.gemini.GeminiInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


@Configuration
public class AppConfig {

    @Value("${gemini.url}")
    String baseUrl;
    @Value("${google.api.key}")
    String googleApiKey;

    @Bean
    public RestClient geminiRestClient(@Value("${gemini.url}") String baseUrl, @Value("${google.api.key}") String googleApiKey) {

        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("x-goog-api-key", googleApiKey)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .build();
    }

    @Bean
    public GeminiInterface geminiInterface(@Qualifier("geminiRestClient") RestClient restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(GeminiInterface.class);
    }
}
