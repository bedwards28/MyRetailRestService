package com.target.myretail.service;

import com.target.myretail.model.Product;

public interface ProductService {
	
	public Product getProduct(String id);
	
	public Product updateProduct(Product product);

}
