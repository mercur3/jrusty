package com.gitlab.mercur3.jrusty.util;

import com.gitlab.mercur3.jrusty.result.Err;
import com.gitlab.mercur3.jrusty.result.ErrorKind;
import com.gitlab.mercur3.jrusty.result.Ok;
import com.gitlab.mercur3.jrusty.result.Result;

/**
 * From {@code String} conversion utils.
 */
public class FromString {
	private FromString() {}

	/**
	 * Parse a {@code String} to {@code boolean}.
	 *
	 * @param str string
	 * @return <code>ErrorKind.FORMAT_ERROR</code> if the string is not valid
	 * @see Boolean#parseBoolean(String)
	 */
	public static Result<Boolean, ErrorKind> parseBoolean(String str) {
		try {
			return new Ok<>(Boolean.parseBoolean(str));
		}
		catch (NumberFormatException e) {
			return new Err<>(ErrorKind.FORMAT_ERROR);
		}
	}

	/**
	 * Parse a {@code String} to {@code byte} with the given radix
	 *
	 * @param str   string
	 * @param radix radix
	 * @return <code>ErrorKind.FORMAT_ERROR</code> if the string is not a valid number
	 * @see Byte#parseByte(String, int)
	 */
	public static Result<Byte, ErrorKind> parseByteWithRadix(String str, int radix) {
		try {
			return new Ok<>(Byte.parseByte(str, radix));
		}
		catch (NumberFormatException e) {
			return new Err<>(ErrorKind.FORMAT_ERROR);
		}
	}

	/**
	 * Parse a {@code String} to {@code byte} base 10
	 *
	 * @param str string
	 * @see #parseByteWithRadix(String, int)
	 */
	public static Result<Byte, ErrorKind> parseByte(String str) {
		return parseByteWithRadix(str, 10);
	}

	/**
	 * Parse a {@code String} to {@code int} with the given radix
	 *
	 * @param str   string
	 * @param radix radix
	 * @return <code>ErrorKind.FORMAT_ERROR</code> if the string is not a valid number
	 * @see Integer#parseInt(String, int)
	 */
	public static Result<Integer, ErrorKind> parseIntWithRadix(String str, int radix) {
		try {
			return new Ok<>(Integer.parseInt(str, radix));
		}
		catch (NumberFormatException e) {
			return new Err<>(ErrorKind.FORMAT_ERROR);
		}
	}

	/**
	 * Parse a {@code String} to {@code int} base 10
	 *
	 * @param str string
	 * @see #parseIntWithRadix(String, int)
	 */
	public static Result<Integer, ErrorKind> parseInt(String str) {
		return parseIntWithRadix(str, 10);
	}

	/**
	 * Parse a {@code String} to {@code long} with the given radix
	 *
	 * @param str   string
	 * @param radix radix
	 * @return <code>ErrorKind.FORMAT_ERROR</code> if the string is not a valid number
	 * @see Long#parseLong(String, int)
	 */
	public static Result<Long, ErrorKind> parseLongWithRadix(String str, int radix) {
		try {
			return new Ok<>(Long.parseLong(str, radix));
		}
		catch (NumberFormatException e) {
			return new Err<>(ErrorKind.FORMAT_ERROR);
		}
	}

	/**
	 * Parse a {@code String} to {@code long} base 10
	 *
	 * @param str string
	 * @see #parseLongWithRadix(String, int)
	 */
	public static Result<Long, ErrorKind> parseLong(String str) {
		return parseLongWithRadix(str, 10);
	}
}
