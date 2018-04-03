package com.ip.tradetunnel.entities.repos;

import org.springframework.data.repository.CrudRepository;

import com.ip.tradetunnel.entities.Product;
import java.lang.String;
import java.util.List;
import com.ip.tradetunnel.entities.SubCategories;

public interface ProductRepository extends CrudRepository<Product, Long> {
	List<Product> findByProductNameLikeOrProductDescriptionLike(String productname,String productDescrip);
	List<Product> findBySubcategory(SubCategories subcategory);
}
