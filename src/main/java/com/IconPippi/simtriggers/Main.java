package com.IconPippi.simtriggers;

import com.IconPippi.simtriggers.gui.SimTriggersGUI;
import com.IconPippi.simtriggers.utils.Logger;

import flightsim.simconnect.SimConnect;

/**
 * Hello there
 * @author IconPippi
 */
public class Main {
	
	/* SimTriggers constant */
	public static SimConnect simConnect;
	
	/* SimTriggersGUI constant */
	public static SimTriggersGUI simTriggersGUI;
	
	public static void main(String[] args) {
		simTriggersGUI = new SimTriggersGUI();
		simTriggersGUI.initialize();
		
		final Logger logger = new Logger();
		logger.log("Initializing SimTriggers");
		
		final ConnectionOpen co; //Connection class
		
		//Establish connection
		try {
			co = new ConnectionOpen();
			simConnect = co.getSimConnect();
		} catch (Exception e) {
			logger.error("Connection could not be initalized: \n"+e.toString());
			return;
		}
		
		co.run(); //Run connection thread
	} 
}
