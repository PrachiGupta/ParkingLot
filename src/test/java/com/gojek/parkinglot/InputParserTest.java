package com.gojek.parkinglot;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;


public class InputParserTest {
	static InputParser inputParser;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@BeforeClass
	public static void setUp() throws CommandException {
		inputParser = new InputParser();
	}

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() throws IOException {
		outContent.flush();
	}

	@Test
	public void executeCommandTestInvalidInput() throws Exception {
		inputParser.executeCommand("hello");
		Assert.assertEquals("NoMethodfound!",
				outContent.toString().trim().replace(" ", ""));
	}
	
	public void executeCommandTestValidInput() throws Exception {
		inputParser.executeCommand("status");
		Assert.assertEquals("Sorry,parkinglotisnotcreated", outContent
				.toString().trim().replace(" ", ""));
	}

}
