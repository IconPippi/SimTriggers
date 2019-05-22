package com.IconPippi.simtriggers.event;

import com.IconPippi.simtriggers.EVENT;
import com.IconPippi.simtriggers.wrappers.SimTriggers;

import flightsim.simconnect.SimConnect;

/*
 * This might solve a couple of problems
 */
public class EventFactory {
	
	@SuppressWarnings("unused")
	private SimConnect sc = SimTriggers.getSimulator();
	
	public void buildClientEvent(String clientEvent, String simEvent, EVENT groupID) {
		//TODO: Build event
		//sc.mapClientEventToSimEvent(encodeEvenet(clientEvent), simEvent);
		//sc.addClientEventToNotificationGroup(groupID, encodeEvenet(clientEvent);
	}
	
	public void buildInputEvent(String inputEvent, String clientEvent, EVENT groupID) {
		//TODO: Build event
		//sc.mapInputEventToClientEvent(groupID, inputEvent, encodeEvent(clientEvent));
	}
	
	protected double encodeEvent(String eventName, boolean clientOrInput) { //Must be a double cause of the size
		//TODO: Encode event
		//Encoding process:
		//If its client event always starts with 00 if it's input 11
		//Add a number to declare the groupID (Each ID can be found in the EVENT class) i.e EVENT.GROUP_THROTTLE = 11
		//Every letter will be represented as it's number in the alphabet and a 0 at the start and end i.e. H = 05, P = 16
		//The underscore will be 27
		//Example encoding "THROTTLE_INCR" string: 001120081815202012052709140318
		return 0;
	}
	
}
