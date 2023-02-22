package com.fdmgroup.clander.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.clander.models.Message;
import com.fdmgroup.clander.models.User;
import com.fdmgroup.clander.repositories.MessageRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	UserService userService;
	
	
	public List<Message> getReceiverMessages(User user){
		return messageRepository.findByReceiverOrderByDateDesc(user);
	}
	
	public List<Message> getSenderMessages(User user){
		return messageRepository.findBySenderAndTypeOrderByDateDesc(user,"MESSAGE");
	}
	
	public void createNewTextMessage(Message message) {
		messageRepository.save(message);
	}
	
	public void sendReport(Long id, User sender) {
		User reportedUser = userService.getUserById(id);
		List<User> adminList = userService.getUserByRole("ROLE_ADMIN");
		for(User admin : adminList) {
			Message message = (new Message(sender, ("[REPORT] "+
					sender.getUsername())+" reported user: "+reportedUser.getUsername()+
					" with ID="+reportedUser.getId(), 
					admin));
			message.markAsReport();
			messageRepository.save(message);
		}
	}
	
	public Message getMessageById(Long id) {
		return messageRepository.findById(id).get();
	}

	public void deleteMessage(Long messageID) {
		messageRepository.deleteById(messageID);
	}
}
