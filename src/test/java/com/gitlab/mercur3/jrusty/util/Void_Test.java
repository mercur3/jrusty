package com.gitlab.mercur3.jrusty.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Void_Test {
	@Test
	void void_is_equal_to_only_itself() {
		var lhs = Void.getInstance();
		var rhs = Void.getInstance();

		assertEquals(lhs, rhs);
		assertEquals(rhs, lhs);

		assert !lhs.equals(new Object());
		assert !new Object().equals(lhs);
	}
}