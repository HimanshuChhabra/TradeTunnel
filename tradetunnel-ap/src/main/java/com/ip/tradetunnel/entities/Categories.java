package com.ip.tradetunnel.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Categories")
public class Categories extends AbstractEntity {

	private String categoryName;

	@ManyToOne
	@JoinColumn(name = "prod_id", nullable = false)
	private Product product;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private Set<SubCategories> subCategory;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Set<SubCategories> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Set<SubCategories> subCategory) {
		this.subCategory = subCategory;
	}
}
