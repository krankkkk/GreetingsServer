package de.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class Starter
{
	public static void main(String[] args)
	{
		Locale.setDefault(Locale.US);
		SpringApplication.run(Starter.class, args);
	}
}
