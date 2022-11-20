package com.ecommerce.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Entities.Category;
import com.ecommerce.Entities.Product;
import com.ecommerce.Exception.ResourceNotFoundException;
import com.ecommerce.Repositories.CategoryRepo;
import com.ecommerce.Repositories.ProductRepo;
import com.ecommerce.Services.ProductService;
import com.ecommerce.payloads.CategoryDto;
import com.ecommerce.payloads.ProductDto;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ProductDto createProduct(ProductDto productDto) {
		
		Product product = this.modelMapper.map(productDto, Product.class);
		product.setProductImage("default.png");
		Product savedProduct = this.productRepo.save(product);
		
		return this.modelMapper.map(savedProduct, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, Integer productId) {
		Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
		CategoryDto categoryDto = productDto.getCategory();
		Category category = this.modelMapper.map(categoryDto,Category.class);
		product.setCategory(category);
		product.setProductName(productDto.getProductName());
		product.setProductPrice(productDto.getProductPrice());
		
		Product savedProduct = this.productRepo.save(product);
		return this.modelMapper.map(savedProduct, ProductDto.class);
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));
		
		return this.modelMapper.map(product, ProductDto.class);
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> products = this.productRepo.findAll();
		List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return productDtos;
	}

	@Override
	public List<ProductDto> getproductsByCategory(Integer catId) {
		Category cat = this.categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", catId));
		List<Product> products = this.productRepo.findByCategory(cat);
		List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return  productDtos;
	}

	@Override
	public void deleteProduct(Integer productId) {
		Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
		this.productRepo.delete(product);
	}
	
	
	
	

}
