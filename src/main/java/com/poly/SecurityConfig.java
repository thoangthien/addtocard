package com.poly;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.poly.service.UserService;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserService userService;

	//Mã hóa mật khẩu
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	//quản lý dữ liệu user
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	protected void configure(HttpSecurity http) throws Exception{

		http.csrf().disable();
		
		//phân quyền sử dụng
		http.authorizeRequests()
		.antMatchers("/order/**").authenticated()
		.antMatchers("/admin/**").hasAnyRole("STAF","DIRE")
		.antMatchers("/rest/authorities").hasRole("DIRE")
		.anyRequest().permitAll();
		
		http.formLogin()
		.loginPage("/security/login/form")
		.loginProcessingUrl("/security/login")
		.defaultSuccessUrl("/security/login/success", false)
		.failureUrl("/security/login/error");
		
		http.rememberMe()
		.tokenValiditySeconds(86400);
		
		http.exceptionHandling()
		.accessDeniedPage("/security/unauthoried");
		
		http.logout()
		.logoutUrl("/security/logoff")
		.logoutSuccessUrl("/security/logoff/success");
		
		http.oauth2Login()
		.loginPage("/auth/login/form")
		.defaultSuccessUrl("/oauth2/login/success", false)
		.failureUrl("/oauth2/login.error")
		.authorizationEndpoint()
		.baseUri("/oauth2/authorization");
	}

}
