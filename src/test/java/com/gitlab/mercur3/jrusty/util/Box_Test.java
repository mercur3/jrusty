package com.gitlab.mercur3.jrusty.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Box_Test {
	@Test
	void should_not_set_inner_content_to_null_with_withDefault() {
		assertThrowsExactly(NullPointerException.class, () -> Box.withDefault(null));
	}

	@Test
	void correct_initialization() {
		var strBox = Box.withDefault("lol");
		assertEquals(strBox.value, "lol");

		var nullBox = Box.empty();
		assertNull(nullBox.value);
	}
}
