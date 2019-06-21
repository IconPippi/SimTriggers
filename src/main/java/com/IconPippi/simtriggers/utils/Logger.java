package com.IconPippi.simtriggers.utils;

import java.util.Date;

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
	private String getTime() {
		final Date date = new Date();
		return date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
	}
	
	/**
	 * Logs a given message into the console (Format: [${time} / LOG] ${message})
	 * @param message to log
	 */
	public void log(String message) {
		System.out.println("["+getTime()+" / LOG] "+message);
	}
	
	/**
	 * Prints a message into the console formatted as an error (Format: [${time} / ERROR] ${message})
	 * @param error message
	 */
	public void error(String message) {
		System.out.println("["+getTime()+" / ERROR] "+message);
	}
	 
	/**
	 * Prints a message into the console as a major information (Format: [${time} / MAJOR] ${message})
	 * @param major message
	 */
	public void major(String message) {
		System.out.println("["+getTime()+" / MAJOR] "+message);
	}
}
