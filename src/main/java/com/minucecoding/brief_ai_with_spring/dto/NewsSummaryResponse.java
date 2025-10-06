package com.minucecoding.brief_ai_with_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsSummaryResponse {
    private String summary;
    private LocalDateTime createdAt;
}
