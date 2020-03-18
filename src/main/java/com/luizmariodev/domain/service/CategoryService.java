package com.luizmariodev.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizmariodev.domain.model.Category;
import com.luizmariodev.domain.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional
	public Category save(Category category) {
		return categoryRepository.save(category);
	}
	
	@Transactional
	public Category update(Long categoryId, Category category) {
		Optional<Category> optionalCategory = findById(categoryId);
		if (optionalCategory.isPresent()) {
			Category categorySave = optionalCategory.get();
			BeanUtils.copyProperties(category, categorySave, "id");
			categoryRepository.save(categorySave);			
			return categorySave;
		}
		return category;
	}
	
	@Transactional
	public void delete(Long categoryId) {
		Optional<Category> optionalCategory = findById(categoryId);		
		if (optionalCategory.isPresent())
			categoryRepository.delete(optionalCategory.get());		
	}
	
	private Optional<Category> findById(Long categoryId) {		
		return categoryRepository.findById(categoryId);
	}

}
