package com.fse.blog.mapper;

import com.fse.blog.dto.request.BlogRequest;
import com.fse.blog.model.Blog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlogMapperTest {
    BlogRequest blogRequest = new BlogRequest();

    @BeforeEach
    void setUp() {
        blogRequest.setTitle("title");
        blogRequest.setContent("content");
        blogRequest.setCategoryId(1);
        blogRequest.setUserEmail("test@test.com");
    }

    @Test
    void blogDtoToBlogEntity() {
        Blog blog = BlogMapper.blogDtoToEntity(blogRequest);

        assertEquals(blog.getTitle(), blogRequest.getTitle());
        assertEquals(blog.getContent(), blogRequest.getContent());
        assertEquals(blog.getCategory().getId(), blogRequest.getCategoryId());
        assertEquals(blog.getUser().getEmail(), blogRequest.getUserEmail());
    }
}
