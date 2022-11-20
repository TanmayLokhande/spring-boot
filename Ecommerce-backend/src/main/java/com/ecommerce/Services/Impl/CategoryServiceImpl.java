package com.ecommerce.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Entities.Category;
import com.ecommerce.Exception.ResourceNotFoundException;
import com.ecommerce.Repositories.CategoryRepo;
import com.ecommerce.Services.CategoryService;
import com.ecommerce.payloads.CategoryDto;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		cat.setCatImage("default.png");
		Category catsaved = this.categoryRepo.save(cat);
		
		return this.modelMapper.map(catsaved, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId) {
		Category category = this.categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category","Id", catId));
		category.setCatName(categoryDto.getCatName());
		category.setCatImage(categoryDto.getCatImage());
		Category savedCat = this.categoryRepo.save(category);
		return this.modelMapper.map(savedCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer catId) {
		Category cat = this.categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", catId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategoryById(Integer catId) {
		Category cat = this.categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", catId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> catDtos = categories.stream().map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
	}

	
	
}
