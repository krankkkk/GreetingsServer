package de.example.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

public final class StringUtils
{

	public static boolean isEmpty(final String str)
	{
		return str == null || str.isEmpty();
	}

	public static boolean isEmptyTrim(final String str)
	{
		return str == null || str.isEmpty() || str.trim().isEmpty();
	}

	public static boolean isAnyEmpty(final Collection<String> coll, final boolean checkTrim)
	{
		if (CollectionUtils.isEmpty(coll))
		{
			return true;
		}


		final Stream<String> stream = coll.stream();
		return checkTrim ? stream.anyMatch(StringUtils::isEmptyTrim) : stream.anyMatch(StringUtils::isEmpty);
	}

	public static boolean isAnyEmpty(final boolean checkTrim, final String... strings)
	{
		if (strings == null || strings.length == 0)
		{
			return true;
		}

		return isAnyEmpty(Arrays.asList(strings), checkTrim);
	}

	private StringUtils()
	{
	}
}
