package com.IconPippi.simtriggers;

import flightsim.simconnect.recv.RecvAssignedObjectID;
import flightsim.simconnect.recv.RecvEvent;
import flightsim.simconnect.recv.RecvSimObjectData;

public enum EVENT {
	
	//Add-ons menu
	ADDONSMENU_FSXJS,
	
	//Module menu
	MODULEMENU_OPEN,
	MODULEMENU_RELOAD,
	MODULEMENU_ENABLE,
	MODULEMENU_DISABLE,
	
	//Reload scripts 
	RELOADSCRIPTS_TEXT;
	
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
