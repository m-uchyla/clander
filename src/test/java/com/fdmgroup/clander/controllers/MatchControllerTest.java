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
public class MatchControllerTest {

	@Autowired
	private MockMvc mockMvc;
	

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
	public void test_getHomePage() throws Exception{
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("index"));
	}
	
	@Test
	@WithMockUser
	public void test_getMatchPage() throws Exception{
		User loggedUser = new User();
		List<User> userList = new ArrayList<>();
		userList.add(loggedUser);
		when(userService.isProfileFilled()).thenReturn(true);
		when(userService.getLoggedUser()).thenReturn(loggedUser);
		when(matchService.getAllDecidedAccounts(loggedUser)).thenReturn(userList);
		when(userService.getUsersOutsideOfTheList(userList)).thenReturn(userList);
		
		mockMvc.perform(get("/match"))
			.andExpect(model().attribute("profiles", userList))
			.andExpect(status().isOk())
			.andExpect(view().name("matchingPage"));
		
		verify(matchService, times(1)).getAllDecidedAccounts(loggedUser);
		verify(userService, times(1)).getUsersOutsideOfTheList(userList);
	}
	
	@Test
	@WithMockUser
	public void test_matchUser() throws Exception{
		Long idLong = 5L;
		User loggedUser = new User();
		loggedUser.setId(idLong);
		when(userService.getLoggedUser()).thenReturn(loggedUser);
		
        mockMvc.perform(post("/match-yes")
        		.param("profileID", idLong.toString()))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/match"));
		
		verify(matchService, times(1)).match(loggedUser,idLong);
	}
	
	@Test
	@WithMockUser
	public void test_declineUser() throws Exception{
		Long idLong = 5L;
		User loggedUser = new User();
		loggedUser.setId(idLong);
		when(userService.getLoggedUser()).thenReturn(loggedUser);
		
        mockMvc.perform(post("/match-no")
        		.param("profileID", idLong.toString()))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/match"));
		
		verify(matchService, times(1)).decline(loggedUser,idLong);
	}
	
	@Test
	@WithMockUser
	public void test_getAllMatchedUser() throws Exception{
		User loggedUser = new User();
		List<User> userList = new ArrayList<>();
		userList.add(loggedUser);
		when(userService.getLoggedUser()).thenReturn(loggedUser);
		when(matchService.getMatchedAccountsList(loggedUser)).thenReturn(userList);
		
		mockMvc.perform(get("/myMatches"))
			.andExpect(model().attribute("allMatchedUsers", userList))
			.andExpect(status().isOk())
			.andExpect(view().name("myMatches"));
		
		verify(matchService, times(1)).getMatchedAccountsList(loggedUser);
	}
}
