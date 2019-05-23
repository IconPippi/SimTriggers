package com.IconPippi.simtriggers.event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.IconPippi.simtriggers.EVENT;
import com.IconPippi.simtriggers.wrappers.SimTriggers;

import flightsim.simconnect.SimConnect;

public class EventFactory {
	
	public static List<Integer> eventList = new ArrayList<>();
	
	protected static int eventCount = 0;
	protected static List<String> stringEventList = new ArrayList<>();

	private SimConnect sc = SimTriggers.getSimulator();
	
	/**
	 * Make a new client event that can be handled in ConnectionOpen class i.e. a throttle event
	 * @param Name of the client event
	 * @param Name of the simulator event
	 * @param Event's enum group
	 * @throws IOException
	 * @see {@link flightsim.simconnect.SimConnect#mapClientEventToSimEvent(Enum, String)}
	 * @see {@link flightsim.simconnect.SimConnect#addClientEventToNotificationGroup(Enum, Enum)}
	 */
	public void buildClientEvent(String clientEvent, String simEvent, EVENT groupID) throws IOException {
		int event = registerEvent(clientEvent, groupID);
		
		sc.mapClientEventToSimEvent(event, simEvent);
		sc.addClientEventToNotificationGroup(getGroupID(groupID), event);
	}
	
	/**
	 * Make a new input event that can be handled in ConnectionOpen class i.e. a keybind event
	 * @param Name of the client event
	 * @param Name of the simulator event
	 * @param Event's enum group
	 * @throws IOException
	 * @see {@link flightsim.simconnect.SimConnect#mapInputEventToClientEvent(Enum, String, Enum)}
	 */
	public void buildInputEvent(String inputEvent, String clientEvent, EVENT groupID) throws IOException {
		int event = registerEvent(inputEvent, groupID);
		
		sc.mapInputEventToClientEvent(getGroupID(groupID), inputEvent, event);
	}
	
	protected int registerEvent(String inputEvent, EVENT groupID) {
		eventCount++;
		int count = eventCount; //Keep track of registered events
		stringEventList.add(inputEvent); //Add the event's name to its list
		int groupIdentifier = getGroupID(groupID); //Create group identifier variable and initialize it
		
		int end = Integer.valueOf(groupIdentifier+""+count); //Final value to return
		
		eventList.add(end);
		
		return end;
	}
	
	@SuppressWarnings("incomplete-switch")
	private int getGroupID(EVENT groupID) {
		int groupIdentifier = -1;
		
		switch (groupID) {
		case GROUP_THROTTLE:
			groupIdentifier = 11;
			break;
		case GROUP_KEYBOARD:
			groupIdentifier = 22;
			break;
		}
		
		return groupIdentifier;
	}
}