package com.simtriggers.fsx.event

/**
 * 03/08/2019
 * This object contains a single function used for decoding event IDs into their String names
 *
 * @author IconPippi
 */
object EventDecoder {

    /**
     * Decode an event ID into his String name
     * @param eventID event's ID
     * @return Event String name
     */
    fun decode(eventID: Int): String? {
        var eventName: String? = null
        var count = 0 //Initialize count variable to keep track of each list's member index

        Event.eventCodes.forEach { i ->
            count++ //Update counter
            if (i == eventID) {
                eventName =
                    Event.events[count - 1] //Get the event's name by relying on the count pos -1
            } else {
                return@forEach
            }
        }
        return eventName
    }

}