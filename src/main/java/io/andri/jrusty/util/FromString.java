package io.andri.jrusty.util;

import io.andri.jrusty.result.Err;
import io.andri.jrusty.result.ErrorKind;
import io.andri.jrusty.result.Ok;
import io.andri.jrusty.result.Result;

public class FromString {
	private FromString() {}

	/**
	 * Parses a <code>String</code> to <code>boolean</code>
	 *
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
	 * Parses a <code>String</code> to <code>byte</code> with the given radix
	 *
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
	 * Parses a <code>String</code> to <code>byte</code> base 10
	 *
	 * @see #parseByteWithRadix(String, int)
	 */
	public static Result<Byte, ErrorKind> parseByte(String str) {
		return parseByteWithRadix(str, 10);
	}

	/**
	 * Parses a <code>String</code> to <code>int</code> with the given radix
	 *
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
	 * Parses a <code>String</code> to <code>int</code> base 10
	 *
	 * @see #parseIntWithRadix(String, int)
	 */
	public static Result<Integer, ErrorKind> parseInt(String str) {
		return parseIntWithRadix(str, 10);
	}

	/**
	 * Parses a <code>String</code> to <code>long</code> with the given radix
	 *
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
	 * Parses a <code>String</code> to <code>long</code> base 10
	 *
	 * @see #parseLongWithRadix(String, int)
	 */
	public static Result<Long, ErrorKind> parseLong(String str) {
		return parseLongWithRadix(str, 10);
	}
}
