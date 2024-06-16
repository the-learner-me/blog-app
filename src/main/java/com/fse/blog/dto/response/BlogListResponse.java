package com.fse.blog.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogListResponse {

    private Long id;
    private String title;
    private String category;
    private String author;
    private String contentPreview;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

}
