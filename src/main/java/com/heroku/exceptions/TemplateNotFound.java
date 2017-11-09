package com.heroku.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Template not found")  // 404
public class TemplateNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
}