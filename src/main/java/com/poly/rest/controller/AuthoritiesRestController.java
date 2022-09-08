package com.poly.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.poly.entity.AccountRole;
import com.poly.service.AuthoritesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/authorities")
public class AuthoritiesRestController {

	@Autowired
	AuthoritesService service;

	@GetMapping
	public List<AccountRole> findAll(@RequestParam("admins") Optional<Boolean> admin) {
		if (admin.orElse(false)) {
			return service.findAuthoritiesOfAdministrators();
		}
		return service.findAll();
	}

	@PostMapping
	public AccountRole post(@RequestBody AccountRole accountRole) {
		return service.save(accountRole);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		service.delete(id);
	}
}
