package com.KoreaIT.project.BAP.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.KoreaIT.project.BAP.repository.ProductRepository;
import com.KoreaIT.project.BAP.vo.Product;

@Service
public class ProductService {
	
	ProductRepository productRepository;
	
	ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> getForPrintproducts(String searchKeyword, String order_by, int low_price, int high_price) {
		return productRepository.getForPrintproducts(searchKeyword, order_by, low_price, high_price);
	}

}
