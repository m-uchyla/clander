package com.fdmgroup.clander.services;

import com.fdmgroup.clander.security.UserDetails;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fdmgroup.clander.models.User;
import com.fdmgroup.clander.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	private Authentication authentication;
	
	public UserService() {};
	
	public UserService(UserRepository userRepository, Authentication authentication) {
		this.userRepository = userRepository;
		this.authentication = authentication;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}
		return new UserDetails(user.get());
	}
	
	public void setAuthenticationObject (Authentication authentication) {
    	 this.authentication = authentication;
    }

	public Authentication getAuthenticationObject() {
	   return authentication;
	}
	
	// ======================================== AUTHENTICATION ABOVE ========================================
	
	
	public List<User> getAllUsers (){return userRepository.findAll();}
	
	
	public User registerUser(User user) {
		user.setDefaultRole();
		return save(user);
	}
	
	public User save(User user) {return userRepository.save(user);}
	
	public User getLoggedUser() {
		if(getAuthenticationObject() == null)return null;
		Optional<User> userOptional = userRepository.findByUsername(getAuthenticationObject().getName());
		if(userOptional.isEmpty())return null;
		return userOptional.get();
	}
	
	public List<User> getUsersOutsideOfTheList(List<User> matches){
		List <User> users = userRepository.findAll();
		users.removeAll(matches);
		users.remove(getLoggedUser());
		List <User> banned = new ArrayList<>();
		for(User user : users)if(user.getBanTime() != null && user.getBanTime().after(new Date()))banned.add(user);
		users.removeAll(banned);
		return users;
	}
	
	public User editProfile (User user) {
		User currentUser = getLoggedUser();
		currentUser.setGame(user.getGame());
		currentUser.setDescription(user.getDescription());
		currentUser.setCharacterLvl(user.getCharacterLvl());
		currentUser.setCharacterRace(user.getCharacterRace());
		currentUser.setCharacterClass(user.getCharacterClass());
		currentUser.setCharacterRole(user.getCharacterRole());
		return userRepository.save(currentUser);
	}
	
	public Boolean isProfileFilled() {
		User currentUser = getLoggedUser();
		if(		currentUser.getCharacterLvl() == 0 ||
				currentUser.getCharacterClass() == null ||
				currentUser.getCharacterRace() == null ||
				currentUser.getGame() == null ||
				currentUser.getDescription() == null
				)return false;
		return true;
	}
	
	public User getUserById(Long id) {
		User user = userRepository.findById(id).get();
		return user;
	}
	
	public List<User> getUserByRole(String role){
		return userRepository.findByRole(role);
	}
	
	public Boolean isBanned() {
		if(getLoggedUser().getBanTime() == null) return false;
		if(getLoggedUser().getBanTime().after(new Date())) return true;
		return false;
	}

	public void banUser(Long userID, String type) {
		Calendar banDate;
		if(type == "PERM") {
			banDate = Calendar.getInstance();
			banDate.setTime(new Date());
			System.out.println(banDate.getTime());
			banDate.add(Calendar.YEAR, 100);
			System.out.println(banDate.getTime());
		}else {
			banDate = Calendar.getInstance();
			banDate.setTime(new Date());
			banDate.add(Calendar.MONTH, 1);
			System.out.println(banDate.getTime());
		}
		User user = userRepository.findById(userID).get();
		user.setBanTime(banDate.getTime());
		System.out.println(userRepository.save(user));
		;
	}

}
