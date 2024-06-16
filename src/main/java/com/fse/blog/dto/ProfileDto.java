package com.fse.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String email;

}
