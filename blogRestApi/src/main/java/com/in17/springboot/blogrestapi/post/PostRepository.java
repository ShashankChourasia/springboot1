package com.in17.springboot.blogrestapi.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PostRepository extends JpaRepository<Post, String>{

}
