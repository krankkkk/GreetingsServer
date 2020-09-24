package de.example.util;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest
{

	@Test
	void isEmpty()
	{
		assertTrue(StringUtils.isEmpty(null));
		assertTrue(StringUtils.isEmpty(""));
		assertFalse(StringUtils.isEmpty(" "));
		assertFalse(StringUtils.isEmpty("aiudhaiwdiuwa"));
	}

	@Test
	void isEmptyTrim()
	{
		assertTrue(StringUtils.isEmptyTrim(null));
		assertTrue(StringUtils.isEmptyTrim(""));
		assertTrue(StringUtils.isEmptyTrim(" "));
		assertFalse(StringUtils.isEmptyTrim("aiudhaiwdiuwa"));
	}

	@Test
	void isAnyEmptyColl()
	{
		assertTrue(StringUtils.isAnyEmpty(null, true));
		assertTrue(StringUtils.isAnyEmpty(null, false));
		assertTrue(StringUtils.isAnyEmpty(Collections.emptyList(), true));
		assertTrue(StringUtils.isAnyEmpty(Collections.emptyList(), false));

		assertTrue(StringUtils.isAnyEmpty(Collections.singletonList(""), true));
		assertTrue(StringUtils.isAnyEmpty(Collections.singletonList(""), false));
		assertTrue(StringUtils.isAnyEmpty(Collections.singletonList(null), true));
		assertTrue(StringUtils.isAnyEmpty(Collections.singletonList(null), false));
		assertTrue(StringUtils.isAnyEmpty(Collections.singletonList(" "), true));
		assertFalse(StringUtils.isAnyEmpty(Collections.singletonList(" "), false));

	}

	@Test
	void isAnyEmptyVar()
	{
		assertTrue(StringUtils.isAnyEmpty(true));
		assertTrue(StringUtils.isAnyEmpty(false));

		assertTrue(StringUtils.isAnyEmpty(true, ""));
		assertTrue(StringUtils.isAnyEmpty(false, (String) null));
		assertTrue(StringUtils.isAnyEmpty(false, (String[]) null));
		assertTrue(StringUtils.isAnyEmpty(true, (String) null));
		assertTrue(StringUtils.isAnyEmpty(true, (String[]) null));
	}
}