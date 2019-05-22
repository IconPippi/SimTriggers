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
	GROUP_THROTTLE, //ID 11
	GROUP_KEYBOARD,	//ID 22
	
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
	THROTTLE_90,
	
	//Key Events
	KEY_0,
	KEY_1,
	KEY_2,
	KEY_3,
	KEY_4,
	KEY_5,
	KEY_6,
	KEY_7,
	KEY_8,
	KEY_9,
	
	KEY_NUMPAD_0,
	KEY_NUMPAD_1,
	KEY_NUMPAD_2,
	KEY_NUMPAD_3,
	KEY_NUMPAD_4,
	KEY_NUMPAD_5,
	KEY_NUMPAD_6,
	KEY_NUMPAD_7,
	KEY_NUMPAD_8,
	KEY_NUMPAD_9,
	KEY_NUMPAD_10,
	KEY_NUMPAD_11,
	KEY_NUMPAD_12,
	
	KEY_ADD,
	KEY_SUBRTACT,
	KEY_MULTIPLY,
	KEY_DIVIDE,
	
	KEY_NUMPAD_EQUALS,
	
	KEY_A,
	KEY_B,
	KEY_C,
	KEY_D,
	KEY_E,
	KEY_F,
	KEY_G,
	KEY_H,
	KEY_I,
	KEY_J,
	KEY_K,
	KEY_L,
	KEY_M,
	KEY_N,
	KEY_O,
	KEY_P,
	KEY_Q,
	KEY_R,
	KEY_S,
	KEY_T,
	KEY_U,
	KEY_V,
	KEY_W,
	KEY_X,
	KEY_Y,
	KEY_Z,
	
	KEY_COMMA,
	KEY_PERIOD,
	KEY_SEMICOLON,
	KEY_COLON,
	KEY_BACKSLASH,
	KEY_DASH,
	
	KEY_NUMPAD_COMMA,
	
	KEY_APOSTROPHE,
	
	KEY_ARROW_DOWN,
	KEY_ARROW_UP,
	KEY_ARROW_LEFT,
	KEY_ARROW_RIGHT,
	
	KEY_F1,
	KEY_F2,
	KEY_F3,
	KEY_F4,
	KEY_F5,
	KEY_F6,
	KEY_F7,
	KEY_F8,
	KEY_F9,
	KEY_F10,
	KEY_F11,
	KEY_F12,
	
	KEY_LBRACKET,
	KEY_RBRACKET,
	
	KEY_SCROLL,
	
	KEY_SPACE,
	
	KEY_TAB,
	KEY_INSERT,
	KEY_LSHIFT,
	KEY_RSHIFT,
	KEY_LCONTROL,
	KEY_RCONTROL,
	KEY_RETURN,
	KEY_NUMPAD_ENTER; //TODO: I think I'm done with the key events, not sure tho (http://legacy.lwjgl.org/javadoc/org/lwjgl/input/Keyboard.html)
	
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
