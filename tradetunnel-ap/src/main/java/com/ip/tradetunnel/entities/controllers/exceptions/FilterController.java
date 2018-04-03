package com.ip.tradetunnel.entities.controllers.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ip.tradetunnel.entities.UserProfile;
import com.ip.tradetunnel.entities.repos.FilterCriteria;
import com.ip.tradetunnel.entities.repos.UserProfileRepository;

@RepositoryRestController
@RequestMapping("/filter")

public class FilterController {


	@Autowired
	UserProfileRepository userRepo;

	@PostMapping
	private ResponseEntity<PersistentEntityResource> userRegistration(@RequestBody FilterCriteria user , PersistentEntityResourceAssembler assembler) {

			
			return ResponseEntity.ok(assembler.toResource(null));
	}
		
}
