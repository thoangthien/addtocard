package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.ProductDAO;
import com.poly.entity.Product;
import com.poly.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;

	@Override
	public List<Product> findAll() {
		return productDAO.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return productDAO.findById(id).get();
	}

	@Override
	public List<Product> findByCid(String cid) {
		return productDAO.findByCid(cid);
	}

	@Override
	public Product create(Product product) {
		return productDAO.save(product);
	}

	@Override
	public Product update(Product product) {
		return productDAO.save(product);
	}

	@Override
	public void deleteById(Integer id) {
		productDAO.deleteById(id);
	}

}
