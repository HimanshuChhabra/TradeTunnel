package com.ip.tradetunnel.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Subcategories")
public class SubCategories extends AbstractEntity {
	private String subcategoryName;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Categories category;

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}

}
