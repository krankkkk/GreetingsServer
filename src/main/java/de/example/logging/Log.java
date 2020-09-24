package de.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public final class Log
{
	private static final Logger logger = LoggerFactory.getLogger(Log.class);

	private static String getFormattedMessage(final Class<?> caller, final String message)
	{
		final String callerName = caller.getSimpleName();
		return String.format("[%s] %s", callerName, message);
	}

	//INFO
	public static void info(final Class<?> caller,
	                        final String message)
	{
		info(caller, message, null);
	}

	public static void info(final Class<?> caller,
	                        final Supplier<String> message)
	{
		if (logger.isInfoEnabled())
		{
			info(caller, message.get(), null);
		}
	}

	public static void info(final Class<?> caller,
	                        final Supplier<String> message,
	                        final Throwable t)
	{
		if (logger.isInfoEnabled())
		{
			info(caller, message.get(), t);
		}
	}

	public static void info(final Class<?> caller,
	                        final String message,
	                        final Throwable t)
	{
		logger.info(getFormattedMessage(caller, message), t);
	}

	//WARN
	public static void warn(final Class<?> caller,
	                        final String message)
	{
		warn(caller, message, null);
	}

	public static void warn(final Class<?> caller,
	                        final Supplier<String> message)
	{
		if (logger.isWarnEnabled())
		{
			warn(caller, message.get());
		}
	}

	public static void warn(final Class<?> caller,
	                        final Supplier<String> message,
	                        final Throwable t)
	{
		if (logger.isWarnEnabled())
		{
			warn(caller, message.get(), t);
		}
	}

	public static void warn(final Class<?> caller,
	                        final String message,
	                        final Throwable t)
	{
		logger.warn(getFormattedMessage(caller, message), t);
	}

	//ERROR
	public static void error(final Class<?> caller,
	                         final String message)
	{
		error(caller, message, null);
	}

	public static void error(final Class<?> caller,
	                         final Supplier<String> message)
	{
		if (logger.isErrorEnabled())
		{
			error(caller, message.get(), null);
		}
	}

	public static void error(final Class<?> caller,
	                         final Supplier<String> message,
	                         final Throwable t)
	{
		if (logger.isErrorEnabled())
		{
			error(caller, message.get(), t);
		}
	}

	public static void error(final Class<?> caller,
	                         final String message,
	                         final Throwable t)
	{
		logger.error(getFormattedMessage(caller, message), t);
	}

	//DEBUG
	public static void debug(final Class<?> caller,
	                         final String message)
	{
		debug(caller, message, null);
	}

	public static void debug(final Class<?> caller,
	                         final Supplier<String> message)
	{
		if (logger.isDebugEnabled())
		{
			debug(caller, message.get(), null);
		}
	}

	public static void debug(final Class<?> caller,
	                         final Supplier<String> message,
	                         final Throwable t)
	{
		if (logger.isDebugEnabled())
		{
			debug(caller, message.get(), t);
		}
	}

	public static void debug(final Class<?> caller,
	                         final String message,
	                         final Throwable t)
	{
		logger.debug(getFormattedMessage(caller, message), t);
	}

	//TRACE
	public static void trace(final Class<?> caller,
	                         final String message)
	{
		trace(caller, message, null);
	}

	public static void trace(final Class<?> caller,
	                         final Supplier<String> message)
	{
		if (logger.isTraceEnabled())
		{
			trace(caller, message.get(), null);
		}
	}

	public static void trace(final Class<?> caller,
	                         final Supplier<String> message,
	                         final Throwable t)
	{
		if (logger.isTraceEnabled())
		{
			trace(caller, message.get(), t);
		}
	}

	public static void trace(final Class<?> caller,
	                         final String message,
	                         final Throwable t)
	{
		logger.trace(getFormattedMessage(caller, message), t);
	}


	private Log()
	{
	}
}
