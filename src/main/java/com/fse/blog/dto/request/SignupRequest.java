package com.fse.blog.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    @NotBlank
    @Email
    @Size(max = 32)
    private String email;
    @NotBlank
    @Size(min = 5, max = 32)
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

}
