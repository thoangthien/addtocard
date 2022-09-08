package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.CategoryDAO;
import com.poly.entity.Category;
import com.poly.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired CategoryDAO categoryDAO;

	@Override
	public List<Category> findAll() {
		return categoryDAO.findAll();
	}

	@Override
	public Category getById(String id) {
		return categoryDAO.getById(id);
	}

	@Override
	public Category create(Category category) {
		return categoryDAO.save(category);
	}

	@Override
	public Category update(Category category) {
		return categoryDAO.save(category);
	}

	@Override
	public void deleteById(String id) {
		categoryDAO.deleteById(id);
		
	}
	
	
	
}
