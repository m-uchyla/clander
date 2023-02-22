package com.fdmgroup.clander.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fdmgroup.clander.ClanderApplication;
import com.fdmgroup.clander.models.User;
import com.fdmgroup.clander.repositories.UserRepository;
import com.fdmgroup.clander.services.MessageService;
import com.fdmgroup.clander.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ClanderApplication.class)
@ActiveProfiles("test")
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	UserService userService;
	@MockBean
	UserRepository userRepository;
	@MockBean
	MessageService messageService;
	
//	@BeforeAll
//	public void setUp(UserService userService) {
//		this.userService = userService;
//	}
	
	@Test
	@WithMockUser
	public void test_displaySpecificUser() throws Exception{
		User loggedUser = new User();
		when(userService.isBanned()).thenReturn(false);
		when(userService.getLoggedUser()).thenReturn(loggedUser);
		when(userService.getUserById(1L)).thenReturn(loggedUser);
		
		mockMvc.perform(get("/user/?id=1"))
			.andExpect(model().attribute("loggedUser", loggedUser))
			.andExpect(model().attribute("user", loggedUser))
			.andExpect(status().isOk())
			.andExpect(view().name("eachUserProfile"));
		
		verify(userService, times(1)).getLoggedUser();
		verify(userService, times(1)).isBanned();
	}
	
	@Test
	@WithMockUser
	public void test_banUser() throws Exception{
		User loggedUser = new User();
		when(userService.isBanned()).thenReturn(false);
		when(userService.getLoggedUser()).thenReturn(loggedUser);
		when(userService.getUserById(1L)).thenReturn(loggedUser);
		
        mockMvc.perform(post("/ban")
                .param("id", "1")
                .param("messID", "10")
                .param("type", "PERM"))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/messages/received"));
		
		verify(messageService, times(1)).deleteMessage(10L);
		verify(userService, times(1)).banUser(1L, "PERM");
	}

}
