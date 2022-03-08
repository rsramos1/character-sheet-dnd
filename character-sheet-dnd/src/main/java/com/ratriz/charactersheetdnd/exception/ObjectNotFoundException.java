package com.ratriz.charactersheetdnd.exception;

import org.springframework.http.HttpStatus;

import com.ratriz.charactersheetdnd.message.ConstantMessages;
import com.ratriz.charactersheetdnd.message.Message;

public class ObjectNotFoundException extends RuntimeException implements ResponseException {
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException() {
		super(Message.get(ConstantMessages.MESSAGE_ERRO_OBJECT_NOT_FOUND));
	}

	public ObjectNotFoundException(RuntimeException e) {
		super(Message.get(ConstantMessages.MESSAGE_ERRO_OBJECT_NOT_FOUND), e);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}

}
