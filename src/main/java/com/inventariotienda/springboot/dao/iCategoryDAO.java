package com.inventariotienda.springboot.dao;

import org.springframework.data.repository.CrudRepository;

import com.inventariotienda.springboot.model.Category;


public interface iCategoryDAO extends CrudRepository<Category, Long>{
	
	

}
