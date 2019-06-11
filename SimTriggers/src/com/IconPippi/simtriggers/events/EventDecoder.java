package com.IconPippi.simtriggers.events;

public class EventDecoder {
	
	/**
	 * Translates a event ID to its String name
	 * @param eventID event's ID
	 * @return Event String name
	 */
	public String decode(int eventID) {
		String toReturn = null;
		int count = 0; //Initialize count variable to keep track of each list's member index
		
		for (int i : EventFactory.eventList) {
			count++; //Update counter
			if (i == eventID) {
				toReturn = EventFactory.stringEventList.get(count-1); //Get the event's name by relying on the count pos -1
				break;
			} else {
				continue;
			}
		}
		return toReturn;
	}
	
}
