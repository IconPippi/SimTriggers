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
	
	//Even groups
	GROUP_THROTTLE,
	
	//Throttle events
	THROTTLE_SET,
	THROTTLE_FULL,
	THROTTLE_CUT,
	THROTTLE_INCR,
	THROTTLE_INCR_SMALL,
	THROTTLE_DECR,
	THROTTLE_DECR_SMALL,
	
	THROTTLE1_SET,
	THROTTLE1_FULL,
	THROTTLE1_CUT,
	THROTTLE1_INCR,
	THROTTLE1_INCR_SMALL,
	THROTTLE1_DECR,
	THROTTLE1_DECR_SMALL,
	
	THROTTLE2_SET,
	THROTTLE2_FULL,
	THROTTLE2_CUT,
	THROTTLE2_INCR,
	THROTTLE2_INCR_SMALL,
	THROTTLE2_DECR,
	THROTTLE2_DECR_SMALL,
	
	THROTTLE3_SET,
	THROTTLE3_FULL,
	THROTTLE3_CUT,
	THROTTLE3_INCR,
	THROTTLE3_INCR_SMALL,
	THROTTLE3_DECR,
	THROTTLE3_DECR_SMALL,
	
	THROTTLE4_SET,
	THROTTLE4_FULL,
	THROTTLE4_CUT,
	THROTTLE4_INCR,
	THROTTLE4_INCR_SMALL,
	THROTTLE4_DECR,
	THROTTLE4_DECR_SMALL,
	
	THROTTLE_10,
	THROTTLE_20,
	THROTTLE_30,
	THROTTLE_40,
	THROTTLE_50,
	THROTTLE_60,
	THROTTLE_70,
	THROTTLE_80,
	THROTTLE_90;
	
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
