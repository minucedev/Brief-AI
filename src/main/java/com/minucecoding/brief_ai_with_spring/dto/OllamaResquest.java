package com.minucecoding.brief_ai_with_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OllamaResquest {
    private String model;
    private String prompt;
    private boolean stream;
}
