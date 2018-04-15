package com.target.myretail.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not found")
public class ProductNotFoundException extends Exception {
	
	private static final long serialVersionUID = 481586425848185293L;

	public ProductNotFoundException() {
		super();
	}

	public ProductNotFoundException(String message) {
		super(message);
	}
	
	public ProductNotFoundException(int id) {
		super("ProductNotFoundException with id="+id);
	}

}
