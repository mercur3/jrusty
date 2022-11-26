package com.gitlab.mercur3.jrusty.util;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Isolated;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
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
