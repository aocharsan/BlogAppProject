package com.seleniumexpress.blog.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seleniumexpress.blog.entities.Comment;
import com.seleniumexpress.blog.entities.Post;
import com.seleniumexpress.blog.exceptions.BlogAPIException;
import com.seleniumexpress.blog.exceptions.ResourceNotFoundException;
import com.seleniumexpress.blog.payload.CommentDto;
import com.seleniumexpress.blog.repositories.CommentRepository;
import com.seleniumexpress.blog.repositories.PostRepository;
import com.seleniumexpress.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public CommentDto createComment(Long postID, CommentDto commentDto) {

		Comment comment = dtoToMap(commentDto);
		// Retrieving Post entity by Id from DB
		Post postFromDB = postRepository.findById(postID)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post_ID", postID));
		// set Post to Comment entity
		comment.setPost(postFromDB);
		// save comment entity
		return mapToDto(commentRepository.save(comment));
	}

	@Override
	public List<CommentDto> getCommentsByPostId(Long postId) {

		List<Comment> commentFromDB = commentRepository.getComments(postId);
		List<CommentDto> collectDto = commentFromDB.stream().map(comment -> mapToDto(comment))
				.collect(Collectors.toList());

		return collectDto;

	}
	
	@Override
	public CommentDto getCommentById(Long postId, Long commentId) {

		Post postFromDB = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post_ID", postId));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "comment_id", commentId));

		if (!comment.getPost().getId().equals(postFromDB.getId())) {

			throw new BlogAPIException("Comment not belogs to given post");
		}

		return mapToDto(comment);

	}
	
	
	@Override
	public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {

		Post postFromDB = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post_ID", postId));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "comment_id", commentId));

		if (!comment.getPost().getId().equals(postFromDB.getId())) {

			throw new BlogAPIException("Comment not belogs to given post");
		}

		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		commentRepository.save(comment);

		return mapToDto(comment);

	}
	
	

	private CommentDto mapToDto(Comment comment) {

		CommentDto commentDto = new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setName(comment.getName());
		commentDto.setEmail(comment.getEmail());
		commentDto.setBody(comment.getBody());

		return commentDto;

	}

	private Comment dtoToMap(CommentDto commentDto) {

		Comment comment = new Comment();
		comment.setId(commentDto.getId());
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());

		return comment;

	}

	
	

}
