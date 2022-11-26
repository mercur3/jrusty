package com.gitlab.mercur3.jrusty.panic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UnimplementedException_Test {
	private static void func1() {
		throw new UnimplementedException();
	}

	/// PRIVATE

	private static String func2() {
		throw new UnimplementedException();
	}

	@Test
	@DisplayName("Code compiles, but panics at runtime")
	void t1() {
		assertThrows(UnimplementedException.class, UnimplementedException_Test::func1);
		assertThrows(UnimplementedException.class, () -> {
			var someStr = func2();
			System.out.println(someStr);
		});
	}
}
