package com.luizmariodev.domain.exception;

public class EntityConflictExcection extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntityConflictExcection(String message) {
		super(message);
	}

}
