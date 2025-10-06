package com.minucecoding.brief_ai_with_spring.services;

import com.minucecoding.brief_ai_with_spring.client.NewsApiClient;
import com.minucecoding.brief_ai_with_spring.client.OllamaClient;
import com.minucecoding.brief_ai_with_spring.dto.NewsApiResponse;
import com.minucecoding.brief_ai_with_spring.dto.NewsSummaryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class NewsBriefService {

    private final NewsApiClient newsApiClient;
    private final OllamaClient ollamaClient;

    @Autowired
    public NewsBriefService(NewsApiClient newsApiClient,
                            OllamaClient ollamaClient) {
        this.newsApiClient = newsApiClient;
        this.ollamaClient = ollamaClient;
    }

    public NewsSummaryResponse generateGeneralNewsBrief() {
        final NewsApiResponse newsApiResponse = newsApiClient.getTopHeadlines();
        log.info("Requesting summary for {} articles", newsApiResponse.getArticles().size());

        final OllamaResponse ollamaResponse = ollamaClient.generateSummary(newsApiResponse.getArticles(), false);

        return NewsSummaryResponse.builder()
                .createdAt(java.time.LocalDateTime.now())
                .summary("This is a placeholder sumary.")
                .build();
    }
}
