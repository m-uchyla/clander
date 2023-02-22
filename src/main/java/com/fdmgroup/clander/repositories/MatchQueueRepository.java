package com.fdmgroup.clander.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fdmgroup.clander.models.MatchQueue;
import com.fdmgroup.clander.models.User;

public interface MatchQueueRepository extends JpaRepository<MatchQueue, Long> {

	List<MatchQueue> findByQueueOwner(User queueOwner);
	List<MatchQueue> findByLikedUser(User likedUser);
	Optional<MatchQueue> findByQueueOwnerAndLikedUser(User queueOwner, User likedUser);
}
