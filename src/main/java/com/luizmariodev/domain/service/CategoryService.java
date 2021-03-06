package com.luizmariodev.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luizmariodev.domain.exception.EntityConflictExcection;
import com.luizmariodev.domain.exception.EntityNotFoundException;
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
		Category categorySave = findById(categoryId);
		BeanUtils.copyProperties(category, categorySave, "id");
		categoryRepository.save(categorySave);			
		return categorySave;
	}
	
	@Transactional
	public void delete(Long categoryId) {
		try {
			categoryRepository.deleteById(categoryId);
			categoryRepository.flush();			
		} catch (DataIntegrityViolationException e) {
			throw new EntityConflictExcection("Category conflict");
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Category not found");
		}
	}
	
	public Category findById(Long categoryId) {
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		if (!optionalCategory.isPresent())
			throw new EntityNotFoundException("Category not found");
		
		return optionalCategory.get();
	}

}
