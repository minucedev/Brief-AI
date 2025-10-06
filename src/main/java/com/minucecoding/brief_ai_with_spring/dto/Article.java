package com.minucecoding.brief_ai_with_spring.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true) // bỏ qua 1 số giá trị không lấy
public class Article {
    private String title;
    private String description;
    private String content;
}
