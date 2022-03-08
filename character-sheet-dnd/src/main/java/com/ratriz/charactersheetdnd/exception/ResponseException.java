package com.ratriz.charactersheetdnd.exception;

import org.springframework.http.HttpStatus;

public interface ResponseException {

	public HttpStatus getStatus();

}
