package com.poly.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.entity.Order;
import com.poly.service.OrderService;

@RestController
@CrossOrigin("*")

public class OrderRestController {

	@Autowired
	OrderService orderService;

	@PostMapping("/rest/orders")
	public Order create(@RequestBody JsonNode node) {
		return orderService.create(node);
	}
}
