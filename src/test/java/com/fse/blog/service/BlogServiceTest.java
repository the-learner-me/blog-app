package com.fse.blog.service;

import com.fse.blog.dto.request.BlogRequest;
import com.fse.blog.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BlogServiceTest {
    private final BlogService blogService;
    BlogRequest blogRequest = new BlogRequest();

    @Autowired
    BlogServiceTest(BlogService blogService) {
        this.blogService = blogService;
    }

    @BeforeEach
    void setUp() {
        blogRequest.setTitle("title");
        blogRequest.setContent("content");
        blogRequest.setCategoryId(1);
        blogRequest.setUserEmail("admin@test.com");
    }

    @Test
    void save() throws UserNotFoundException {
        BlogRequest save = blogService.save(blogRequest);

        assertEquals(save.getCategoryId(), blogRequest.getCategoryId());
        assertEquals(save.getUserEmail(), blogRequest.getUserEmail());
    }
}
