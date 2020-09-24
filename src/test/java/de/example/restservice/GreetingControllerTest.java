package de.example.restservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class GreetingControllerTest
{
	@Autowired
	GreetingController controller;

	@DisplayName("Test Spring @Autowired Integration")
	@Test()
	void testGreeting()
	{
		int lastNumber = controller.getLastNumber();
		final Greeting actual = controller.greeting(null);
		final Greeting expected = new Greeting(lastNumber+1, "Hello, null!", actual.getTimestamp());
		assertEquals(expected, actual);
		assertNotEquals(expected, controller.greeting(null));
		assertEquals(lastNumber+3, controller.greeting(null).getId());
		assertEquals("Hello, User123!", controller.greeting("User123").getContent());
	}
}