package com.fse.blog.repository;

import com.fse.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
     Long countBlogByCategory_Name(String category_name);

     @Query(value = "SELECT b.* FROM blogs AS b " +
             "JOIN users AS u ON b.user_id = u.id WHERE u.email = ?1", nativeQuery = true)
     List<Blog> getBlogsByUserEmail(String email);
}
