package com.minucecoding.brief_ai_with_spring.client;

import com.minucecoding.brief_ai_with_spring.dto.NewsApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j // quick log
public class NewsApiClient {

    @Value("${news.api.key}")
    private String apiKey;

    @Value("${news.api.base.url}")
    private String baseUrl;

    @Value("${news.api.default.country}")
    private String defaultCountry;

    @Value("${news.api.url.content.top.headlines}")
    private String topHeadlineUrl;

    public NewsApiResponse getTopHeadlines() {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = getTopHeadlinesUrl();

        log.info("Top headlines URL: {}", url);

        final NewsApiResponse result = restTemplate.getForObject(url, NewsApiResponse.class);

        log.info("Top headlines response: {}", result);
        return result;
    }


    private String getTopHeadlinesUrl() {
        final String baseUrl = this.baseUrl + this.topHeadlineUrl;

        final String urlWithParams = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("country", defaultCountry)
                .queryParam("apiKey", apiKey)
                .toUriString();
        log.info("Top headlines URL: {}", urlWithParams);
        return urlWithParams;
    }
}
