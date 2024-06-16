package com.fse.blog.mapper;

import com.fse.blog.dto.request.BlogRequest;
import com.fse.blog.model.Blog;
import com.fse.blog.model.Category;
import com.fse.blog.model.User;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


@Component
public class BlogMapper {

    /**
     * Maps a BlogRequest to a Blog entity.
     *
     * @param blogRequest The BlogRequest to map.
     * @return The mapped Blog entity.
     */
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "userEmail", target = "user.email")
    public static Blog blogDtoToEntity(BlogRequest blogRequest){
        User user = User.builder().email(blogRequest.getUserEmail()).build();
        Category category = Category.builder().id(blogRequest.getCategoryId()).build();
        return Blog.builder()
                .title(blogRequest.getTitle())
                .content(blogRequest.getContent())
                .content(blogRequest.getContent())
                .user(user)
                .category(category)
                .build();
    }
}
