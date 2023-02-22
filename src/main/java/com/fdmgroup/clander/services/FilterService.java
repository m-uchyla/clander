package com.fdmgroup.clander.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.clander.models.Filter;
import com.fdmgroup.clander.models.User;
import com.fdmgroup.clander.repositories.FilterRepository;

@Service
public class FilterService {

	@Autowired
	FilterRepository filterRepository;
	
	public Filter editFilter(Filter filter, User user) {
		Filter currentFilter = filterRepository.findByUser(user).get();
		currentFilter.setGame(filter.getGame());
		currentFilter.setCharacterClass(filter.getCharacterClass());
		currentFilter.setCharacterRace(filter.getCharacterRace());
		currentFilter.setCharacterLvlFrom(filter.getCharacterLvlFrom());
		currentFilter.setCharacterLvlTo(filter.getCharacterLvlTo());
		currentFilter.setCharacterRole(filter.getCharacterRole());
		return filterRepository.save(currentFilter);
	}
	
	public Filter getUserFilter(User user) {
		if(filterRepository.findByUser(user).isEmpty()) {
			Filter filter = new Filter();
			filter.setUser(user);
			return filterRepository.save(filter);
		}
		return filterRepository.findByUser(user).get();
	}
	
	public ArrayList<String> getGames() {
		return filterRepository.findAllGames();
	} 
	
	public ArrayList<String> getRaces() {
		return filterRepository.findAllRaces();
	} 
	
	public ArrayList<String> getClasses() {
		return filterRepository.findAllClasses();
	} 
	
	//
}
