package com.poly.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.entity.Role;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/roles")
public class RolesRestController {
	@Autowired
	com.poly.service.RoleService service;

	@GetMapping()
	public List<Role> get() {
		return service.findAll();
	}
}
