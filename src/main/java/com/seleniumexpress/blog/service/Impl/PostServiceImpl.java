package com.seleniumexpress.blog.service.Impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.seleniumexpress.blog.entities.Post;
import com.seleniumexpress.blog.payload.PostDto;
import com.seleniumexpress.blog.repositories.PostRepository;
import com.seleniumexpress.blog.service.PostService;
import com.seleniumexpress.blog.exceptions.*;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public PostDto createPost(PostDto postDto) {
		
		Post post = dtoToMap(postDto);
        Post newPost = postRepository.save(post);

		return mapToDto(newPost);
	}

	public List<PostDto> getAllPosts() {

		List<Post> posts = postRepository.findAll();
		return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

	}
	
	@Override
	public PostDto getPostById(Long postID) {
		
		Post postById = postRepository.findById(postID)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postID", postID));

		return mapToDto(postById);
	}

	@Override
	public PostDto updatePostById(PostDto postDto, Long postID) {

		Post postByIdFromDB = postRepository.findById(postID)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postID", postID));

		postByIdFromDB.setTitle(postDto.getTitle());
		postByIdFromDB.setDescription(postDto.getDescription());
		postByIdFromDB.setContent(postDto.getContent());

		Post updatePost=postRepository.save(postByIdFromDB);
		return mapToDto(updatePost);
	}
	
	@Override
	public void removePost(Long postID) {
		
		Post postByIdFromDB = postRepository.findById(postID)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postID", postID));
		postRepository.delete(postByIdFromDB);
	}


	
	
	private PostDto mapToDto(Post post) {

		// convert entity to DTo
		PostDto dto = new PostDto();
		dto.setId(post.getId());
		dto.setTitle(post.getTitle());
		dto.setDescription(post.getDescription());
		dto.setContent(post.getContent());

		return dto;
	}

	private Post dtoToMap(PostDto postDto) {
		// convert DTo to entity
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());

		return post;

	}

	
	
	
}
