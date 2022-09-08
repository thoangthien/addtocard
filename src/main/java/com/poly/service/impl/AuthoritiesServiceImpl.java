package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.AccountDAO;
import com.poly.dao.AccountRoleDAO;
import com.poly.entity.Account;
import com.poly.entity.AccountRole;
import com.poly.service.AuthoritesService;

@Service
public class AuthoritiesServiceImpl implements AuthoritesService {
	@Autowired
	AccountDAO accountDAO;
	@Autowired
	AccountRoleDAO roleDAO;

	public List<AccountRole> findAuthoritiesOfAdministrators() {
		List<Account> accounts = accountDAO.findAdmin();
		System.out.println(roleDAO.authoritiesOf(accounts));
		return roleDAO.authoritiesOf(accounts);
	}

	@Override
	public List<AccountRole> findAll() {
		return roleDAO.findAll();
	}

	@Override
	public AccountRole save(AccountRole accountRole) {
		return roleDAO.save(accountRole);
	}

	@Override
	public void delete(Integer id) {
		roleDAO.deleteById(id);

	}

}
