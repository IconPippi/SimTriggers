package com.IconPippi.simtriggers.util;

import java.util.Date;

import com.IconPippi.simtriggers.Main;
import com.IconPippi.simtriggers.gui.console.Console;

/**
 * This class contains some logging utilities
 * @author IconPippi
 *
 */
public class Logger {
	
	@SuppressWarnings("deprecation")
	/*
	 * Get the time string
	 */
	private static String getTime() {
		final Date date = new Date();
		return date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
	}
	
	/**
	 * Logs a given message into the console (Format: [${time} / LOG] ${message})
	 * @param message to log
	 */
	public static void log(String message) {
		System.out.println("["+getTime()+" / LOG] "+message);
		if (Main.simTriggersGUI != null) Console.println("["+getTime()+" / LOG] "+message);
	}
	
	/**
	 * Prints a message into the console formatted as an error (Format: [${time} / ERROR] ${message})
	 * @param error message
	 */
	public static void error(String message) {
		System.out.println("["+getTime()+" / ERROR] "+message);
		if (Main.simTriggersGUI != null) Console.println("["+getTime()+" / ERROR] "+message);
	}
	 
	/**
	 * Prints a message into the console as a major information (Format: [${time} / MAJOR] ${message})
	 * @param major message
	 */
	public static void major(String message) {
		System.out.println("["+getTime()+" / MAJOR] "+message);
		if (Main.simTriggersGUI != null) Console.println("["+getTime()+" / MAJOR] "+message);
	}
	
	/**
	 * Prints a message into the console as a debug information (Format: [${time} / DEBUG] ${message})
	 * @param debug message
	 */
	public static void debug(String message) {
		System.out.println("["+getTime()+" / DEBUG] "+message);
		if (Main.simTriggersGUI != null) Console.println("["+getTime()+" / DEBUG] "+message);
	}
}
