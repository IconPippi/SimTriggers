package com.IconPippi.simtriggers.wrappers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.IconPippi.simtriggers.events.EventFactory;
import com.IconPippi.simtriggers.utils.Logger;

import flightsim.simconnect.SimConnect;

/**
 * This class simplifies the menu creating process
 * @author IconPippi
 */
public class Menu {
	
	/* SimConnect constant */
	private final SimConnect sc = SimTriggers.getSimulator();
	
	/* Logger */
	private final Logger logger = new Logger();
	
	/*
	 * Menu data
	 */
	private String menuName;
	private String title;
	private List<String> options = new ArrayList<>();
	private String menuHandler;
	private int menuID;
	
	/**
	 * Initialize a menu object by providing its handler
	 * @param menuHandler Handler's function name
	 */
	public Menu(String menuHandler) {
		this.menuHandler = menuHandler;
		
		menuID = new EventFactory().buildMenu(this.menuHandler);
	}
	
	/**
	 * Sets the menu's name (a.k.a "title" in the SimConnect .menu method)
	 * @param name Menu's name
	 */
	public void setName(String name) {
		this.menuName = name;
	}
	
	/**
	 * Sets the menu's title (a.k.a "prompt" in the SimConnect .menu method)
	 * @param title Menu's title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Add a option to the menu and its action specifying the function's name
	 * @param option Option name
	 */
	public void addOption(String option) {
		options.add(option);
	}
	
	/**
	 * Show the menu
	 */
	public void show() {
		try {
			sc.menu(0, menuID, menuName, title, options.toArray(new String[0]));
		} catch (UnsupportedOperationException | IOException e) {
			logger.error(e.toString());
		}
	}
	
	/**
	 * Hide the menu
	 */
	public void hide() {
		try {
			sc.menu(0.0f, menuID, null, null, (String[])null); //Hide the menu
		} catch (UnsupportedOperationException | IOException e) {
			logger.error(e.toString());
		}
	}
}
