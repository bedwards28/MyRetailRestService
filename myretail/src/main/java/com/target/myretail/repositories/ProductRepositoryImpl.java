package com.target.myretail.repositories;

import java.io.IOException;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.myretail.exceptions.ProductNotFoundException;
import com.target.myretail.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private final RestTemplate restTemplate;

	public ProductRepositoryImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public Product getProduct(String productId) throws ProductNotFoundException {
		
		Product product = new Product();

		String url = "https://redsky.target.com/v2/pdp/tcin/" + productId
				+ "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";

		ObjectMapper infoMapper = new ObjectMapper();
		Map<String, Map> infoMap;

		try {
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			infoMap = infoMapper.readValue(response.getBody(), Map.class);

			product.setProductId(productId);
			Map<String, Map> productMap = infoMap.get("product");
			Map<String, Map> itemMap = productMap.get("item");
			Map<String, String> prodDescMap = itemMap.get("product_description");
			product.setTitle(prodDescMap.get("title"));


		} catch (IOException e) {
			 throw new ProductNotFoundException();
		}

		return product;
	}

}
