package com.krry.repository;

import com.krry.entity.Blog;
import com.krry.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BlogRepository extends MongoRepository<Blog, String>{

    @Override
    public List<Blog> findAll();
    public List<Blog> findBlogsByUid(String uid);

    public Blog findBlogBy_id(String _id);
    public List<Blog> findBlogsByType(String type);
    public void removeBy_id(String _id);

    public List<Blog> findByTitleLike(String title);
    public List<Blog> findByContentLike(String content);
    public List<Blog> findByType(String type);


}
