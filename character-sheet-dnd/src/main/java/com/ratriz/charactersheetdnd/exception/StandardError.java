package com.ratriz.charactersheetdnd.exception;

public record StandardError(Integer status, String message, Long timeStamp) {

	public StandardError(Integer status, String message) {
		this(status, message, System.currentTimeMillis());
	}

}
