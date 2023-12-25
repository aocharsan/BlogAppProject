package com.seleniumexpress.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seleniumexpress.blog.payload.CommentDto;
import com.seleniumexpress.blog.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/posts/{post_id}/comment")
	public ResponseEntity<CommentDto> createComment(@PathVariable(name = "post_id") Long postID,
			@RequestBody CommentDto commentDto) {

		return new ResponseEntity<>(commentService.createComment(postID, commentDto), HttpStatus.CREATED);

	}

	@GetMapping("/posts/{post_id}/comments")
	public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable(name = "post_id") Long id) {

		return new ResponseEntity<>(commentService.getCommentsByPostId(id), HttpStatus.OK);
	}

	@GetMapping("/posts/{post_id}/comment/{comment_id}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "post_id") Long postId,
			@PathVariable(name = "comment_id") Long commentId) {

		return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
	}
	
	@PutMapping("/posts/{post_id}/comment/{comment_id}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable("post_id") Long postid,
			                                    @PathVariable("comment_id")  Long commentid,
			                                     @RequestBody  CommentDto commentDto){
		return new ResponseEntity<CommentDto>(commentService.updateComment(postid, commentid, commentDto),HttpStatus.OK);
		
	}

}
