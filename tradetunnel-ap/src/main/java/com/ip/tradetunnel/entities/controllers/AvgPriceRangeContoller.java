package com.ip.tradetunnel.entities.controllers;

import java.util.Arrays;
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
import com.ip.tradetunnel.entities.SubCategories;
import com.ip.tradetunnel.entities.repos.ProductRepository;
import com.ip.tradetunnel.entities.repos.SubCategoriesRepository;

@RepositoryRestController
@RequestMapping("/average")
public class AvgPriceRangeContoller {


	@Autowired
	ProductRepository productRepo;
	@Autowired
	SubCategoriesRepository subcatrepo;


	@GetMapping("/{subcategoryID}")
	public @ResponseBody ResponseEntity<?> getAveragePrice(@PathVariable Long subcategoryID) {
		float totalCost = 0.00f;
		Float averageCost;
		SubCategories subcat = subcatrepo.findOne(subcategoryID);

		List<Product> product = productRepo.findBySubcategory(subcat);
		
		for(Product prod : product) {
			totalCost+=prod.getPrice();
		}
		
		averageCost = totalCost/product.size();
		
		 List<Float> list = Arrays.asList(averageCost);
		Resources<Float> resources = new Resources<Float>(list);
		
		return ResponseEntity.ok(resources);
	}

}
