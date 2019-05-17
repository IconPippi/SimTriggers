package com.IconPippi.simtriggers;

import com.IconPippi.simtriggers.utils.Logger;

import flightsim.simconnect.SimConnect;

public class Main {
	
	public static SimConnect simConnect;
	
	public static void main(String[] args) {
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
