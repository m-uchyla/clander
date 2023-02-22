package com.fdmgroup.clander.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.clander.models.User;
import com.fdmgroup.clander.repositories.UserRepository;
import com.fdmgroup.clander.services.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/login")
	public String getLoginPage() {return "loginPage";}
	
	
	@GetMapping("/register")
	public String getRegisterPage() {return "registerPage";}
	
	
	@PostMapping("/register")
	public String registerNewUser(@ModelAttribute User user) {
		userService.registerUser(user);
		return "redirect:/auth/login";
	}
	
	
	@GetMapping("/change-pass")
	public String getChangePasswordPage() {return "changePass";}
	
	
	@PostMapping("/change-pass")
	public String changePassword(
			@RequestParam("username") String username,
			@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword) {
		
		User user = userService.loadUserByUsername(username).getUser();
		if(user == null || !(user.getPassword().equals(oldPassword)))return "redirect:/auth/change-pass";
		user.setPassword(newPassword);
		userService.save(user);
		
		return "redirect:/auth/login";
	}

}
