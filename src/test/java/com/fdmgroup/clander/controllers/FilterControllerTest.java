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
import com.fdmgroup.clander.models.Filter;
import com.fdmgroup.clander.models.User;
import com.fdmgroup.clander.services.FilterService;
import com.fdmgroup.clander.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ClanderApplication.class)
@ActiveProfiles("test")
public class FilterControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	

	@MockBean
	UserService userService;
	@MockBean
	FilterService filterService;
	
	@BeforeEach
	public void setUp() {
		when(userService.isBanned()).thenReturn(false);
	}
	
	@Test
	@WithMockUser
	public void test_getProfilePage() throws Exception{
		User loggedUser = new User();
		ArrayList<String> list = new ArrayList<String>();
		list.add("Test string");
		Filter filter = new Filter();
		when(userService.getLoggedUser()).thenReturn(loggedUser);
		when(filterService.getUserFilter(loggedUser)).thenReturn(filter);
		when(filterService.getGames()).thenReturn(list);
		when(filterService.getClasses()).thenReturn(list);
		when(filterService.getRaces()).thenReturn(list);
		
		mockMvc.perform(get("/profile"))
			.andExpect(model().attribute("loggedUser", loggedUser))
			.andExpect(model().attribute("filter", filter))
			.andExpect(model().attribute("games", list))
			.andExpect(model().attribute("classes", list))
			.andExpect(model().attribute("races", list))
			.andExpect(status().isOk())
			.andExpect(view().name("profilePage"));
		
		verify(filterService, times(1)).getUserFilter(loggedUser);
		verify(userService, times(2)).getLoggedUser();
		verify(filterService, times(1)).getGames();
		verify(filterService, times(1)).getClasses();
		verify(filterService, times(1)).getRaces();
	}
	
	@Test
	@WithMockUser
	public void test_profileEdit() throws Exception{
		Long idLong = 5L;
		User loggedUser = new User();
		loggedUser.setId(idLong);
		when(userService.getLoggedUser()).thenReturn(loggedUser);
		Filter filter = new Filter(null, "TestGameFilter", 10, 70, "TestRaceFilter", "TestClassFilter", null);
		
        mockMvc.perform(post("/profile")
        		.param("username", "Testusername")
        		.param("characterLvl", "100")
        		.param("game", "TestGame")
        		.param("characterRace", "TestRace")
        		.param("characterClass", "TestClass")
        		.param("description", "TestDesc")
        		.param("characterLvlFromFilter", "10")
        		.param("characterLvlToFilter", "70")
        		.param("gameFilter", "TestGameFilter")
        		.param("characterRaceFilter", "TestRaceFilter")
        		.param("characterClassFilter", "TestClassFilter"))
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/profile"));
		
        verify(userService, times(1)).getLoggedUser();
        verify(filterService, times(1)).editFilter(filter, loggedUser);
	}

}
