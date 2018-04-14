package com.target.myretail.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.target.myretail.exceptions.ProductNotFoundException;
import com.target.myretail.model.Price;
import com.target.myretail.model.Product;
import com.target.myretail.repositories.PriceRepository;
import com.target.myretail.repositories.ProductRepository;
import com.target.myretail.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PriceRepository priceRepository;
	
	@GetMapping("/products/{productId}")
	public Product getProduct(@PathVariable String productId) {
		
		// get product
		Product product = new Product();
		
		try {
			product = productRepository.getProduct(productId);
		} catch (ProductNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// add price info
		Price price = priceRepository.findById(productId).orElse(null);
		
		if (price != null) {
			product.setPrice(price);
		}
		
		// return full product
		return product;
		
	}

}
