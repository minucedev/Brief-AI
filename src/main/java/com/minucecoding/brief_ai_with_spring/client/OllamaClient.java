package com.minucecoding.brief_ai_with_spring.client;

import com.minucecoding.brief_ai_with_spring.dto.Article;
import com.minucecoding.brief_ai_with_spring.dto.OllamaResponse;
import com.minucecoding.brief_ai_with_spring.dto.OllamaResquest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class OllamaClient {
    @Value("${ollama.base.url}")
    private String ollamaUrl;

    @Value("${ollama.mistral.model}")
    private String aiModel;

    public OllamaResponse generateSummary(final List<Article> articles, final boolean isRender) {
        final RestTemplate restTemplate = new RestTemplate();

        final String prompt = getPrompt(articles, isRender);

        final OllamaResquest resquestPayload = OllamaResquest.builder()
                .model(aiModel)
                .prompt(prompt)
                .stream(false)
                .build();

        final HttpEntity<OllamaResquest> entity = getHttpEntity(resquestPayload);

        final ResponseEntity<OllamaResponse> response =
                restTemplate.postForEntity(ollamaUrl, entity, OllamaResponse.class);
        log.info("Ollama response: {}", response.getBody());
        return response.getBody();

    }

    private static HttpEntity<OllamaResquest> getHttpEntity(OllamaResquest resquestPayload) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OllamaResquest> entity = new HttpEntity<>(resquestPayload, headers);
        return entity;
    }


    private String getPrompt(List<Article> articles, final boolean isRender) {
        final StringBuilder promptBuilder = new StringBuilder();

        if (isRender) {
            promptBuilder.append("You are an expert HTML generator. " +
                    "Summarize the following news articles and return only the full HTML code for a complete webpage. " +
                    "Make the page look clean and readable using inline CSS or basic embedded styles. " +
                    "Do not include any explanations, introductions, or markdown formatting - " +
                    "only raw HTML code that can be copied and run in a browser. Do not add markdown such as ```html\n\n");

        } else {
            promptBuilder.append("You are a news summarizer. " +
                    "Summarize the top global news stories from today in concise and informative way. " +
                    "Focus on major events, political developments, economic updates, and major technology or " +
                    "science breakthroughs. Keep the summary clear, objective, and easy to read, like a daily news brief.");

        }

//        for (Article article : articles) {
//            promptBuilder
//                    .append("Title: ").append(article.getTitle()).append("\n")
//                    .append("Description: ").append(article.getDescription()).append("\n")
//                    .append("End of article\n\n");
//        }
        for (int i = 0; i < Math.min(2, articles.size()); i++) {
            Article article = articles.get(i);
            promptBuilder
                    .append("Title: ").append(article.getTitle()).append("\n")
                    .append("Description: ").append(article.getDescription()).append("\n")
                    .append("End of article\n\n");
        }

        final String prompt = promptBuilder.toString();
        return prompt;
    }
}
