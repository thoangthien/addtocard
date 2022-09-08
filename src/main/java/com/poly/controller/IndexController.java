package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.dao.ProductDAO;
import com.poly.entity.Product;

@Controller
public class IndexController {

	@Autowired
	ProductDAO productDAO;

	@RequestMapping("/index")
	public String index() {
		return "/product/home";
	}

	@RequestMapping({ "/admin", "/admin/home/index" })
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}

	@ModelAttribute("ProductNew")
	public List<Product> getProductIndex() {
		Page<Product> page = productDAO.findAll(PageRequest.of(0, 8, Sort.by(Direction.DESC, "createdate")));
		return page.getContent();
	}

	@RequestMapping("/contact")
	public String contact() {
		return "/product/contact";
	}
}
