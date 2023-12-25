package com.seleniumexpress.blog.service;

import java.util.List;


import com.seleniumexpress.blog.payload.CommentDto;

public interface CommentService {

	CommentDto createComment(Long postID, CommentDto commentDto);

	List<CommentDto> getCommentsByPostId(Long postId);
	
	CommentDto getCommentById(Long postId,Long commentId);
	
	CommentDto updateComment(Long postId,Long commentId,CommentDto commentDto );
}
