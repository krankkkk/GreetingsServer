package de.example.restservice;

import de.example.database.PostGres;
import de.example.logging.Log;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.postgresql.util.PSQLException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static de.example.database.DBConstants.*;
import static org.jooq.impl.DSL.*;

@RestController
public final class GreetingController
{

	private static final String TEMPLATE = "Hello, %s!";
	private static final String FINISHED = "\"getGreeting\" finished, returning. Took ";

	private final AtomicInteger ID_COUNTER = new AtomicInteger(getLastNumber());

	public int getLastNumber()
	{
		final Result<Record1<Integer>> result = PostGres.getCreate().select(FIELD_GREETING_ID)
				.from(TABLE_GREETING)
				.orderBy(FIELD_GREETING_ID.desc())
				.limit(1)
				.fetch();
		if (result.isEmpty())
		{
			Log.warn(getClass(), "While Trying to set ID_COUNTER, the DataBase didnt return an value");
			return 0;
		}

		final int rowCount = result.getValue(0, FIELD_GREETING_ID);
		Log.debug(getClass(), () -> "Successfully updated ID_COUNTER with Integer:  " + rowCount);
		return rowCount;
	}


	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name)
	{
		final long start = System.currentTimeMillis();

		Log.trace(getClass(), () -> "Received Request \"greeting\" with name= " + name);
		Greeting greeting = new Greeting(ID_COUNTER.incrementAndGet(), String.format(TEMPLATE, name), new Timestamp(System.currentTimeMillis()));
		Log.trace(getClass(), () -> "Created Greeting: " + greeting.toString());

		insertGreeting(greeting);

		long end = System.currentTimeMillis();
		Log.trace(getClass(), () -> "Greeting finished, returning. Took " + (end - start) + " ms");

		return greeting;
	}

	@GetMapping("/getGreeting")
	public Greeting getGreeting(@RequestParam(value = "id", defaultValue = "-1") String id)
	{
		final long start = System.currentTimeMillis();
		Log.trace(getClass(), () -> "Received Request \"getGreeting\" with id= " + id);

		final int parsedID;
		try
		{
			parsedID = Integer.parseInt(id);
		}
		catch (final NumberFormatException e)
		{
			Log.error(getClass(), () -> "Exception while Parsing number from Request with id= " + id, e);
			long end = System.currentTimeMillis();
			Log.trace(getClass(), () -> FINISHED + (end - start) + " ms");
			return null;
		}

		if (parsedID < 0)
		{
			Log.trace(getClass(), () -> "parsed ID less than 0, ID= " + id);
			long end = System.currentTimeMillis();
			Log.trace(getClass(), () -> FINISHED + (end - start) + " ms");
			return null;
		}

		final List<Record> result = PostGres.getCreate().select().from(TABLE_GREETING).where(
				FIELD_GREETING_ID.eq(inline(parsedID))).fetch();

		if (result.isEmpty())
		{
			Log.trace(getClass(), () -> "DataBase returned no matching Record for ID = " + id);
			long end = System.currentTimeMillis();
			Log.trace(getClass(), () -> FINISHED + (end - start) + " ms");
			return null;
		}


		final Record record = result.get(0);
		final Greeting fromDB = new Greeting(record.get(field(PG_GREETING_ID, Integer.class)),
		                                     record.get(field(PG_GREETING_USER, String.class)),
		                                     record.get(field(PG_GREETING_TIMESTAMP, Timestamp.class)));

		long end = System.currentTimeMillis();
		Log.trace(getClass(), () -> FINISHED + (end - start) + " ms");

		return fromDB;
	}

	@GetMapping("/greetings")
	public Collection<Greeting> allGreetings()
	{
		final long start = System.currentTimeMillis();
		Log.trace(getClass(), () -> "Received Request \"greetings\" ");

		final Collection<Greeting> result = getAllGreetings();

		long end = System.currentTimeMillis();
		int size = result.size();
		Log.trace(getClass(), () -> "\"greetings\" finished, returning. Took " + (end - start) + " ms for " + size + " Greetings.");
		return result;
	}

	private Collection<Greeting> getAllGreetings()
	{

		final List<Record> results = PostGres.getCreate().select().from(table(PG_SCHEMA + '.' + PG_GREETING_TABLE_NAME)).fetch();

		final Collection<Greeting> result = new ArrayList<>(results.size());

		for (final Record record : results)
		{
			final int id = record.getValue(FIELD_GREETING_ID);
			final String content = record.getValue(FIELD_GREETING_USER);
			final Timestamp timestamp = record.getValue(FIELD_GREETING_TIMESTAMP);
			result.add(new Greeting(id, content, timestamp));
		}

		return result;
	}

	private void insertGreeting(Greeting greeting)
	{
		try
		{
			PostGres.getCreate().transaction( configuration -> {
				final int result = DSL.using(configuration).insertInto(TABLE_GREETING)
						.values(greeting.getId(), greeting.getContent(), greeting.getTimestamp())
						.execute();

				if (result == 1)
				{
					Log.trace(getClass(), () -> "Inserted Greeting: " + greeting.toString());
				}
				else
				{
					Log.warn(getClass(), "Could not Insert Greeting: " + greeting.toString());
				}
			});


		}
		catch (final DataAccessException e)
		{
			Log.error(getClass(), () -> "Exception while trying to insert into DB", e);
			if (e.getMessage().contains("Visits_pkey")
			    && e.getCause(PSQLException.class) != null
			    && e.getCause(PSQLException.class).getSQLState().equals("23505"))//Duplicate Key
			{
				Log.error(getClass(), () -> "Trying to recover from error, reinitializing ID_Counter");
				ID_COUNTER.set(getLastNumber());
				insertGreeting(new Greeting(ID_COUNTER.incrementAndGet(), greeting.getContent(), greeting.getTimestamp()));
			}
		}
	}

}