package com.fse.blog.service.impl;

import com.fse.blog.repository.CategoryRepository;
import com.fse.blog.service.CategoryService;
import com.fse.blog.mapper.CategoryMapper;
import com.fse.blog.model.Category;
import com.fse.blog.dto.request.CategoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(CategoryRequest category) {
        return categoryRepository.save(CategoryMapper.categoryDtoToCategoryEntity(category));
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
