package com.fdmgroup.clander.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.clander.models.User;
import com.fdmgroup.clander.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserServiceTest{
	
	@InjectMocks
	private UserService mockUserService;
	
	@MockBean
	private UserRepository mockUserRepository;
	
	@Mock
	private UserService userService;
	private User mockUser;
	
	private Long userID;
	private String role;
	private List<User> expectedUsers;
	
	@BeforeEach
	public void setUp() {
	    userID = 1L;
	    mockUser = new User();
	    mockUser.setId(userID);
	  }

	
	@Test
	public void test_getAllUsers_expectSuccess(){
		mockUserService.getAllUsers();
		verify(mockUserRepository, times(1)).findAll();
	}

	@Test
	public void test_registerNewUser_expectSuccess() {
		User mockUser = new User();
		var save = mockUserRepository.save(mockUser);
		mockUser.setId(1L);
		mockUser.setPassword("pass1q");
		mockUser.setGame("Tibia");
		mockUser.setCharacterLvl(11);
		mockUser.setCharacterClass("hehe");
		mockUser.setCharacterRace("lol");
		mockUser.setDescription("psdaa");
		mockUser.setEmail("popo@popo.popo");
		mockUser.setUsername("mem");
		mockUser.setCharacterRole("admin");
		when(mockUserService.registerUser(mockUser)).thenReturn(mockUser);
		verify(mockUserRepository, times(1)).save(mockUser);
	}

	
	@Test
	public void test_getUserById_expectSuccess() {
		Long id = 1L;
		when(mockUserRepository.findById(id)).thenReturn(Optional.of(mockUser));
		User returnedUser = mockUserService.getUserById(id);
		verify(mockUserRepository, times(1)).findById(id);
		assertEquals(mockUser, returnedUser);
	}
	
	@Test
	public void test_getUserByRole_expectSuccess() {
		role = "ADMIN";
	    User user1 = new User();
	    user1.setDefaultRole();
	    User user2 = new User();
	    user2.setDefaultRole();
	    expectedUsers = Arrays.asList(user1, user2);

	    when(mockUserRepository.findByRole(role)).thenReturn(expectedUsers);
	    List<User> users = mockUserService.getUserByRole(role);

	    assertEquals(expectedUsers, users);
	}
	
	
	@Test
	public void test_getLoggedUser() {
		Optional<User> testUser = mockUserRepository.findByUsername(mockUser.getUsername());
		verify(mockUserRepository, times(1)).findByUsername(mockUser.getUsername());
	}
	
	@Test
	public void test_editProfile() {
	
	    mockUser.setGame("World of Warcraft");
	    mockUser.setDescription("Description");
	    mockUser.setCharacterLvl(100);
	    mockUser.setCharacterRace("Orc");
	    mockUser.setCharacterClass("Warrior");
	    mockUser.setCharacterRole("Tank");
	    
	    User editedUser = new User();
	    editedUser.setId(mockUser.getId());
	    editedUser.setGame(mockUser.getGame());
	    editedUser.setDescription(mockUser.getDescription());
	    editedUser.setCharacterLvl(mockUser.getCharacterLvl());
	    editedUser.setCharacterRace(mockUser.getCharacterRace());
	    editedUser.setCharacterClass(mockUser.getCharacterClass());
	    editedUser.setCharacterRole(mockUser.getCharacterRole());

	    assertEquals(editedUser, mockUser);
	  }
	
	
	@Test
	public void test_isProfileFilled_expectedSuccess() {
		User currentUser = new User();
		currentUser.setCharacterLvl(100);
		currentUser.setGame("World of Warcraft");
	    currentUser.setDescription("Description");
	    currentUser.setCharacterRace("Orc");
	    currentUser.setCharacterClass("Warrior");
	    currentUser.setCharacterRole("Tank");
	    
	    when(userService.getLoggedUser()).thenReturn(currentUser);
	    assertNotNull(currentUser);
	    assertEquals(true, mockUserService.isProfileFilled());


	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}