package com.fdmgroup.clander.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fdmgroup.clander.models.Match;
import com.fdmgroup.clander.models.User;

public interface MatchRepository extends JpaRepository<Match, Long> {

	List<Match> findByUser1(User user1);
	List<Match> findByUser2(User user2);
	
	List<Match> findByUser1AndIsMatched(User user1, Boolean isMatched);
	List<Match> findByUser2AndIsMatched(User user2, Boolean isMatched);
}
