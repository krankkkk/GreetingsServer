package de.example.database;

import org.jooq.*;
import org.jooq.Record;
import java.sql.Timestamp;

import static org.jooq.impl.DSL.*;

public final class DBConstants {

	public static final String PG_SCHEMA                = "public";
	public static final String PG_GREETING_TABLE_NAME   = "visits";
	public static final String PG_GREETING_ID           = "number";
	public static final String PG_GREETING_USER         = "user";
	public static final String PG_GREETING_TIMESTAMP    = "timestamp";

	public static final Table<Record> TABLE_GREETING    = table(PG_SCHEMA + '.' + PG_GREETING_TABLE_NAME);

	public static final Field<Integer> FIELD_GREETING_ID            = field(PG_GREETING_ID, Integer.class);
	public static final Field<String> FIELD_GREETING_USER           = field(PG_GREETING_USER, String.class);
	public static final Field<Timestamp> FIELD_GREETING_TIMESTAMP   = field(PG_GREETING_TIMESTAMP, Timestamp.class);

	private DBConstants(){}

}
