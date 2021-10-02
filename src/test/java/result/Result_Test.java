package result;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class Result_Test {
	private static final String ERROR_MESSAGE = "It cannot be parsed";

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



	/*     PRIVATE     */

	@Test
	void ok_path_works() {
		var str = "Hello, world!";
		var x = new Ok<String, Integer>(str);

		assertTrue(x.isOk());
		assertFalse(x.isErr());
		assertEquals(x.ok(), Optional.of(str));
		assertEquals(x.err(), Optional.empty());
		assertEquals(x.unwrap(), str);
		assertEquals(x.unwrapOr(""), str);
		assertEquals(x.unwrapOrElse(Result_Test::__toString), str);
	}

	@Test
	void err_path_works() {
		var str = "Some error type";
		var x = new Err<String, Integer>(str);

		assertFalse(x.isOk());
		assertTrue(x.isErr());
		assertEquals(x.ok(), Optional.empty());
		assertEquals(x.err(), Optional.of(str));
		assertThrows(IllegalStateException.class, x::unwrap);
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
		assertEquals(ok.unwrapOr(0), num);
		assertEquals(ok.unwrapOrElse(Result_Test::__length), num);

		assertFalse(err.isOk());
		assertTrue(err.isErr());
		assertEquals(err.ok(), Optional.empty());
		assertEquals(err.err(), Optional.of("It cannot be parsed"));
		assertThrows(IllegalStateException.class, err::unwrap);
		assertEquals(err.unwrapOr(1), 1);
		assertEquals(err.unwrapOrElse(Result_Test::__length), ERROR_MESSAGE.length());
	}

	@Test
	void null_not_allowed() {
		assertThrows(NullPointerException.class, () -> new Ok<>(null));
		assertThrows(NullPointerException.class, () -> new Err<>(null));
	}
}
