package com.poly.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String>{

	
}
