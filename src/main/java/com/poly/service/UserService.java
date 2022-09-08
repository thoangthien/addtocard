package com.poly.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poly.dao.AccountDAO;
import com.poly.entity.Account;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountDAO accountDAO;
	
	@Autowired
	BCryptPasswordEncoder pe;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			try {
				Account account = accountDAO.findById(username).get();
				
				String password = account.getPassword();
				String[] roles = account.getAuthority().stream()
						.map(au -> au.getRole().getId())
						.collect(Collectors.toList()).toArray(new String[0]);
				return User.withUsername(username)
						.password(pe.encode(password))
						.roles(roles).build();
			} catch (Exception e) {
				throw new UsernameNotFoundException(username);
			}
	}
	
	public void loginFromOAuth2(OAuth2AuthenticationToken token) {
//		String name = token.getPrincipal().getAttribute("name");
		String email = token.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis());
		
		UserDetails userDetails = User.withUsername(email)
				.password(pe.encode(password)).roles("CUST").build();
		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
}
