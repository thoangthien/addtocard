package com.poly.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.dao.OrderDAO;
import com.poly.dao.OrderDetailDAO;
import com.poly.entity.Order;
import com.poly.entity.OrderDetails;
import com.poly.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDAO orderDAO;

	@Autowired
	OrderDetailDAO detailDAO;

	@Override
	public Order create(JsonNode node) {

		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.convertValue(node, Order.class);
		orderDAO.save(order);

		TypeReference<List<OrderDetails>> type = new TypeReference<List<OrderDetails>>() {
		};
		List<OrderDetails> details = mapper.convertValue(node.get("orderdetails"), type).stream()
				.peek(d -> d.setOrder(order)).collect(Collectors.toList());
		detailDAO.saveAll(details);
		return order;
	}

	@Override
	public Order findById(Long id) {
		return orderDAO.findById(id).get();
	}

	@Override
	public List<Order> findByUsername(String username) {
		return orderDAO.findByUsername(username);
	}
}
