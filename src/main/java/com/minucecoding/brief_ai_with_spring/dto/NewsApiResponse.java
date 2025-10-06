package com.minucecoding.brief_ai_with_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsApiResponse {
    private int totalResults;
    private List<Article> articles;

}
