package com.simtriggers.fsx.event

import com.simtriggers.fsx.SimTriggers
import flightsim.simconnect.SimConnect
import java.io.IOException
import flightsim.simconnect.NotificationPriority

/**
 * 03/08/2019
 * This object provides functions for event registering purposes
 *
 * @author IconPippi
 */
object Event {

    @JvmStatic val eventCodes = ArrayList<Int>()
    @JvmStatic var eventCount = 0
    @JvmStatic val events = ArrayList<String>()

    /** SimConnect constant */
    private val sc: SimConnect = SimTriggers.sc

    /**
     * Register a new simulator event
     * @param eventName Name used to identify the event
     * @param eventID SimConnect event ID
     * @param groupID ID of the event's group
     * @throws IOException
     * @see {@link flightsim.simconnect.SimConnect#mapClientEventToSimEvent(Enum, String)}
     * @see {@link flightsim.simconnect.SimConnect#addClientEventToNotificationGroup(Enum, Enum)}
     */
    @Throws(IOException::class)
    fun registerSimulatorEvent(eventName: String, eventID: String, groupID: GROUP) {
        val event = encodeEvent(eventName, groupID)

        sc.mapClientEventToSimEvent(event, eventID)
        sc.addClientEventToNotificationGroup(getGroupID(groupID), event)
    }

    /**
     * Register a new client event
     * @param eventName Name of the new event
     * @param groupID Event's group
     * @throws IOException
     * @see {@link flightsim.simconnect.SimConnect#mapInputEventToClientEvent(Enum, String, Enum)}
     */
    @Throws(IOException::class)
    fun registerClientEvent(eventName: String, groupID: GROUP) {
        val event = encodeEvent(eventName, groupID)
        val group = getGroupID(groupID)

        sc.mapClientEventToSimEvent(event)
        sc.addClientEventToNotificationGroup(group, event, true)
        sc.setNotificationGroupPriority(group, NotificationPriority.HIGHEST)
        sc.mapInputEventToClientEvent(group, eventName, event)
        sc.setInputGroupState(group, true)
    }

    /**
     * Encode event names into Integers because SimConnect event subscribing system sucks
     * @return Encoded event ID
     */
    private fun encodeEvent(eventName: String, groupID: GROUP): Int {
        eventCount++
        val count = eventCount //Keep track of registered events
        events.add(eventName) //Add the event's name to its list
        val groupIdentifier = getGroupID(groupID) //Create group identifier variable and initialize it

        val end = Integer.valueOf(groupIdentifier.toString() + "" + count) //Final value to return
        eventCodes.add(end)

        return end
    }

    private fun getGroupID(groupID: GROUP): Int {
        var id: Int = -1

        when(groupID) {
            GROUP.NULL -> id = 1
        }

        return id
    }
}