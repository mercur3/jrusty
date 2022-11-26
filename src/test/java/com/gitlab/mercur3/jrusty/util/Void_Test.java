package com.gitlab.mercur3.jrusty.util;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class Void_Test {
	@Test
	void void_is_equals__only_itself() {
		var lhs = Void.getInstance();
		var rhs = Void.getInstance();

		assertEquals(lhs, rhs);
		assertEquals(rhs, lhs);

		assertNotEquals(lhs, new Object());
		assertNotEquals(new Object(), lhs);
	}
}