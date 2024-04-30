package com.getposted.logger;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.Formatter;
import java.util.logging.FileHandler;
import java.util.logging.ConsoleHandler;
import java.util.logging.SimpleFormatter;

import java.io.IOException;

public class Logging {

	private static String logDirectory = "%h/";
	private static String logFileName = "getPosted.log";
	private static Formatter formatter = new SimpleFormatter();

	public static Logger getLogger(String className){
		Logger logger = null;
		Handler handler = null;

		// create the handler and config the handler
		try{
			FileHandler fileHandler = new FileHandler(Logging.getLogPath(),true);
			fileHandler.setLevel(Level.ALL);
			fileHandler.setFormatter(Logging.formatter);
			handler = fileHandler;
		}
		catch(IOException e){
			ConsoleHandler consoleHandler = new ConsoleHandler();
			consoleHandler.setFormatter(new SimpleFormatter());
			consoleHandler.setLevel(Level.ALL);
			handler = consoleHandler;
		}

		// get the logger object
		logger = Logger.getLogger(className);

		// config the logger object
		logger.setUseParentHandlers(false);
		logger.addHandler(handler);
		logger.setLevel(Level.ALL);

		return logger;
	}

	public static String getLogPath(){
		return Logging.logDirectory+"%u"+logFileName;
	}
}
