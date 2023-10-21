package com.in17.springboot.blogrestapi.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public List<Post> retrieveAllPosts() {
		List<Post> allPosts = new ArrayList<>();
		postRepository.findAll().forEach(post -> allPosts.add(post));
		return allPosts;
	}

	public Post addNewPost(Post post) {
		postRepository.save(post);
		return post;
	}

	public boolean deleteItemById(String id) {
		if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
	}

}
