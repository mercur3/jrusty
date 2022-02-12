package com.gitlab.mercur3.jrusty.panic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnimplementedException_Test {
	@Test
	@DisplayName("Code compiles, but panics at runtime")
	void t1() {
		assertThrows(UnimplementedException.class, UnimplementedException_Test::func1);
		assertThrows(UnimplementedException.class, () -> {
			var someStr = func2();
			System.out.println(someStr);
		});
	}

	/// PRIVATE

	private static void func1() {
		throw new UnimplementedException();
	}

	private static String func2() {
		throw new UnimplementedException();
	}
}
