package com.inventariotienda.springboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventariotienda.springboot.dao.iCategoryDAO;
import com.inventariotienda.springboot.model.Category;
import com.inventariotienda.springboot.response.CategoryResponseRest;





@Service
public class CategoryServiceImplement implements iCategoryService{
	
	@Autowired
	private iCategoryDAO categoryDAO;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> search() {
		
		CategoryResponseRest response = new CategoryResponseRest();
		
		try {
			
			List<Category> category = (List<Category>) categoryDAO.findAll();
			response.getCategoryResponse().setCategory(category);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta no ok", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoryResponseRest> searchById(Long id) {

		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		try {
			
			Optional<Category> category = categoryDAO.findById(id);
			
			if (category.isPresent()) {
				list.add(category.get());
				response.getCategoryResponse().setCategory(list);
				response.setMetadata("Respuesta ok", "00", "Categoría encontrada");
				
			} else {
				
				response.setMetadata("Respuesta no ok", "-1", "Categoría no encontrada");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
				
			}
			

			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta no ok", "-1", "Error al consultar por id");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> save(Category category) {
		
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		try {
			
			Category categorySaved = categoryDAO.save(category);
			
			if (categorySaved != null) {
				list.add(categorySaved);
				response.getCategoryResponse().setCategory(list);
				response.setMetadata("Respuesta ok", "00", "Categoría guardada");
			} else {
				
				response.setMetadata("Respuesta no ok", "-1", "Categoría no guardada");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
				
			}
			
			

			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta no ok", "-1", "Error al grabar categoría");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
		CategoryResponseRest response = new CategoryResponseRest();
		List<Category> list = new ArrayList<>();
		
		try {
			
			Optional<Category> categorySearch = categoryDAO.findById(id);
			
			if (categorySearch.isPresent()) {
				//se procederá a actualizar el registro
				categorySearch.get().setName(category.getName());
				categorySearch.get().setDescription(category.getDescription());
				
				Category categoryToUpdate = categoryDAO.save(categorySearch.get());
				
				if (categoryToUpdate != null) {
					
					list.add(categoryToUpdate);
					response.getCategoryResponse().setCategory(list);
					response.setMetadata("Respuesta ok", "00", "Categoría actualizada");
					
				} else {
					
					response.setMetadata("Respuesta no ok", "-1", "Categoría no actualizada");
					return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
					
				}
				
			} else {
				
				response.setMetadata("Respuesta no ok", "-1", "Categoría no encontrada");
				return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
				
			}
			
			

			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta no ok", "-1", "Error al actualizar categoría");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> deleteById(Long id) {
		
		CategoryResponseRest response = new CategoryResponseRest();
		
		try {
			
			categoryDAO.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Registro eliminado");
		
			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta no ok", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);

	}

}

