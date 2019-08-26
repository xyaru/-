package com.krry.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.krry.entity.User;


public interface UserRepository extends MongoRepository<User, String> {
/**
 * 
 * findByUsername 命名有研究，比如 findBy后面的名称是实体类属性名称
 * Username
 * 
 */
	public User findByUsername(String username);
}
