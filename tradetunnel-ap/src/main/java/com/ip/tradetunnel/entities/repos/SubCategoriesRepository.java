package com.ip.tradetunnel.entities.repos;

import org.springframework.data.repository.CrudRepository;

import com.ip.tradetunnel.entities.SubCategories;
import java.lang.String;
import java.util.List;


public interface SubCategoriesRepository extends CrudRepository<SubCategories, Long> {
	
	List<SubCategories> findBySubcategoryName(String subcategoryname);
	
}
