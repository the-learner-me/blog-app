package com.fse.blog.controller;

import com.fse.blog.dto.ProfileDto;
import com.fse.blog.dto.response.MessageResponse;
import com.fse.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This controller implements api for fetching and updating user profile.
 */
@RestController
@RequestMapping("/api/profile")
@Validated
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ProfileDto> getProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Received request for fetching user profile, {}", email);
        return ResponseEntity.ok(userService.getProfileInfo(email));
    }

    @PostMapping
    public ResponseEntity<?> updateProfile(@Valid @RequestBody ProfileDto profileDto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Received request for updating user profile, {}", email);
        profileDto.setEmail(email);
        userService.updateProfile(profileDto);
        return ResponseEntity.ok(new MessageResponse("Profile updated successfully"));
    }
}
