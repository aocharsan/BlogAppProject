package com.seleniumexpress.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seleniumexpress.blog.payload.PostDto;
import com.seleniumexpress.blog.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/createPost")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {

		return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

	}

	@GetMapping("/getallposts")
	public ResponseEntity<List<PostDto>> getAllPosts() {

		return ResponseEntity.ok(postService.getAllPosts());
	}

	@GetMapping("getpost/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long postID) {

		return ResponseEntity.ok(postService.getPostById(postID));

	}

	@PutMapping("updatepost/{id}")
	public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto,
			@PathVariable(name = "id") Long postID) {

		return ResponseEntity.ok(postService.updatePostById(postDto, postID));

	}

	@DeleteMapping("removepost/{id}")
	public ResponseEntity<String> removePost(@PathVariable(name = "id") Long postID) {

		postService.removePost(postID);

		return new ResponseEntity<>("post deleted successfully", HttpStatus.OK);
	}

}
