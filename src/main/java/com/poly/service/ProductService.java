package com.poly.service;

import java.util.List;
import com.poly.entity.Product;

public interface ProductService {

	Product findById(Integer id);

	List<Product> findAll();

	List<Product> findByCid(String cid);

	Product create(Product product);

	Product update(Product product);

	void deleteById(Integer id);
}
