package com.ip.tradetunnel.filters;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class FilterCriteria {

	private Set<String> categories;
	private Set<String> subcategories;
	@Autowired
	private Price range;

	public Set<String> getCategories() {
		return categories;
	}

	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}

	public Set<String> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(Set<String> subcategories) {
		this.subcategories = subcategories;
	}

	public Price getRange() {
		return range;
	}

	public void setRange(Price range) {
		this.range = range;
	}

}
