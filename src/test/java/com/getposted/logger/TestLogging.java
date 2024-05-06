package com.getposted.logger;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.SimpleFormatter;

@Ignore
public class TestLogging {

	private static Logger logger;

	@Test
	public void testGetLogPath() {
		String expectedString = "%h/%uTestLogging#getPosted.log";
		String givenString = Logging.getLogPath();
		assertEquals(expectedString, givenString);
	}

	@Test
	public void testGetLogger() {
		logger = Logging.getLogger("TestLogging");
		assertNotNull(logger);
	}

	@Test
	public void testGetLoggerDataIsLogger() {
		logger = Logging.getLogger("TestLogging");
		assertNotNull(logger);
		assertTrue(logger instanceof Logger);
	}

	@Test
	public void testGetLoggerHandler() {
		logger = Logging.getLogger("TestLogging");
		assertNotNull(logger);

		Handler[] handlers = logger.getHandlers();
		assertTrue(handlers.length == 1);
	}

	@Test
	public void testLoggerFormatter() {
		logger = Logging.getLogger("TestLogging");
		assertNotNull(logger);

		Handler[] handlers = logger.getHandlers();

		for (Handler handler : handlers) {
			assertTrue(handler.getFormatter() instanceof SimpleFormatter);
		}
	}

	@Test
	public void testLogging() {
		logger = Logging.getLogger("TestLogging");
		logger.finest("This is testing finest log");
		logger.finer("This is testing finer log");
		logger.fine("This is testing fine log");
		logger.config("This is testing config log");
		logger.info("This is testing info log");
		logger.warning("This is testing warning log");
		logger.severe("This is testing severe log");
	}
}
