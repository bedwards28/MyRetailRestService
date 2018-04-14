package com.target.myretail.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.myretail.exceptions.PriceNotFoundException;
import com.target.myretail.exceptions.ProductNotFoundException;
import com.target.myretail.model.Price;
import com.target.myretail.model.Product;
import com.target.myretail.repositories.PriceRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final RestTemplate restTemplate;
	
	@Autowired
	private PriceRepository priceRepository;

	public ProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public Product getProduct(String id) {
		
		Product product = new Product();
		
		String url = "https://redsky.target.com/v2/pdp/tcin/" + id + 
				"?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
		
//		Map<String, String> urlVariables = new HashMap<>();
//		urlVariables.put("id", id + "");
		
		ObjectMapper infoMapper = new ObjectMapper();
		Map<String, Map> infoMap;
		
		try {
//			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, urlVariables);
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			infoMap = infoMapper.readValue(response.getBody(), Map.class);
			
			product.setProductId(id);
			Map<String, Map> productMap = infoMap.get("product");
			Map<String, Map> itemMap = productMap.get("item");
			Map<String, String> prodDescMap = itemMap.get("product_description");
			product.setTitle(prodDescMap.get("title"));
			
//			Price price = priceRepository.findByProductId(id);
			Price price = priceRepository.findById(id).orElse(null);
			
//			if (price == null) {
//				throw new PriceNotFoundException("Price not found");
//			}
			
			product.setPrice(price);
			
		} catch (Exception e) {
//			throw new ProductNotFoundException("Product not found");
		}
		
		return product;
	}

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

}
