package com.fse.blog.service;

import com.fse.blog.model.User;
import com.fse.blog.dto.ProfileDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    boolean existsByEmail(String email);
    User getByEmail(String email);
    User save(User user);
    void updateProfile(ProfileDto profile);
    ProfileDto getProfileInfo(String email);
}
