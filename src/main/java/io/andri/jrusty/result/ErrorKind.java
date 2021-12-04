package io.andri.jrusty.result;

public enum ErrorKind {
	/** Rust's <code>()</code> */
	EMPTY,
	/** Generic error caused by an IO operation */
	IO_ERROR,
	FORMAT_ERROR,
	ILLEGAL_STATE,
	OVERFLOW,
	NOT_FOUND,
	PERMISSION_DENIED,
	CONNECTION_REFUSED,
	NOT_CONNECTED,
	ALREADY_EXISTS,
	READONLY,
	INVALID_INPUT,
}
