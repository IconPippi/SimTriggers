package com.IconPippi.simtriggers.wrappers;

import java.io.IOException;

import com.IconPippi.simtriggers.events.EventFactory;
import com.IconPippi.simtriggers.utils.Logger;

import flightsim.simconnect.SimConnect;
import flightsim.simconnect.TextType;

/**
 * This class simplifies the text line generating process
 * @author IconPippi
 *
 */
public class TextLine {
	
	/* SimTriggers constant */
	private final SimConnect sc = SimTriggers.getSimulator();
	
	/* Logger */
	private final Logger logger = new Logger();
	
	/*
	 * Text line data
	 */
	private String text;
	private int timeout;
	private TextType textColor;
	private int textLineID;
	
	/**
	 * Initialize a text line
	 */
	public TextLine() {
		textLineID = new EventFactory().buildTextLine();
	}
	
	/**
	 * Sets the text line's text 
	 * @param text text line's text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Sets the text line's seconds timeout
	 * @param timeout text line's timeout
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	/**
	 * Sets the text color
	 * @see {@link flightsim.simconnect.TextType} 
	 * @param color text's color
	 */
	public void setTextColor(TextType color) {
		this.textColor = color;
	}
	
	/**
	 * Show the text line
	 */
	public void show() {
		try {
			sc.text(textColor, timeout, textLineID, text);
		} catch (UnsupportedOperationException | IOException e) {
			logger.error(e.toString());
		}
	}
}
