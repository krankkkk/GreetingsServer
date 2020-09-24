package de.example.util;

import java.util.Collection;

public final class CollectionUtils
{

	public static boolean isEmpty(final Collection<?> coll)
	{
		return coll == null || coll.isEmpty();
	}

	private CollectionUtils(){}
}
