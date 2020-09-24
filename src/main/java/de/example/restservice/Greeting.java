package de.example.restservice;

import java.sql.Timestamp;
import java.util.Objects;

public final class Greeting
{
	private final int id;
	private final String content;
	private final Timestamp timestamp;

	public Greeting(final int greetingID,
	                final String greetingContent,
	                final Timestamp greetingTimeStamp)
	{
		this.id = greetingID;
		this.content = greetingContent;
		this.timestamp = greetingTimeStamp;
	}

	public int getId()
	{
		return id;
	}

	public String getContent()
	{
		return content;
	}

	public Timestamp getTimestamp()
	{
		return timestamp;
	}

	@Override
	public String toString()
	{
		return "Greeting[ID: \"" + this.id + "\" Content: \"" + this.content + "\" TimeStamp: \"" + this.timestamp + "\"]";
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Greeting))
		{
			return false;
		}
		Greeting other = (Greeting) o;
		return this.id == other.id &&
		       Objects.equals(this.timestamp, other.timestamp) &&
		       Objects.equals(this.content, other.content);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(this.id, this.content, this.timestamp);
	}
}
