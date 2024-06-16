package com.fse.blog.service;

import com.fse.blog.dto.request.BlogRequest;
import com.fse.blog.dto.response.BlogDetailResponse;
import com.fse.blog.dto.response.BlogNumberInCategory;
import com.fse.blog.exception.BlogNotFoundException;
import com.fse.blog.exception.UserNotFoundException;

import java.util.List;

public interface BlogService {
    BlogRequest save(BlogRequest blog) throws UserNotFoundException;
    List<BlogDetailResponse> getAll();
    BlogDetailResponse getBlogDetail(Long id) throws BlogNotFoundException;
    List<BlogNumberInCategory> blogCategoryCount();

    List<BlogDetailResponse> getAllByUserEmail(String userEmail) throws BlogNotFoundException;

    void deleteBlogById(Long id);
}
