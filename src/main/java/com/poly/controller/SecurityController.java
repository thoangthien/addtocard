package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.service.UserService;

@Controller
public class SecurityController {
	@Autowired
	UserService userService;

	@RequestMapping("/security/login/form")
	public String login(Model model) {
		model.addAttribute("mess", "Chưa đăng nhập");
		return "security/login";
	}

	@RequestMapping("/security/login/success")
	public String success(Model model) {
		model.addAttribute("mess", "Đăng nhập thành công");
		return "redirect:/index";
	}

	@RequestMapping("/security/login/error")
	public String error(Model model) {
		model.addAttribute("mess", "Đăng nhập không thành công");
		return "security/login";
	}

	@RequestMapping("/security/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("mess", "Không có quyền");
		return "security/login";
	}

	@RequestMapping("/security/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("mess", "Đã đăng xuất");
		return "redirect:/index";
	}

	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken token) {
		userService.loginFromOAuth2(token);
		return "forward:/index";
	}
	
	
	@RequestMapping("/security/register")
	public String register() {
		return "security/register";
	}
}
