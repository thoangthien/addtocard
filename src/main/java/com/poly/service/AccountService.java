package com.poly.service;

import java.util.List;
import com.poly.entity.Account;

public interface AccountService {

	Account findById(String username);

	List<Account> findAll();

	List<Account> getAdmin();

	Account create(Account account);

	Account update(Account account);

	void deleteById(String username);
}
