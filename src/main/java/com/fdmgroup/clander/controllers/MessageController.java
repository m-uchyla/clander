package com.fdmgroup.clander.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.clander.models.Message;
import com.fdmgroup.clander.services.MatchService;
import com.fdmgroup.clander.services.MessageService;
import com.fdmgroup.clander.services.UserService;

@Controller
public class MessageController {

	@Autowired
	MessageService messageService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MatchService matchService;

	@GetMapping("/messages/received")
	public String allMessagesReceived(Model model) {
		if(userService.isBanned()) return "redirect:/logout";
		model.addAttribute("allMessagesReceived", messageService.getReceiverMessages(userService.getLoggedUser()));
		return "myMessages";
	}
	
	@GetMapping("/messages/sent")
	public String allMessagesSent(Model model) {
		if(userService.isBanned()) return "redirect:/logout";
		model.addAttribute("allMessagesSent", messageService.getSenderMessages(userService.getLoggedUser()));
		return "myMessages";
	}
	
	@GetMapping("/message/received")
	public String messageReceivedById(@RequestParam("id") Long msgID,Model model) {
		if(userService.isBanned()) return "redirect:/logout";
		model.addAttribute("allMessagesReceived", messageService.getReceiverMessages(userService.getLoggedUser()));
		model.addAttribute("selectedMsg",messageService.getMessageById(msgID));
		if(messageService.getMessageById(msgID).getContent().contains("[REPORT]")){
			model.addAttribute("reportedID",messageService.getMessageById(msgID).getContent().split("=")[1]);
		}else {
			model.addAttribute("reportedID",null);
		}
		
		return "myMessages";
	}
	
	@GetMapping("/message/sent")
	public String messageSentById(@RequestParam("id") Long msgID,Model model) {
		if(userService.isBanned()) return "redirect:/logout";
		model.addAttribute("allMessagesSent", messageService.getSenderMessages(userService.getLoggedUser()));
		model.addAttribute("selectedMsg",messageService.getMessageById(msgID));
		return "myMessages";
	}
	
	@GetMapping("/newMessage")
	public String createMessageView(Model model) {
		if(userService.isBanned()) return "redirect:/logout";
		model.addAttribute("allMessagesSent", messageService.getSenderMessages(userService.getLoggedUser()));
		model.addAttribute("matchedUsers",matchService.getMatchedAccountsList(userService.getLoggedUser()));
		return "myMessages";
	}
	
	@PostMapping("/newMessage")
	public String createMessage(@ModelAttribute Message message) {
		message.setSender(userService.getLoggedUser());
		messageService.createNewTextMessage(message);
		return "redirect:/messages/sent";
	}
	
	@PostMapping("/report")
	public String reportPlayer(@RequestParam("profileID") Long profileID) {
		messageService.sendReport(profileID, userService.getLoggedUser());
		return "redirect:/match";
	}
	
	@PostMapping("/deleteMessage")
	public String deleteMessage(@RequestParam("id") Long messageID) {
		messageService.deleteMessage(messageID);
		return "redirect:/messages/received";
	}

}
