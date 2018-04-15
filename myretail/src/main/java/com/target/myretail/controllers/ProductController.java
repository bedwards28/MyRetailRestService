package com.target.myretail.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.target.myretail.exceptions.PriceNotFoundException;
import com.target.myretail.exceptions.ProductNotFoundException;
import com.target.myretail.model.Price;
import com.target.myretail.model.Product;
import com.target.myretail.repositories.PriceRepository;
import com.target.myretail.repositories.ProductRepository;

@RestController
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PriceRepository priceRepository;
	
	@GetMapping("/products/{productId}")
	public Product getProduct(@PathVariable String productId) throws Exception {
		
		Product product = null;
		
		product = productRepository.getProduct(productId);
		
		if (product == null) {
			throw new ProductNotFoundException();
		}
		
		Price price = priceRepository.findById(productId).orElse(null);
		
		if (price != null) {
			product.setPrice(price);
		} else {
			throw new PriceNotFoundException("Price data not found for product id");
		}
		
		return product;
		
	}

}
