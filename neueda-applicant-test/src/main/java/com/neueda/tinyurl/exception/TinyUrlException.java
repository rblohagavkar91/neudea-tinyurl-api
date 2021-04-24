package com.neueda.tinyurl.exception;

/**
 * This is an exception class for TinyUrl.
 */
public class TinyUrlException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TinyUrlException(String message) {
        super(message);
    }

    public TinyUrlException(String message, Throwable cause) {
        super(message, cause);
    }
}
