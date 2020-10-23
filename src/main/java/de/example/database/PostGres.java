package de.example.database;

import de.example.logging.Log;
import de.example.util.StringUtils;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.lang.NonNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class PostGres
{

	private final Connection connection;
	private static PostGres INSTANCE = null;
	private static DSLContext CONTEXT = null;

	public static PostGres getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = ofDefault();
		}
		return INSTANCE;
	}

	public static DSLContext getContext()
	{
		if (CONTEXT == null)
		{
			CONTEXT = DSL.using(getInstance().getConnection(), SQLDialect.POSTGRES);
		}
		return CONTEXT;
	}


	private PostGres(@NonNull final String host,
	                 @NonNull final String port,
	                 @NonNull final String schema,
	                 @NonNull final String user,
	                 @NonNull final String password)
			throws SQLException
	{
		final String url = String.format("jdbc:postgresql://%s:%s/%s", host, port, schema);
		this.connection = DriverManager.getConnection(url, user, password);
	}


	public static PostGres of(@NonNull final String host,
	                          @NonNull final String port,
	                          @NonNull final String schema,
	                          @NonNull final String user,
	                          @NonNull final String password)
	{
		{

			if (StringUtils.isAnyEmpty(true, host, port, schema, user, password))
			{
				Log.error(PostGres.class, ".of() called with invalid Parameters");
				Log.trace(PostGres.class,
				          "Invalid Parameters: host=" + host + "; port=" + port + "; schema=" + schema + "; user=" + user + "; password=" + password);
				throw new IllegalArgumentException();
			}

			try
			{
				return new PostGres(host,
				                    port,
				                    schema,
				                    user,
				                    password);
			}
			catch (final SQLException e)
			{
				Log.error(PostGres.class, "Exception while trying to create Connection to PostGresDB", e);
				throw new IllegalArgumentException(e);
			}
		}
	}

	private static PostGres ofDefault()
	{
		return PostGres.of("localhost",
		                   "5432",
		                   "Spring",
		                   "SpringUser",
		                   "SpringPassword");
	}


	public Connection getConnection()
	{
		return connection;
	}
}
