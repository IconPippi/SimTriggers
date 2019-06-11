package com.IconPippi.simtriggers.events;

import flightsim.simconnect.recv.RecvAssignedObjectID;
import flightsim.simconnect.recv.RecvEvent;
import flightsim.simconnect.recv.RecvSimObjectData;

/**
 * This class contains all enums needed to identify SimConnect events or event groups
 * @author IconPippi
 *
 */
public enum EVENT {
	
	/*
	 * Null
	 */
	NULL,
	
	/*
	 * System events
	 */
	SIM_START,
	
	/*
	 * SimTriggers tab in Flight Simulator X's add-ons menu
	 */
	ADDONSMENU_SIMTRIGGERS,
	
	/*
	 * Sub tabs for SimTriggers tab
	 */
	SIMTRIGGERSTAB_RELOADSCRIPTS,
	SIMTRIGGERSTAB_OPENGUI,
	
	/*
	 * Reload scripts event
	 */
	RELOADSCRIPTS_TEXT,
	
	/*
	 * Trigger event groups
	 */
	GROUP_MENU, //ID 11
	GROUP_THROTTLE, //ID 22
	GROUP_MIXTURE, //ID 33
	GROUP_KEYS, //ID 44
	
	//TODO: TEST
	REQUEST_PLANE_LATITUTE,
	PLANE_LATITUDE;
	//TODO: TEST
	
	/**
	 * Returns true if it matches the SimConnect received event
	 * @param e SimConnect event
	 * @return true or false
	 */
	public boolean isEvent(RecvEvent e) {
		return e.getEventID() == ordinal();
	}
	
	public boolean isRequest(RecvSimObjectData e) {
		return e.getRequestID() == ordinal();
	}
	
	public boolean isRequest(RecvAssignedObjectID e) {
		return e.getRequestID() == ordinal();
	}
	
	public EVENT getEVENT(EVENT e) {
		return e;
	}
	
}
