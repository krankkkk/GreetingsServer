package de.example.util;

import java.util.Arrays;
import java.util.Objects;

public final class ArrayUtils
{

	public static boolean allNonNull(final Object... objects)
	{
		if (objects == null)
		{
			return false;
		}

		return Arrays.stream(objects).noneMatch(Objects::isNull);
	}



	private ArrayUtils(){}

}
