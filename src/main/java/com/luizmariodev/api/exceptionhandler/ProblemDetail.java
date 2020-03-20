package com.luizmariodev.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

public class ProblemDetail {
	
	private Integer status;
	private String type;
	private String title;
	private String detail;
	private LocalDateTime timeStamp;
	private String message;
	private List<Propriedade> properties;
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Propriedade> getProperties() {
		return properties;
	}

	public void setProperties(List<Propriedade> properties) {
		this.properties = properties;
	}

	public static class Propriedade {
		private String name;
		private String messageUser;
				
		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setMessageUser(String messageUser) {
			this.messageUser = messageUser;
		}
		
		public String getMessageUser() {
			return messageUser;
		}
	}
}
