package com.fse.blog.mapper;

import com.fse.blog.model.Category;
import com.fse.blog.dto.request.CategoryRequest;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public static Category categoryDtoToCategoryEntity(CategoryRequest category) {
        return Category.builder().name(category.getName()).build();
    }

}
