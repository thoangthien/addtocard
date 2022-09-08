package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Category;
import com.poly.service.CategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/categories")
public class CategoryRestController {

	@Autowired CategoryService categoryService;
//
//	@GetMapping("/rest/categories")
//	public List<Category> getAll() {
//		return categoryService.findAll();
//	}


	@GetMapping("{id}")
	public Category getOne(@PathVariable("id") String id) {
		return categoryService.getById(id);
	}

	@GetMapping()
	public List<Category> getList() {
		return categoryService.findAll();
	}

	@PostMapping()
	public Category save(@RequestBody Category category) {
		return categoryService.create(category);
	}

	@PutMapping("{id}")
	public Category put(@PathVariable("id") Integer id, @RequestBody Category category) {
		return categoryService.update(category);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") String id) {
		categoryService.deleteById(id);
	}
}
