package com.luizmariodev.api.exceptionhandler;

public enum TypeProblem {

	DATA_INVALID("/erro-data-invalid", "Data invalid"),
	DATA_NOT_FOUND("/erro-data-not-found", "Data not found");
	private String title;
	private String path;	
	
	private TypeProblem(String path, String titulo) {
		this.path = "https://api.luizmariodev-product.com.br" + path;
		this.title = titulo;
	}

	public String getTitle() {
		return title;
	}

	public String getPath() {
		return path;
	}	
}
