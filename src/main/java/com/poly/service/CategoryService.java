package com.poly.service;

import java.util.List;

import com.poly.entity.Category;

public interface CategoryService {

	Category getById(String id);

	List<Category> findAll();

	Category create(Category category);

	Category update(Category category);

	void deleteById(String id);


}
