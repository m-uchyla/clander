package com.fdmgroup.clander.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.clander.models.Message;
import com.fdmgroup.clander.models.User;


public interface MessageRepository extends JpaRepository<Message, Long>{
	
	List<Message> findBySenderOrderByDateDesc(User sender);
	List<Message> findBySenderAndTypeOrderByDateDesc(User sender, String type);
	List<Message> findByReceiverOrderByDateDesc(User receiver);
	
}
