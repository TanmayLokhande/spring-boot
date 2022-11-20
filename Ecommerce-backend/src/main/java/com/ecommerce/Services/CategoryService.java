package com.ecommerce.Services;

import java.util.List;

import com.ecommerce.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto,Integer catId);
	
	void deleteCategory(Integer catId);
	
	CategoryDto getCategoryById(Integer catId);
	
	List<CategoryDto> getAllCategories();
	
}
