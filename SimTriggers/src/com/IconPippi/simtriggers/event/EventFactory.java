package com.IconPippi.simtriggers.event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.IconPippi.simtriggers.EVENT;
import com.IconPippi.simtriggers.wrappers.SimTriggers;

import flightsim.simconnect.NotificationPriority;
import flightsim.simconnect.SimConnect;

public class EventFactory {
	
	protected static List<Integer> eventList = new ArrayList<>(); //Event IDs
	protected static int eventCount = 0;
	protected static List<String> stringEventList = new ArrayList<>(); //Event names

	private final SimConnect sc = SimTriggers.getSimulator();
	
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
	 * @param Name of the input event
	 * @param Name of the client event
	 * @param Event's enum group
	 * @throws IOException
	 * @see {@link flightsim.simconnect.SimConnect#mapInputEventToClientEvent(Enum, String, Enum)}
	 */
	public void buildInputEvent(String inputEvent, EVENT groupID) throws IOException {
		int event = registerEvent(inputEvent, groupID);
		int group = getGroupID(groupID);
		
		sc.mapClientEventToSimEvent(event);
		sc.addClientEventToNotificationGroup(group, event, true);
		sc.setNotificationGroupPriority(group, NotificationPriority.HIGHEST);
		sc.mapInputEventToClientEvent(group, inputEvent, event);
		sc.setInputGroupState(group, true);
	}
	
	/**
	 * Make a new menu event ID, marked by 00 at the start of it's ID
	 * @param Handler's function name
	 * @return Menu ID
	 */
	public int buildMenu(String menuHandler) {
		return registerEvent(menuHandler, EVENT.GROUP_MENU);
	}
	
	private int registerEvent(String inputEvent, EVENT groupID) {
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
		case GROUP_MENU:
			groupIdentifier = 11;
			break;
		case GROUP_THROTTLE:
			groupIdentifier = 22;
			break;
		case GROUP_MIXTURE:
			groupIdentifier = 33;
			break;
		case GROUP_KEYS:
			groupIdentifier = 44;
			break;
		}
		
		return groupIdentifier;
	}
}
