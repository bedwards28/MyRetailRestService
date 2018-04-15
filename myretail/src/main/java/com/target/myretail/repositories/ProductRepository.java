package com.target.myretail.repositories;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.target.myretail.exceptions.ProductNotFoundException;
import com.target.myretail.model.Product;

public interface ProductRepository {
	
	public Product getProduct(String productId) throws ProductNotFoundException;

}
