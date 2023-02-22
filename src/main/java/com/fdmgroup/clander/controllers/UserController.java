package com.fdmgroup.clander.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.clander.models.User;
import com.fdmgroup.clander.services.MessageService;
import com.fdmgroup.clander.services.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	MessageService messageService;
	
	@GetMapping("/user")
	public String getUsersList(@RequestParam("id") Long userID,Model model) {
		if(userService.isBanned()) return "redirect:/logout";
		model.addAttribute("loggedUser", userService.getLoggedUser());
		model.addAttribute("user",userService.getUserById(userID));
		return "eachUserProfile";
	}
	
	@PostMapping("/ban")
	public String banUser(@RequestParam("id") Long userID,@RequestParam("messID") Long messID,@RequestParam("type") String type) {
		messageService.deleteMessage(messID);
		userService.banUser(userID,type);
		return "redirect:/messages/received";
	}

}
