package com.IconPippi.simtriggers.wrappers;

import com.IconPippi.simtriggers.Main;

import flightsim.simconnect.SimConnect;

/**
 * This class represents the Simulator
 * @author IconPippi
 *
 */
public class SimTriggers {
	
	/**
	 * Gets the SimConnect instance
	 * @return SimConnect instance
	 */
	public static SimConnect getSimulator() {
		return Main.simConnect;
	}

}
