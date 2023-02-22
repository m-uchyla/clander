package com.fdmgroup.clander.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.websocket.AsyncChannelWrapperNonSecure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.clander.models.Filter;
import com.fdmgroup.clander.models.Message;
import com.fdmgroup.clander.models.User;
import com.fdmgroup.clander.repositories.FilterRepository;
import com.fdmgroup.clander.repositories.MessageRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class FilterServiceTest {

	@InjectMocks
	private FilterService filterService;
	private MessageService messageService;
	private UserService userService;
	
	@MockBean
	private FilterRepository filterRepository;
	
	@Mock
	private Filter mockFilter;
	private User mockUser;
	private Message mockMessage;
	
	private Long userID;
	private Long messageID;

	
	@BeforeEach
	public void setUp() {
	    userID = 1L;
	    messageID = 1L;
	    mockMessage = new Message();
	    mockMessage.setId(messageID);
	    mockUser = new User();
	    mockUser.setId(userID);
	  }
	
	
	@Test
	public void test_editFilter() {
		Filter currentFilter = new Filter();
		when(filterRepository.findByUser(mockUser)).thenReturn(Optional.of(currentFilter));
	    when(filterRepository.save(currentFilter)).thenReturn(currentFilter);
	    Filter result = filterService.editFilter(mockFilter, mockUser);
	    verify(filterRepository).findByUser(mockUser);
	    verify(filterRepository).save(currentFilter);
	    assertEquals(currentFilter, result);
	}
	
	
	@Test
	public void test_getUserFilter() {
		when(filterRepository.findByUser(mockUser)).thenReturn(Optional.of(mockFilter));
		Filter resultFilter = filterService.getUserFilter(mockUser);
		verify(filterRepository, times(2)).findByUser(mockUser);
		assertEquals(mockFilter, resultFilter);
	}
	
	
	@Test
	public void test_getClasses() {
		ArrayList<String> classes = new ArrayList<>();
	    classes.add("Hunter");
	    classes.add("Sorcerer");
	    when(filterRepository.findAllClasses()).thenReturn(classes);
	    ArrayList<String> result = filterService.getClasses();
		verify(filterRepository).findAllClasses();
		assertEquals(classes, result);
	} 
	
	@Test
	public void test_getRaces() {
		ArrayList<String> races = new ArrayList<>();
	    races.add("Orc");
	    races.add("Elf");
	    when(filterRepository.findAllRaces()).thenReturn(races);
	    ArrayList<String> result = filterService.getRaces();
		verify(filterRepository).findAllRaces();
		assertEquals(races, result);
	} 
	
	@Test
	public void test_getGames() {
		ArrayList<String> games = new ArrayList<>();
		games.add("LoL");
		games.add("Tibia");
	    when(filterRepository.findAllGames()).thenReturn(games);
	    ArrayList<String> result = filterService.getGames();
		verify(filterRepository).findAllGames();
		assertEquals(games, result);
	} 
	
	
	
}
