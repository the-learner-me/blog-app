package com.fse.blog.service;

import com.fse.blog.model.Category;
import com.fse.blog.dto.request.CategoryRequest;

import java.util.List;

public interface CategoryService {
    Category save(CategoryRequest category);
    List<Category> getAll();
}
