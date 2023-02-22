package com.fdmgroup.clander.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import com.fdmgroup.clander.models.Match;
import com.fdmgroup.clander.models.User;
import com.fdmgroup.clander.repositories.MatchRepository;
import com.fdmgroup.clander.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class MatchServiceTest {

	@InjectMocks
	private MatchService matchService;
	
	@MockBean
	private MatchRepository matchRepository;
	
	@MockBean
	private UserRepository userRepository;
	
	@Mock
	private User mockUser1;
	@Mock
	private User mockUser2;
	@Mock
	private Match mockMatch;
	
	private Long userID1;
	private Long userID2;
	private List<User> matches;
	
	@BeforeEach
	public void setUp() {
		matches = new ArrayList<>();
	    userID1 = 1L;
	    userID2 = 2L;
	    mockMatch = new Match();
	    mockUser1 = new User();
	    mockUser2 = new User();
	    mockUser1.setId(userID1);
	    mockUser2.setId(userID2);
	  }
	
	
	@Test
	public void test_decline() {
		User mockUser1 = new User();
		User mockUser2 = new User();
		mockUser2.setId(userID2);
		Match mockMatch1 = matchService.decline(mockUser1, mockUser2.getId());
		mockMatch1.setIsMatched(false);
		matchRepository.save(mockMatch1);
		
		verify(matchRepository.save(mockMatch1));
	}
	@Test
	public void test_getMatchedAccountsList() {
		User mockUser3 = new User();
		Match match1 = new Match(mockUser1, mockUser2);
		match1.setIsMatched(true);
		matchRepository.findByUser1AndIsMatched(mockUser1, true);
		matchRepository.save(match1);
		
		Match match2 = new Match(mockUser1, mockUser3);
		match2.setIsMatched(true);
		matchRepository.findByUser2AndIsMatched(mockUser2, true);
		matchRepository.save(match2);
		
		when(matchService.getMatchedAccountsList(mockUser1)).thenReturn(matches);

		assertTrue(matches.contains(mockUser2));
		assertTrue(matches.contains(mockUser3));
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
