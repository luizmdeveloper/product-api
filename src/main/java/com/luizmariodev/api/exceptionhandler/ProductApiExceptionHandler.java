package com.luizmariodev.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.luizmariodev.domain.exception.EntityNotFoundException;

@ControllerAdvice
public class ProductApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource; 	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
		
		BindingResult bindResult = ex.getBindingResult();
		List<ProblemDetail.Propriedade> properties = new ArrayList<ProblemDetail.Propriedade>();
		for (FieldError fieldError: bindResult.getFieldErrors()) {
			String mesageUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			
			ProblemDetail.Propriedade property = new ProblemDetail.Propriedade();
			property.setName(fieldError.getField());
			property.setMessageUser(mesageUser);
			properties.add(property);
		}
		
		ProblemDetail problem = criarProblemaBuilder(TypeProblem.DATA_INVALID, status, detail);
		problem.setMessage(detail);
		problem.setProperties(properties);
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}	
	
	@ExceptionHandler({EntityNotFoundException.class})
	protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
		ProblemDetail problem = criarProblemaBuilder(TypeProblem.DATA_NOT_FOUND, HttpStatus.NOT_FOUND, ex.getMessage());
		problem.setMessage(ex.getMessage());

		return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ProblemDetail problem = new ProblemDetail();
		problem.setStatus(status.value());
		problem.setTimeStamp(LocalDateTime.now());
		
		if (body == null) {
			problem.setTitle(status.getReasonPhrase());
			
			body = problem;
		} else if (body instanceof String) {
			problem.setDetail((String) body);
			body = problem;
		} 
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	
	
	private ProblemDetail criarProblemaBuilder(TypeProblem typeProblem, HttpStatus status, String detail){
		ProblemDetail problem = new ProblemDetail();
		problem.setStatus(status.value());
		problem.setTitle(typeProblem.getTitle());
		problem.setType(typeProblem.getPath());
		problem.setDetail(detail);
		problem.setTimeStamp(LocalDateTime.now());		
		return problem;
	}
}
