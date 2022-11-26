package com.gitlab.mercur3.jrusty.option;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class Option_Test {
	@Test
	void map_or() {
		var x = Optional.of("alpha");
		assertEquals(Option.mapOr(x, 42, String::length), 5);

		Optional<String> y = Optional.empty();
		assertEquals(Option.mapOr(y, 42, String::length), 42);
	}

	@Test
	void map_or_else() {
		var k = 21;

		var x = Optional.of("beta");
		assertEquals(Option.mapOrElse(x, () -> 2 * k, String::length), 4);

		Optional<String> y = Optional.empty();
		assertEquals(Option.mapOrElse(y, () -> 2 * k, String::length), 42);
	}
}