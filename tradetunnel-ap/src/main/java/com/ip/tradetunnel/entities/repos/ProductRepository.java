package com.ip.tradetunnel.entities.repos;

import org.springframework.data.repository.CrudRepository;

import com.ip.tradetunnel.entities.Product;
import java.lang.String;
import java.util.List;
import com.ip.tradetunnel.entities.SubCategories;
import com.ip.tradetunnel.entities.Categories;
import com.ip.tradetunnel.entities.UserProfile;

public interface ProductRepository extends CrudRepository<Product, Long> {
	List<Product> findByProductNameLikeOrProductDescriptionLike(String productname,String productDescrip);
	List<Product> findByCategory(Categories category);
	List<Product> findBySubcategory(SubCategories subcategory);
	List<Product> findByPriceBetween(Float from, Float to);
	List<Product> findByUserProfile(UserProfile userprofile);
}
