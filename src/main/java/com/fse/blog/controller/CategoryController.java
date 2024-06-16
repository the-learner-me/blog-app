package com.fse.blog.controller;

import com.fse.blog.dto.request.CategoryRequest;
import com.fse.blog.model.Category;
import com.fse.blog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This controller implements api for adding and fetching blog categories.
 */
@RestController
@RequestMapping("/api/categories")
@Validated
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryRequest category) {
        log.info("Request received for adding blog category");
        return ResponseEntity.ok(categoryService.save(category));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        log.info("Request received for fetching all blog category");
        return ResponseEntity.ok(categoryService.getAll());
    }

}
