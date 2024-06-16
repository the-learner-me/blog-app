package com.fse.blog.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private Integer categoryId;
    @JsonIgnore
    private String userEmail;

}
