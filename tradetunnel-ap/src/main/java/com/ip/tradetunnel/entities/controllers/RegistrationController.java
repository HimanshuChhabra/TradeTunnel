package com.ip.tradetunnel.entities.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ip.tradetunnel.entities.UserProfile;
import com.ip.tradetunnel.entities.controllers.exceptions.MalFormedUserException;
import com.ip.tradetunnel.entities.repos.UserProfileRepository;

@RepositoryRestController
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	UserProfileRepository userRepo;

	@PostMapping
	private ResponseEntity<PersistentEntityResource> userRegistration(@RequestBody UserProfile user , PersistentEntityResourceAssembler assembler) {

		if (user.getFirstName() != null && !user.getFirstName().isEmpty() && user.getLastName() != null
				&& !user.getLastName().isEmpty() && user.getEmailId() != null && !user.getEmailId().isEmpty()
				&& user.getPassWord() != null && !user.getPassWord().isEmpty()) {
			
			if(userRepo.findByEmailId(user.getEmailId()).isEmpty()) {
				userRepo.save(user);	
			}
			
			return ResponseEntity.ok(assembler.toResource(user));
		}else {
			throw new MalFormedUserException("Missing Manditory Fileds" );
		}
	}
}