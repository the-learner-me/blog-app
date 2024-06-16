package com.fse.blog.controller;

import com.fse.blog.dto.request.BlogRequest;
import com.fse.blog.dto.response.BlogDetailResponse;
import com.fse.blog.dto.response.BlogNumberInCategory;
import com.fse.blog.dto.response.MessageResponse;
import com.fse.blog.exception.BlogNotFoundException;
import com.fse.blog.exception.UserNotFoundException;
import com.fse.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This controller implements api for blog creation, fetching and deletion.
 */
@RestController
@RequestMapping("/api/blogs")
@Validated
@Slf4j
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> addBlog(@Valid @RequestBody BlogRequest blogRequest) throws UserNotFoundException {
        log.info("Received request to add Blog, {}", blogRequest.getUserEmail());
        blogRequest.setUserEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        blogService.save(blogRequest);
        return ResponseEntity.ok(new MessageResponse("Blog created successfully"));
    }

    @GetMapping
    public ResponseEntity<List<BlogDetailResponse>> getAll() {
        log.info("Received request to fetch all blogs");
        return ResponseEntity.ok(blogService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDetailResponse> getBlog(@PathVariable Long id) throws BlogNotFoundException {
        log.info("Received request to get Blog by id, {}", id);
        return ResponseEntity.ok(blogService.getBlogDetail(id));
    }

    @GetMapping("/count-by-category")
    public ResponseEntity<List<BlogNumberInCategory>> blogNumberInCategory() {
        log.info("Received request to get Blog count by category");
        return ResponseEntity.ok(this.blogService.blogCategoryCount());
    }

    @GetMapping("/user")
    public ResponseEntity<List<BlogDetailResponse>> getAllByUser() throws BlogNotFoundException {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Received request to get Blog by user email, {}",userEmail);
        return ResponseEntity.ok(blogService.getAllByUserEmail(userEmail));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteBlogById(@PathVariable Long id) {
        log.info("Received request to delete blog by id: {}", id);
        blogService.deleteBlogById(id);
        return ResponseEntity.ok(new MessageResponse("Deleted blog successfully"));
    }
}
