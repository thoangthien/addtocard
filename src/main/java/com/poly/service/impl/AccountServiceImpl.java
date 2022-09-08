package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.dao.AccountDAO;
import com.poly.entity.Account;
import com.poly.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDAO accountDAO;

	@Override
	public List<Account> findAll() {
		return accountDAO.findAll();
	}

	@Override
	public Account findById(String username) {
		return accountDAO.findById(username).get();
	}

	@Override
	public List<Account> getAdmin() {
		return accountDAO.findAdmin();
	}

	@Override
	public Account create(Account account) {
		return accountDAO.save(account);
	}

	@Override
	public Account update(Account account) {
		return accountDAO.save(account);
	}

	@Override
	public void deleteById(String username) {
		accountDAO.deleteById(username);
		
	}
}
