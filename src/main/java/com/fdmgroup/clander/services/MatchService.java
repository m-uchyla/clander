package com.fdmgroup.clander.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.clander.models.Filter;
import com.fdmgroup.clander.models.Match;
import com.fdmgroup.clander.models.MatchQueue;
import com.fdmgroup.clander.models.User;
import com.fdmgroup.clander.repositories.FilterRepository;
import com.fdmgroup.clander.repositories.MatchQueueRepository;
import com.fdmgroup.clander.repositories.MatchRepository;
import com.fdmgroup.clander.repositories.UserRepository;

@Service
public class MatchService {

	@Autowired
	MatchRepository matchRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	MatchQueueRepository matchQueueRepository;

	public Match match(User loggedUser, Long id) {
		Optional<User> optUser = userRepository.findById(id);
		if (optUser.isPresent()) {
			User pickedUser = optUser.get();
			Optional<MatchQueue> queue = matchQueueRepository.findByQueueOwnerAndLikedUser(pickedUser, loggedUser);
			if (queue.isPresent()) {
				matchQueueRepository.deleteById(queue.get().getId());
				return matchRepository.save(new Match(loggedUser, pickedUser));
			} else {
				matchQueueRepository.save(new MatchQueue(loggedUser, pickedUser));
				return null;
			}
		} else {
			return null;
		}
	}

	public Match decline(User user1, Long id) {
		Match match = new Match(user1, userRepository.findById(id).get());
		match.setIsMatched(false);
		return matchRepository.save(match);
	}

	public List<User> getMatchedAccountsList(User user) {
		return connectUserLists(matchRepository.findByUser1AndIsMatched(user, true),
				matchRepository.findByUser2AndIsMatched(user, true));
	}

	public List<User> getAllDecidedAccounts(User user) {
		List<User> matchedUserList = connectUserLists(matchRepository.findByUser1(user),
				matchRepository.findByUser2(user));
		for (MatchQueue queue : matchQueueRepository.findByQueueOwner(user))
			matchedUserList.add(queue.getLikedUser());
		return matchedUserList;
	}

	private List<User> connectUserLists(List<Match> user1List, List<Match> user2List) {
		List<User> usersList = new ArrayList<>();
		for (Match match : user1List)
			usersList.add(match.getUser2());
		for (Match match : user2List)
			usersList.add(match.getUser1());
		return usersList;
	}
}
