
package com.ip.tradetunnel.entities.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public @ResponseBody ResponseEntity<?> getProductListByName(@PathVariable String word ) {
		List<Product> productList = productRepo.findByProductNameLikeOrProductDescriptionLike("%"+word+"%","%"+word+"%");
		//List<String> list = Arrays.asList("Hi","LP");
		Resources<Product> resources = new Resources<Product>(productList);
		//Resources<String> resources = new Resources<String>(list);
		return ResponseEntity.ok(resources);
	}
}
