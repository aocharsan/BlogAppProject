package com.seleniumexpress.blog.service;

import java.util.List;

import com.seleniumexpress.blog.payload.PostDto;

public interface PostService {
	
	 PostDto createPost(PostDto postDto); 
		
	 List<PostDto> getAllPosts();

	PostDto getPostById(Long postID);

	PostDto updatePostById(PostDto postDto, Long postID);

	void removePost(Long postID);	
	

}
