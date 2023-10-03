package com.gitlab.mercur3.jrusty.result;

import com.gitlab.mercur3.jrusty.panic.UnreachableException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class Result_Test {
	private static final String ERROR_MESSAGE = "An error has occurred";
	private static final int DEFAULT_ERR_INT = -1;

	private static Result<Integer, String> __parseInt(String str) {
		try {
			return new Ok<>(Integer.parseInt(str));
		}
		catch (NumberFormatException e) {
			return new Err<>(ERROR_MESSAGE);
		}
	}

	private static int __length(String str) {
		return str.length();
	}

	private static String __toString(int x) {
		return String.valueOf(x);
	}


	@Test
	void ok_path_works() {
		var str = "Hello, world!";
		var x = new Ok<String, Integer>(str);

		assertTrue(x.isOk());
		assertFalse(x.isErr());
		assertEquals(x.ok(), Optional.of(str));
		assertEquals(x.err(), Optional.empty());
		assertEquals(x.expect(ERROR_MESSAGE), str);
		assertThrowsExactly(
				IllegalStateException.class,
				() -> x.expectErr(ERROR_MESSAGE),
				ERROR_MESSAGE
		);
		assertEquals(x.map(ok -> 1), new Ok<>(1));
		assertEquals(x.mapErr(err -> ErrorKind.FORMAT_ERROR), new Ok<>(str));
		assertEquals(x.mapOr(-1, String::length), str.length());
		assertEquals(x.mapOrElse((err -> -1), String::length), str.length());
		assertEquals(x.unwrap(), str);
		assertThrowsExactly(IllegalStateException.class, x::unwrapErr);
		assertEquals(x.unwrapOr(""), str);
		assertEquals(x.unwrapOrElse(Result_Test::__toString), str);
	}

	@Test
	void err_path_works() {
		var str = "Some error type";
		var isErr = "Is err";
		var x = new Err<String, Integer>(str);

		assertFalse(x.isOk());
		assertTrue(x.isErr());
		assertEquals(x.ok(), Optional.empty());
		assertEquals(x.err(), Optional.of(str));
		assertThrows(IllegalStateException.class, x::unwrap);
		assertThrows(RuntimeException.class, () -> x.expect(ERROR_MESSAGE));
		assertEquals(x.map(ok -> ""), new Err<>(str));
		assertEquals(x.mapErr(err -> Empty.UNIT), new Err<>(Empty.UNIT));
		assertEquals(x.mapOr(isErr, String::valueOf), isErr);
		assertEquals(x.mapOrElse(this::strToDefaultInt, ok -> ok), -1);
		assertEquals(str, x.expectErr("oops!"));
		assertEquals(str, x.unwrapErr());
		assertEquals(x.unwrapOr(1), 1);
		assertEquals(x.unwrapOrElse(Result_Test::__length), str.length());
	}

	@Test
	void function_works() {
		int num = 1234;
		var ok = __parseInt("1234");
		var err = __parseInt("LOL");

		assertTrue(ok.isOk());
		assertFalse(ok.isErr());
		assertEquals(ok.ok(), Optional.of(num));
		assertEquals(ok.err(), Optional.empty());
		assertEquals(ok.unwrap(), num);
		assertEquals(ok.expect(ERROR_MESSAGE), num);
		assertEquals(ok.unwrapOr(0), num);
		assertEquals(ok.unwrapOrElse(Result_Test::__length), num);

		assertFalse(err.isOk());
		assertTrue(err.isErr());
		assertEquals(err.ok(), Optional.empty());
		assertEquals(err.err(), Optional.of("An error has occurred"));
		assertThrows(RuntimeException.class, () -> err.expect(ERROR_MESSAGE));
		assertThrows(IllegalStateException.class, err::unwrap);
		assertEquals(err.unwrapOr(1), 1);
		assertEquals(err.unwrapOrElse(Result_Test::__length), ERROR_MESSAGE.length());
	}

	@Test
	void null_not_allowed() {
		assertThrows(NullPointerException.class, () -> new Ok<>(null));
		assertThrows(NullPointerException.class, () -> new Err<>(null));
	}

	@Test
	void flatten_works() {
		Result<Result<String, Integer>, Integer> res1 = new Ok<>(new Ok<>("hello"));
		assertEquals(res1.flatten(), new Ok<>("hello"));

		Result<Result<String, Integer>, Integer> res2 = new Ok<>(new Err<>(6));
		assertEquals(res2.flatten(), new Err<>(6));

		Result<Result<String, Integer>, Integer> res3 = new Err<>(6);
		assertEquals(res3.flatten(), new Err<>(6));

		Result<Result<Result<String, Integer>, Integer>, Integer> res4 = new Ok<>(new Ok<>(new Ok<>(
				"hello")));
		assertEquals(res4.flatten(), new Ok<>(new Ok<>("hello")));
		assertEquals(res4.flatten().flatten(), new Ok<>("hello"));
	}

	@Test
	void pattern_matching_works() {
		Result<String, Integer> ok = new Ok<>("alpha");
		var out1 = switch (ok) {
			case Ok<String, Integer> o -> "beta";
			case Err<Integer, String> e -> "gamma";
        };
		assertEquals("beta", out1);

		Result<String, Integer> err = new Err<>(1);
		var out2 = switch (err) {
			case Ok<String, Integer> o -> "beta";
			case Err<Integer, String> e -> "gamma";
		};
		assertEquals("gamma", out2);
	}

	private int strToDefaultInt(String str) {
		return DEFAULT_ERR_INT;
	}
}
