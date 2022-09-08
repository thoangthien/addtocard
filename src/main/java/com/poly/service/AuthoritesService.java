package com.poly.service;

import java.util.List;

import com.poly.entity.AccountRole;

public interface AuthoritesService {

	public List<AccountRole> findAuthoritiesOfAdministrators();

	List<AccountRole> findAll();

	AccountRole save(AccountRole accountRole);

	void delete(Integer id);

}
