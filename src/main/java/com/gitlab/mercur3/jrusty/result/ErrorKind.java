package com.gitlab.mercur3.jrusty.result;

/**
 * Some error kinds
 */
public enum ErrorKind {
	/** Generic error caused by an IO operation */
	IO_ERROR,
	/** Format error */
	FORMAT_ERROR,
	/** Illegal state */
	ILLEGAL_STATE,
	/** Overflow */
	OVERFLOW,
	/** Not found */
	NOT_FOUND,
	/** Permission denied */
	PERMISSION_DENIED,
	/** Connection refused */
	CONNECTION_REFUSED,
	/** Not connected */
	NOT_CONNECTED,
	/** Already exists */
	ALREADY_EXISTS,
	/** Readonly */
	READONLY,
	/** Invalid input */
	INVALID_INPUT,
}
