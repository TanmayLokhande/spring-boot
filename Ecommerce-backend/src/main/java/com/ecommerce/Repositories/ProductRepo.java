package com.ecommerce.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.Entities.Category;
import com.ecommerce.Entities.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

	List<Product> findByCategory(Category category);
	
}
