
package com.ip.tradetunnel.entities.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ip.tradetunnel.entities.Product;
import com.ip.tradetunnel.entities.repos.ProductRepository;

@RepositoryRestController
@RequestMapping("/search")
public class CustomSearchSuggestionController {

	@Autowired
	ProductRepository productRepo;

	@GetMapping("/{word}")
	public @ResponseBody ResponseEntity<?> getProductListByName(@PathVariable String word,
			PersistentEntityResourceAssembler assembler) {
		List<Product> productList = productRepo.findByProductNameLikeOrProductDescriptionLike("%" + word + "%",
				"%" + word + "%");
		// List<String> list = Arrays.asList("Hi","LP");

		List<PersistentEntityResource> halProds = new ArrayList<PersistentEntityResource>();
		for (Product prod : productList) {
			halProds.add(assembler.toResource(prod));
		}
		Resources<PersistentEntityResource> resources = new Resources<PersistentEntityResource>(halProds);
		// Resources<Product> resources = new Resources<Product>(productList);
		// Resources<String> resources = new Resources<String>(list);

		return ResponseEntity.ok(resources);
	}
}
