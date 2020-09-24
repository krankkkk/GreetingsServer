package de.example.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilsTest
{

	@Test
	void allNonNull()
	{
		assertFalse(ArrayUtils.allNonNull());
		assertTrue(ArrayUtils.allNonNull((Object) null));
		assertFalse(ArrayUtils.allNonNull((Object[]) null));
	}
}