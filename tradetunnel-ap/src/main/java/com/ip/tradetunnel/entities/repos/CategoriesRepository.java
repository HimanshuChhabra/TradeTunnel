package com.ip.tradetunnel.entities.repos;

import org.springframework.data.repository.CrudRepository;

import com.ip.tradetunnel.entities.Categories;
import java.lang.String;
import java.util.List;

public interface CategoriesRepository extends CrudRepository<Categories, Long> {
			List<Categories> findByCategoryName(String categoryname);
}
