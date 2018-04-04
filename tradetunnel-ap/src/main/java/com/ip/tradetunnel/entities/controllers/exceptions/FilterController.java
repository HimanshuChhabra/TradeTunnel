package com.ip.tradetunnel.entities.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ip.tradetunnel.entities.Categories;
import com.ip.tradetunnel.entities.Product;
import com.ip.tradetunnel.entities.SubCategories;
import com.ip.tradetunnel.entities.UserProfile;
import com.ip.tradetunnel.entities.repos.CategoriesRepository;
import com.ip.tradetunnel.entities.repos.ProductRepository;
import com.ip.tradetunnel.entities.repos.SubCategoriesRepository;
import com.ip.tradetunnel.entities.repos.UserProfileRepository;
import com.ip.tradetunnel.filters.FilterCriteria;
import com.ip.tradetunnel.filters.Price;

@RepositoryRestController
@RequestMapping("/filter")

public class FilterController {

	@Autowired
	ProductRepository prodRepo;
	@Autowired
	CategoriesRepository catRepo;
	@Autowired
	SubCategoriesRepository subcatRepo;

	@PostMapping
	private @ResponseBody ResponseEntity<?> userRegistration(@RequestBody FilterCriteria criteria,
			PersistentEntityResourceAssembler assembler) {
		List<Product> productList = new ArrayList<Product>();

		if (criteria != null) {
			if (criteria.getCategories() != null) {
				Set<String> categories = criteria.getCategories();
				for (String category : categories) {
					List<Categories> categoryNameList = catRepo.findByCategoryName(category);
					if (!categoryNameList.isEmpty()) {
						for (Categories cat : categoryNameList)
							productList.addAll(prodRepo.findByCategory(cat));
					}
				}
			}

			if (criteria.getSubcategories() != null) {
				Set<String> subcategories = criteria.getSubcategories();
				for (String subCategory : subcategories) {
					List<SubCategories> subcategoryNameList = subcatRepo.findBySubcategoryName(subCategory);
					if (!subcategoryNameList.isEmpty()) {
						for (SubCategories subcat : subcategoryNameList) {
							List<Product> subList = prodRepo.findBySubcategory(subcat);
							productList = merge(productList, subList);
						}
					}
				}
			}

			if (criteria.getRange() != null) {
				Price price = criteria.getRange();
				if (price != null) {
					List<Product> between = prodRepo.findByPriceBetween(price.getFrom(), price.getTo());
					productList = merge(productList, between);
				}
			}
		}
		List<PersistentEntityResource> halProds = new ArrayList<PersistentEntityResource>();
		for (Product prod : productList) {
			halProds.add(assembler.toResource(prod));
		}
		Resources<PersistentEntityResource> resources = new Resources<PersistentEntityResource>(halProds);
		return ResponseEntity.ok(resources);
	}

	private List<Product> merge(List<Product> productList, List<Product> subList) {
		List<Product> resultSet = new ArrayList<Product>();
		for (Product prod : subList) {
			if (productList.contains(prod)) {
				resultSet.add(prod);
;			}
		}
		return resultSet;
	}

}
