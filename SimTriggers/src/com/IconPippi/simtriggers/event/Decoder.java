package com.IconPippi.simtriggers.event;

public class Decoder {
	
	/**
	 * Translates a event ID to its String name
	 * @param Event ID
	 * @return Event Name
	 */
	public String decode(int event) {
		String toReturn = null;
		int count = 0; //Initialize count variable to keep track of each list's member index
		
		for (int i : EventFactory.eventList) {
			count++; //Update counter
			if (i == event) {
				toReturn = EventFactory.stringEventList.get(count-1); //Get the event's name by relying on the count pos -1
				break;
			} else {
				continue;
			}
		}
		return toReturn;
	}
	
}
