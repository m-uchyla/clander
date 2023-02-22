package com.fdmgroup.clander.repositories;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fdmgroup.clander.models.Filter;
import com.fdmgroup.clander.models.User;

public interface FilterRepository extends JpaRepository<Filter, Long> {

	Optional<Filter> findByUser(User user);
	//Selecting the right column
	@Query("SELECT DISTINCT c.game FROM User c")
	public ArrayList<String> findAllGames();
	
	@Query("SELECT DISTINCT c.characterRace FROM User c")
	public ArrayList<String> findAllRaces();
	
	@Query("SELECT DISTINCT c.characterClass FROM User c")
	public ArrayList<String> findAllClasses();
}
