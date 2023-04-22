package com.inventariotienda.springboot.response;

import java.util.List;

import com.inventariotienda.springboot.model.Category;

public class CategoryResponse {
	
	private List<Category> category;
	
	public CategoryResponse() {

	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}
	
	

}

