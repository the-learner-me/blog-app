package com.fse.blog.mapper;

import com.fse.blog.model.User;
import com.fse.blog.dto.ProfileDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static ProfileDto userToProfileDto(User user) {
        return ProfileDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
