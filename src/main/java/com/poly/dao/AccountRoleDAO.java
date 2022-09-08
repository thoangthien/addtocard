package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Account;
import com.poly.entity.AccountRole;

public interface AccountRoleDAO extends JpaRepository<AccountRole, Integer> {
	@Query("SELECT DISTINCT a FROM AccountRole a WHERE a.account IN ?1")
	List<AccountRole> authoritiesOf(List<Account> accounts);

}
