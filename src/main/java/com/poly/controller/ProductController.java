package com.poly.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.ProductDAO;
import com.poly.entity.Product;
import com.poly.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired ProductService productService;
	@Autowired ProductDAO productDAO;
	@Autowired HttpSession session;

	@RequestMapping("/product/list")
	public String list(Model model, @RequestParam("cid") Optional<String> cid, @RequestParam("page") Optional<Integer> page) {
		if (cid.isPresent()) {
			List<Product> list = productService.findByCid(cid.get());
			model.addAttribute("items", list);
		} else {
			List<Product> list = productService.findAll();
			model.addAttribute("items", list);
		}
		
		Pageable pageable = PageRequest.of(page.orElse(0), 6);
		Page<Product> pageProduct = productDAO.findAll(pageable);
		model.addAttribute("pageProduct", pageProduct);
		
		return "product/list";
	}
	
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product item = productService.findById(id);
		model.addAttribute("item",item);
		return "product/detail";
	}
}
