package com.ecommerce.Services;

import java.util.List;

import com.ecommerce.payloads.ProductDto;

public interface ProductService {

	ProductDto createProduct(ProductDto productDto);
	
	ProductDto updateProduct(ProductDto productDto,Integer productId);
	
	ProductDto getProductById(Integer productId);
	
	List<ProductDto> getAllProducts();
	
	List<ProductDto> getproductsByCategory(Integer catId);
	
	void deleteProduct(Integer productId);
	
}
