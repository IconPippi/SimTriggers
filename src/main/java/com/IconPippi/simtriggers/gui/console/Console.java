package com.IconPippi.simtriggers.gui.console;

import java.io.IOException;

import javax.swing.JTextArea;

/**
 * This class represents the console
 * @author IconPippi
 * 
 */
public class Console {
	
	private static JTextAreaOutputStream consoleStream;
	
	/**
	 * Open a new console output stream
	 * @param console console swing component
	 */
	public Console(JTextArea console) {
		consoleStream = new JTextAreaOutputStream(console);
	}
	
	/**
	 * Print a line into the console
	 * @param line line to print
	 */
	public static void println(String line) {
		consoleStream.println(line);
	}
	
	/**
	 * Clear the console
	 */
	public static void clear() {
		consoleStream.clear();
	}
	
	/**
	 * Close the console stream
	 */
	public static void close() {
		try {
			consoleStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
