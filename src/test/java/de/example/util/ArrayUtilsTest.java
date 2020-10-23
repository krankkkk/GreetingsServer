package de.example.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilsTest
{

	@Test
	void allNonNull()
	{
		assertTrue(ArrayUtils.allNonNull());
		assertFalse(ArrayUtils.allNonNull((Object) null));
		assertFalse(ArrayUtils.allNonNull((Object[]) null));
	}
}