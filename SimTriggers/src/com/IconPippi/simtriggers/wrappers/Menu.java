package com.IconPippi.simtriggers.wrappers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.IconPippi.simtriggers.utils.Logger;

import flightsim.simconnect.SimConnect;

public class Menu {
	
	private SimConnect sc = SimTriggers.getSimulator();
	private Logger logger = new Logger();
	
	public String menuName;
	public String title;
	public List<String> options = new ArrayList<>();
	
	/**
	 * Sets the menu's name (a.k.a "title" in the SimConnect .menu method)
	 * @param Menu's name
	 */
	public void setName(String name) {
		this.menuName = name;
	}
	
	/**
	 * Sets the menu's title (a.k.a "prompt" in the SimConnect .menu method)
	 * @param Menu's title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Add a option to the menu and its action specifying the function's name
	 * @param Option name
	 * @param Function name
	 */
	public void addOption(String option, String actionMethod) { //TODO: Option action
		options.add(option);
	}
	
	/**
	 * Show the menu
	 */
	public void show() {
		try {
			sc.menu(0, 14, menuName, title, options.toArray(new String[0]));
		} catch (UnsupportedOperationException | IOException e) {
			logger.error(e.toString());
		}
	}
	
	/**
	 * Hide the menu
	 */
	public void hide() {
		try {
			sc.menu(0.0f, 14, null, null, (String[])null); //Hide the menu
		} catch (UnsupportedOperationException | IOException e) {
			logger.error(e.toString());
		}
	}
}
