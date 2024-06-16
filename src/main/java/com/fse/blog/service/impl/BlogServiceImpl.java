package com.fse.blog.service.impl;

import com.fse.blog.dto.request.BlogRequest;
import com.fse.blog.dto.response.BlogDetailResponse;
import com.fse.blog.dto.response.BlogNumberInCategory;
import com.fse.blog.exception.BlogNotFoundException;
import com.fse.blog.exception.UserNotFoundException;
import com.fse.blog.mapper.BlogMapper;
import com.fse.blog.model.Blog;
import com.fse.blog.model.Category;
import com.fse.blog.model.User;
import com.fse.blog.repository.BlogRepository;
import com.fse.blog.repository.CategoryRepository;
import com.fse.blog.repository.UserRepository;
import com.fse.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public BlogRequest save(BlogRequest blogRequest) throws UserNotFoundException {
        log.info("Saving blog to database. {}", blogRequest.getUserEmail());
        if(userRepository.findByEmail(blogRequest.getUserEmail()).isEmpty())
            throw new UserNotFoundException("Invalid user data, "+ blogRequest.getUserEmail());
        try {
            Blog blog = BlogMapper.blogDtoToEntity(blogRequest);
            User user = userRepository.findByEmail(blogRequest.getUserEmail()).get();
            blog.setUser(user);
            blog.setCreatedAt(LocalDateTime.now());
            blogRepository.save(blog);
        } catch (Exception e) {
            log.error("Cannot save to database. {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return blogRequest;
    }

    @Override
    public List<BlogDetailResponse> getAll() {
        log.info("Fetching blogs from DB");
        List<BlogDetailResponse> blogListResponses = new ArrayList<>();
        List<Blog> blogs = blogRepository.findAll();

        for (Blog blog : blogs) {
            blogListResponses.add(mapBlogToBlogDetailsResponse(blog, true));
        }
        return blogListResponses;
    }

    @Override
    public BlogDetailResponse getBlogDetail(Long id) throws BlogNotFoundException {
        log.info("Fetching blog details by id, {}", id);
        Optional<Blog> blogOptional = blogRepository.findById(id);
        if(blogOptional.isPresent()) {
            Blog blog = blogOptional.get();
            return mapBlogToBlogDetailsResponse(blog, false);
        } else {
            throw new BlogNotFoundException("Blog not found for given id: " + id);
        }
    }

    @Override
    public List<BlogNumberInCategory> blogCategoryCount() {
        log.info("Fetching blog count by category");
        List<Category> categories = this.categoryRepository.findAll();
        List<BlogNumberInCategory> blogCategoryCount = new ArrayList<>();
        for (Category cat: categories) {
            Long blogCount = this.blogRepository.countBlogByCategory_Name(cat.getName());
            if (blogCount > 0) {
                blogCategoryCount.add(new BlogNumberInCategory(cat.getName(), blogCount));
            }
        }
        return blogCategoryCount;
    }

    @Override
    public List<BlogDetailResponse> getAllByUserEmail(String userEmail) throws BlogNotFoundException {
        List<Blog> blogList = this.blogRepository.getBlogsByUserEmail(userEmail);
        if(CollectionUtils.isEmpty(blogList))
            throw new BlogNotFoundException("No blogs found for given user email, "+userEmail);

        List<BlogDetailResponse> blogListResponses = new ArrayList<>();
        blogList.forEach(blog -> blogListResponses.add(mapBlogToBlogDetailsResponse(blog, true)));

        return blogListResponses;
    }

    @Override
    public void deleteBlogById(Long id) {
        blogRepository.deleteById(id);
    }

    private BlogDetailResponse mapBlogToBlogDetailsResponse(Blog blog, boolean contentPreviewFlag) {
        String author = "";
        if (blog.getUser().getFirstName() != null) {
            author = blog.getUser().getFirstName() + " " + blog.getUser().getLastName();
        }
        String contentPreview = contentPreviewFlag? "..." : null;
        String content = contentPreviewFlag? null: blog.getContent();

        int startIndex = blog.getContent().indexOf("<p");
        int endIndex = blog.getContent().indexOf("</p");
        if (startIndex != -1 && endIndex != -1) {
            contentPreview = blog.getContent().substring(startIndex, endIndex);
        }
        return new BlogDetailResponse(
                blog.getId(),
                blog.getTitle(),
                blog.getCategory().getName(),
                author,
                content,
                contentPreview,
                blog.getCreatedAt()
        );
    }
}
