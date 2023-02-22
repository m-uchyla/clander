package com.fdmgroup.clander.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.clander.models.User;
import com.fdmgroup.clander.services.MatchService;
import com.fdmgroup.clander.services.UserService;

@Controller
public class MatchController {

	@Autowired
	MatchService matchService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String getHomePage() {
		if(userService.isBanned()) return "redirect:/logout";
		if(userService.getLoggedUser() != null) return "redirect:/match";
		return "index";
	}
	
	@GetMapping("/match")
	public String getMatchPage(Model model) {
		if(userService.isBanned()) return "redirect:/logout";
		if(!userService.isProfileFilled())return "redirect:/profile";
		model.addAttribute("profiles",userService.getUsersOutsideOfTheList(matchService.getAllDecidedAccounts(userService.getLoggedUser())));
		return "matchingPage";
	}
	
	@PostMapping("/match-yes")
	public String matchUser(@RequestParam("profileID") Long profileID) {
		matchService.match(userService.getLoggedUser(),profileID);
		return "redirect:/match";
	}
	
	@PostMapping("/match-no")
	public String declineUser(@RequestParam("profileID") Long profileID) {
		matchService.decline(userService.getLoggedUser(),profileID);
		return "redirect:/match";
	}
	
	@GetMapping("/myMatches")
	public String getAllMatchedUser(Model model, User user) {
		if(userService.isBanned()) return "redirect:/logout";
		model.addAttribute("allMatchedUsers",matchService.getMatchedAccountsList(userService.getLoggedUser()));
		return "myMatches";
	}
	
}
