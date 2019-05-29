package com.IconPippi.simtriggers;

import flightsim.simconnect.recv.RecvAssignedObjectID;
import flightsim.simconnect.recv.RecvEvent;
import flightsim.simconnect.recv.RecvSimObjectData;

public enum EVENT {
	
	//Null
	NULL,
	
	//Add-ons menu
	ADDONSMENU_SIMTRIGGERS,
	
	//SimTriggers tab
	SIMTRIGGERSTAB_RELOADSCRIPTS,
	SIMTRIGGERSTAB_OPENGUI,
	
	//Module menu
	MODULEMENU_OPEN,
	MODULEMENU_RELOAD,
	MODULEMENU_ENABLE,
	MODULEMENU_DISABLE,
	
	//Reload scripts 
	RELOADSCRIPTS_TEXT,
	
	//Event groups
	GROUP_MENU, //ID 11
	GROUP_THROTTLE, //ID 22
	GROUP_MIXTURE, //ID 33
	GROUP_KEYS; //ID 44

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
