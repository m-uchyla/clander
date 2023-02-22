package com.fdmgroup.clander.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.clander.models.User;
import com.fdmgroup.clander.models.Message;
import com.fdmgroup.clander.repositories.MessageRepository;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class MessageServiceTest{
	
	
	@InjectMocks
	private MessageService messageService;
	private UserService userService;
	
	@MockBean
	private MessageRepository messageRepository;
	
	@Mock
	private User mockUser;
	private Message mockMessage;
	
	private Long userID;
	private Long messageID;
	private List<Message> expectedMessages;
	
	@BeforeEach
	public void setUp() {
		expectedMessages = new ArrayList<>();
	    userID = 1L;
	    messageID = 1L;
	    mockMessage = new Message();
	    mockMessage.setReceiver(mockUser);
	    mockUser = new User();
	    mockUser.setId(userID);
	  }
	
	@Test
	public void test_getReceiverMessages(){
		User receiverUser = new User();
		List<Message> result = messageService.getReceiverMessages(mockUser);
		when(messageRepository.findByReceiverOrderByDateDesc(mockUser)).thenReturn(expectedMessages);
	    assertEquals(expectedMessages, result);
	}
	
	@Test
	public void test_createNewMessage() {
		messageService.createNewTextMessage(mockMessage);
		verify(messageRepository, times(1)).save(mockMessage);
		
	}
	
	@Test
	public void test_getMessageById(){
		when(messageRepository.findById(messageID)).thenReturn(Optional.of(mockMessage));
	    Message result = messageService.getMessageById(messageID);
	    assertEquals(mockMessage, result);
	}
	
	@Test
	public void test_deleteMessage() {
		messageService.deleteMessage(messageID);
		verify(messageRepository).deleteById(messageID);
	}
	
	@Test
	public void test_sendReport() {
		User reportedUser = new User();
		reportedUser.setId(userID);
		User sender = new User();
		User admin = new User();
		admin.setRole("ROLE_ADMIN");
		List<User> adminList = new ArrayList<>();
		Message message = new Message(sender, "test subject", admin);
		
		when(userService.getUserById(userID)).thenReturn(reportedUser);
	    when(userService.getUserByRole("ROLE_ADMIN")).thenReturn(adminList);
	    when(messageRepository.save(message)).thenReturn(message);
	    messageService.sendReport(messageID, sender);
	    verify(messageRepository).save(message);
	    assertEquals(sender, message.getSender());
	    assertEquals(admin, message.getReceiver());
	    assertEquals("[REPORT] " + sender.getUsername() + " reported user: " + reportedUser.getUsername() + " with ID=" + reportedUser.getId(), message.getContent());

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}