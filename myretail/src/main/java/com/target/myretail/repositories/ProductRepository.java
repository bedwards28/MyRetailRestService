package com.target.myretail.repositories;

import com.target.myretail.exceptions.ProductNotFoundException;
import com.target.myretail.model.Product;

public interface ProductRepository {
	
	public Product getProduct(String productId) throws ProductNotFoundException;

}
