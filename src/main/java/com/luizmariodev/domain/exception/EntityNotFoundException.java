package com.luizmariodev.domain.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntityNotFoundException(String message) {
		super(message);
	}
	
	public EntityNotFoundException(Long entityId) {
		super(String.format("Entity %l not found or not exists", entityId));
	}
}
