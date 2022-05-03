package com.metadt.enrollment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ConditionNotAcceptableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConditionNotAcceptableException() {
		super();
	}

	public ConditionNotAcceptableException(String message) {
		super(message);
	}

	public ConditionNotAcceptableException(String message, Throwable cause) {
		super(message, cause);
	}
}