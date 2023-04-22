package com.inventariotienda.springboot.services;

import org.springframework.http.ResponseEntity;

import com.inventariotienda.springboot.model.Category;
import com.inventariotienda.springboot.response.CategoryResponseRest;



public interface iCategoryService {
	
	public ResponseEntity<CategoryResponseRest> search();
	public ResponseEntity<CategoryResponseRest> searchById(Long id);
	public ResponseEntity<CategoryResponseRest> save(Category category);
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id);
	public ResponseEntity<CategoryResponseRest> deleteById(Long id);


}
