package com.fdmgroup.clander.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.clander.ClanderApplication;
import com.fdmgroup.clander.models.Message;
import com.fdmgroup.clander.models.User;
import com.fdmgroup.clander.services.MatchService;
import com.fdmgroup.clander.services.MessageService;
import com.fdmgroup.clander.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ClanderApplication.class)
@ActiveProfiles("test")
public class MessageControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	MessageService messageService;
	@MockBean
	UserService userService;
	@MockBean
	MatchService matchService;
	
	@BeforeEach
	public void setUp() {
		when(userService.isBanned()).thenReturn(false);
	}
	
	@Test
	@WithMockUser
	public void test_allMessagesReceived() throws Exception {
		User loggedUser = new User();
		List<Message> messList = new ArrayList<>();
		when(userService.getLoggedUser()).thenReturn(loggedUser);
		when(messageService.getReceiverMessages(loggedUser)).thenReturn(messList);
		
		mockMvc.perform(get("/messages/received"))
			.andExpect(model().attribute("allMessagesReceived", messList))
			.andExpect(status().isOk())
			.andExpect(view().name("myMessages"));
		
		verify(messageService, times(1)).getReceiverMessages(loggedUser);
	}	
	
	@Test
	@WithMockUser
	public void test_allMessagesSent() throws Exception {
		User loggedUser = new User();
		List<Message> messList = new ArrayList<>();
		when(userService.getLoggedUser()).thenReturn(loggedUser);
		when(messageService.getSenderMessages(loggedUser)).thenReturn(messList);
		
		mockMvc.perform(get("/messages/sent"))
			.andExpect(model().attribute("allMessagesSent", messList))
			.andExpect(status().isOk())
			.andExpect(view().name("myMessages"));
		
		verify(messageService, times(1)).getSenderMessages(loggedUser);
	}	
	
	@Test
	@WithMockUser
	public void test_messageReceivedById() throws Exception {
		Long idLong = (long) 5;
		User loggedUser = new User();
		Message message = new Message();
		message.setContent("[REPORT]id="+idLong);
		List<Message> messList = new ArrayList<>();
		messList.add(message);
		when(userService.getLoggedUser()).thenReturn(loggedUser);
		when(messageService.getReceiverMessages(loggedUser)).thenReturn(messList);
		when(messageService.getMessageById(idLong)).thenReturn(message);
		//when(messageService.getMessageById(idLong).getContent().split("=")[1]).thenReturn(idLong.toString());
		
		mockMvc.perform(get("/message/received/?id=5"))
			.andExpect(model().attribute("allMessagesReceived", messList))
			.andExpect(model().attribute("selectedMsg", message))
			.andExpect(model().attribute("reportedID", idLong.toString()))
			.andExpect(status().isOk())
			.andExpect(view().name("myMessages"));
		
		verify(messageService, times(1)).getReceiverMessages(loggedUser);
		verify(messageService, times(3)).getMessageById(idLong);
	}	
	
	@Test
	@WithMockUser
	public void test_messageSentById() throws Exception {
		Long idLong = (long) 5;
		User loggedUser = new User();
		Message message = new Message();
		message.setContent("id="+idLong);
		List<Message> messList = new ArrayList<>();
		messList.add(message);
		when(userService.getLoggedUser()).thenReturn(loggedUser);
		when(messageService.getSenderMessages(loggedUser)).thenReturn(messList);
		when(messageService.getMessageById(1L)).thenReturn(message);
		
		mockMvc.perform(get("/message/sent/?id=1"))
			.andExpect(model().attribute("allMessagesSent", messList))
			.andExpect(model().attribute("selectedMsg", message))
			.andExpect(status().isOk())
			.andExpect(view().name("myMessages"));
		
		verify(messageService, times(1)).getSenderMessages(loggedUser);
		verify(messageService, times(1)).getMessageById(1L);
	}	
	
	@Test
	@WithMockUser
	public void test_createMessageView() throws Exception {
		Long idLong = (long) 5;
		User loggedUser = new User();
		Message message = new Message();
		message.setContent("id="+idLong);
		List<Message> messList = new ArrayList<>();
		List<User> usersList = new ArrayList<>();
		usersList.add(loggedUser);
		messList.add(message);
		when(userService.getLoggedUser()).thenReturn(loggedUser);
		when(messageService.getSenderMessages(loggedUser)).thenReturn(messList);
		when(matchService.getMatchedAccountsList(loggedUser)).thenReturn(usersList);
		
		mockMvc.perform(get("/newMessage"))
			.andExpect(model().attribute("allMessagesSent", messList))
			.andExpect(model().attribute("matchedUsers", usersList))
			.andExpect(status().isOk())
			.andExpect(view().name("myMessages"));
		
		verify(messageService, times(1)).getSenderMessages(loggedUser);
		verify(matchService, times(1)).getMatchedAccountsList(loggedUser);
	}	
	
	@Test
	@WithMockUser
	public void test_createMessage() throws Exception {
		Long idLong = 5L;
		User loggedUser = new User();
		loggedUser.setId(idLong);
		Message mess = new Message(loggedUser, "test", loggedUser);
		when(userService.getLoggedUser()).thenReturn(loggedUser);
		
        mockMvc.perform(post("/newMessage")
        		.flashAttr("message", mess))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/messages/sent"));
		
		verify(messageService, times(1)).createNewTextMessage(mess);
	}	
	
	@Test
	@WithMockUser
	public void test_report() throws Exception {
		Long idLong = 5L;
		User loggedUser = new User();
		loggedUser.setId(idLong);
		when(userService.getLoggedUser()).thenReturn(loggedUser);
		
        mockMvc.perform(post("/report")
        		.param("profileID", idLong.toString()))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/match"));
		
		verify(messageService, times(1)).sendReport(idLong,loggedUser);
	}	
	
	@Test
	@WithMockUser
	public void test_deleteMessage() throws Exception {
		Long idLong = 5L;
		
        mockMvc.perform(post("/deleteMessage")
        		.param("id", idLong.toString()))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/messages/received"));
		
		verify(messageService, times(1)).deleteMessage(idLong);
	}
	
	

}
