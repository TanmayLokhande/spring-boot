package com.ecommerce.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.Entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
