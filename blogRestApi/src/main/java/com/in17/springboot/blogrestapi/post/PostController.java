package com.in17.springboot.blogrestapi.post;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = { "http://localhost:3000/" })
public class PostController {

	private static final Logger logger = Logger.getLogger(PostController.class.getName());

	@Autowired
	private PostService service;

	@Autowired
	private PostRepository postRepository;

	@GetMapping("/posts")
	public List<Post> getAllPosts() {
		return service.retrieveAllPosts();
	}

	@RequestMapping(value = "/new-posts", method = RequestMethod.POST)
	public ResponseEntity<Post> createNewPost(@RequestBody Post post) {
		Post newPost = service.addNewPost(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId())
				.toUri();
		return ResponseEntity.created(location).body(newPost);
	}

	@RequestMapping(value = "/update-posts", method = RequestMethod.PUT)
	public ResponseEntity<Post> updatePost(@RequestBody Post post) {
		logger.info("Updating post with ID: " + post.getId());

		Optional<Post> optionalPost = postRepository.findById(post.getId());

		if (optionalPost.isEmpty()) {
			logger.warning("Post with ID " + post.getId() + " not found");
			return ResponseEntity.notFound().build();
		}

		Post updatePost = optionalPost.get();
		updatePost.setDescription(post.getDescription());
		updatePost.setTitle(post.getTitle());
		updatePost.setImagePath(post.getImagePath());
		updatePost.setAuthor(post.getAuthor());

		postRepository.save(updatePost);

		logger.info("Post updated successfully: " + updatePost.toString());
		return ResponseEntity.ok(updatePost);
	}
	
	@DeleteMapping("/delete-post/{id}")
    public ResponseEntity<String> deleteItemById(@PathVariable String id) {
        if (service.deleteItemById(id)) {
            return new ResponseEntity<>("Post with ID " + id + " deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Post with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

//	@GetMapping("/posts/ex")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
//	public String hello() {
//		return "Logged in successfully";
//	}

}
