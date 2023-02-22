package com.fdmgroup.clander.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.clander.models.Filter;
import com.fdmgroup.clander.models.User;
import com.fdmgroup.clander.services.FilterService;
import com.fdmgroup.clander.services.UserService;

@Controller
public class FilterController {

	@Autowired
	FilterService filterService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/profile")
	public String getProfilePage(Model model) {
		if(userService.isBanned()) return "redirect:/logout";
		model.addAttribute("loggedUser", userService.getLoggedUser());
		model.addAttribute("filter", filterService.getUserFilter(userService.getLoggedUser()));
		model.addAttribute("games", filterService.getGames());
		model.addAttribute("classes", filterService.getClasses());
		model.addAttribute("races", filterService.getRaces());
		return "profilePage";
	}
	
	@PostMapping("/profile")
	public String profileEdit(@ModelAttribute User user,
			@RequestParam("characterLvlFromFilter") int characterLvlFrom,
			@RequestParam("characterLvlToFilter") int characterLvlTo,
			@RequestParam("gameFilter") String game,
			@RequestParam("characterRaceFilter") String characterRace,
			@RequestParam("characterClassFilter") String characterClass) {
		Filter filter = new Filter(null, game, characterLvlFrom, characterLvlTo, characterRace, characterClass, null);
		filterService.editFilter(filter, userService.getLoggedUser());
		userService.editProfile(user);
		return "redirect:/profile";
	}
	
	//
}
