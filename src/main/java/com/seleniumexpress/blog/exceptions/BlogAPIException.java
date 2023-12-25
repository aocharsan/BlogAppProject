package com.seleniumexpress.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//we throw this exception whenever we write some business logic or validate request parameters
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BlogAPIException extends RuntimeException {
	

	private static final long serialVersionUID = 1L;

	private String message;

	public BlogAPIException(String message) {

		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
