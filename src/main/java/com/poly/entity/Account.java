package com.poly.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="Accounts", uniqueConstraints = {
		@UniqueConstraint(name="email_account" ,columnNames = {"email"})
})
public class Account implements Serializable{
	
	@Id
	private String username;
	private String password;
	private String fullname;
	private String email;
	private String photo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<AccountRole> authority;
	
}
