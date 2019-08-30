package com.krry.repository;

import com.krry.entity.Blog;
import com.krry.entity.tBlog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface tBlogRepository extends MongoRepository<tBlog, String> {
//
//    @Override
//    List<tBlog> findAll();

    tBlog findByTitle(String title);

}
