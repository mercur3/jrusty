package io.andri.jrusty.util;

import io.andri.jrusty.result.Err;
import io.andri.jrusty.result.Ok;
import io.andri.jrusty.result.Result;

public class FromString {
	private static final String ERROR_MESSAGE = "Cannot be parsed";

	private FromString() {}

	public static Result<Boolean, String> parseBoolean(String str) {
		try {
			return new Ok<>(Boolean.parseBoolean(str));
		}
		catch (NumberFormatException e) {
			return new Err<>(ERROR_MESSAGE);
		}
	}

	public static Result<Byte, String> parseByteWithRadix(String str, int radix) {
		try {
			return new Ok<>(Byte.parseByte(str, radix));
		}
		catch (NumberFormatException e) {
			return new Err<>(ERROR_MESSAGE);
		}
	}

	public static Result<Byte, String> parseByte(String str) {
		return parseByteWithRadix(str, 10);
	}

	public static Result<Integer, String> parseIntWithRadix(String str, int radix) {
		try {
			return new Ok<>(Integer.parseInt(str, radix));
		}
		catch (NumberFormatException e) {
			return new Err<>(ERROR_MESSAGE);
		}
	}

	public static Result<Integer, String> parseInt(String str) {
		return parseIntWithRadix(str, 10);
	}

	public static Result<Long, String> parseLongWithRadix(String str, int radix) {
		try {
			return new Ok<>(Long.parseLong(str, radix));
		}
		catch (NumberFormatException e) {
			return new Err<>(ERROR_MESSAGE);
		}
	}

	public static Result<Long, String> parseLong(String str) {
		return parseLongWithRadix(str, 10);
	}
}
