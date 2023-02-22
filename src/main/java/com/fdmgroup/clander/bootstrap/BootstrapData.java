package com.fdmgroup.clander.bootstrap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.fdmgroup.clander.models.Filter;
import com.fdmgroup.clander.models.Match;
import com.fdmgroup.clander.models.Message;
import com.fdmgroup.clander.models.User;
import com.fdmgroup.clander.repositories.FilterRepository;
import com.fdmgroup.clander.repositories.MatchRepository;
import com.fdmgroup.clander.repositories.MessageRepository;
import com.fdmgroup.clander.repositories.UserRepository;
import com.fdmgroup.clander.services.MatchService;

@Profile("!test")
@Component
public class BootstrapData implements ApplicationRunner {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	FilterRepository filterRepository;
	
	@Autowired
	MatchRepository matchRepository;
	
	@Autowired
	MatchService matchService;


	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Bootstraping...");
		String[] games = {"Tibia","ESO","GW2","WoW"};
		String[] descriptions = {"Donec porta enim sit amet tortor fringilla, tempus pretium nibh finibus. Vivamus tincidunt eros nec vestibulum condimentum. Curabitur laoreet bibendum.",
				"In ut luctus urna. Proin ultricies leo id eros fermentum dictum. Nunc ac ex sagittis, venenatis mi ut, dapibus mi.",
				"In ac mi imperdiet diam imperdiet iaculis. Nulla fermentum metus eget metus euismod, at aliquam quam ultricies. Morbi posuere dignissim.",
				"Ut pretium tincidunt suscipit. Nullam a tellus ut eros aliquet ultrices quis sed nisl. Maecenas facilisis quam ac nulla tincidunt.",
				"Praesent commodo ante tincidunt, egestas massa id, fringilla nisl. Integer eu interdum nibh. Mauris id volutpat purus, quis vulputate nunc."};
		int[] lvls = {5,15,75,100,750};
		String[] races = 	{"Human","Ork","Elf","Goblin","Dwarf"};
		String[] classes = 	{"Assasin","Knight","Paladin","Sorcerer","Bard"};
		String[] roles = 	{"Tank","Healer","Damage Dealer"};
		
		/*
		 * 
		 * 	USER 1 	- ADMIN					-	admin / admin 
		 * 	USER 2 	- TESTING USER			-	user  / admin
		 * 	USER 12	- USER FOR PRESENTATION	-	Kokos / admin
		 * 
		 */
		
		//					   	  LOGIN			 	EMAIL				 			PASSWORD
		User user1 = 	new User("admin",			"adminemail@clander.com",		"admin");
		User user2 = 	new User("user",			"user@clander.com",				"admin");
		User user3 = 	new User("Maciek197",		"Maciek197@clander.com",		"admin");
		User user4 = 	new User("Ania487",			"Ania487@clander.com",			"admin");
		User user5 = 	new User("Tomeczek<3",		"Tomeczek@clander.com",			"admin");
		User user6 = 	new User("Konon",			"Konon@clander.com",			"admin");
		User user7 = 	new User("Vega",			"Vega@clander.com",				"admin");
		User user8 = 	new User("Cynamon",			"Cynamon@clander.com",			"admin");
		User user9 = 	new User("xAdas08x",		"xAdas08x@clander.com",			"admin");
		User user10 = 	new User("Punisher",		"Punisher@clander.com",			"admin");
		User user11 = 	new User("Lord",			"Lord@clander.com",				"admin");
		User user12 = 	new User("Kokos",			"Kokos@clander.com",			"admin");
		User user13 = 	new User("Seba(L)",			"Seba(L)@clander.com",			"admin");
		User user14 = 	new User("Destroyer3000",	"Destroyer3000@clander.com",	"admin");
		User user15 = 	new User("Juliett",			"Juliett@clander.com",			"admin");
		
		user1.setRole("ROLE_ADMIN");
		user7.setRole("ROLE_ADMIN");
		
		List<User> userList = Arrays.asList(user1,user2,user3,user4,user5,user6,user6,user7,user8,user9,user10,user11,user12,user13,user14,user15);
//		this.game = null;
//		this.description = null;
//		this.characterLvl = 0;
//		this.characterRace = null;
//		this.characterClass = null;
//		this.characterRole = null;
		for(User user : userList) {
			user.setGame(games[(int)Math.floor(Math.random() *4)]);
			user.setDescription(descriptions[(int)Math.floor(Math.random() *5)]);
			user.setCharacterLvl(lvls[(int)Math.floor(Math.random() *5)]);
			user.setCharacterRace(races[(int)Math.floor(Math.random() *5)]);
			user.setCharacterClass(classes[(int)Math.floor(Math.random() *5)]);
			user.setCharacterRole(roles[(int)Math.floor(Math.random() *3)]);
			userRepository.save(user);
		}
		
		matchService.match(user1, user12.getId());
		matchService.match(user2, user12.getId());
		
		Message message1 = new Message(user1, "Heyyy mann", user2);
		Message message2 = new Message(user2, "Yo mannnnnn", user1);
		Message message6 = new Message(user1, "message6", user2);
		Message message5 = new Message(user1, "message5", user2);
		Message message3 = new Message(user2, "message3", user1);
		Message message4 = new Message(user2, "message4", user1);
		
		
		messageRepository.save(message1);
		messageRepository.save(message2);

		messageRepository.save(message3);
		messageRepository.save(message4);
		
		messageRepository.save(message5);
		messageRepository.save(message6);
		
		

		
		
		
		System.out.println("Number of users: " + userRepository.count()+"\n");
		System.out.println("ADMIN: 			"+userRepository.findByEmail(user1.getEmail()).get());
		System.out.println("TEST USER: 		"+userRepository.findByEmail(user2.getEmail()).get());
		System.out.println("PRESENTATION USER: 	"+userRepository.findByEmail(user12.getEmail()).get());
		
		System.out.println("\nBootstraping finished.\n");
		
	}
}
