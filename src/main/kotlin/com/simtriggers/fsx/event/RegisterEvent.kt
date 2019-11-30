package com.simtriggers.fsx.event

import com.simtriggers.fsx.SimTriggers
import flightsim.simconnect.SimConnect
import java.io.IOException
import flightsim.simconnect.NotificationPriority
import kotlin.collections.ArrayList

/**
 * 03/08/2019
 * Register new events
 *
 * @author IconPippi
 */
class RegisterEvent {

    /**
     * Event storage
     */
    companion object {
        val eventCodes = ArrayList<Int>()
        var eventCount = 0
        val events = ArrayList<String>()
    }

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
     * @param groupID RegisterEvent's group
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
     * Register a new system event
     * @evenName Name of the event
     * @return Event ID
     */
    fun registerSystemEvent(eventName: String) {
        val eventID = encodeEvent(eventName, GROUP.SYSTEM)

        sc.subscribeToSystemEvent(eventID, eventName)
    }

    /**
     * Register a new menu
     * @param menuHandler Menu's handler function
     * @return Menu's event id
     */
    fun registerMenuEvent(menuHandler: String): Int {
        return encodeEvent(menuHandler, GROUP.MENU)
    }

    /**
     * Register a new text line
     * @return Text line ID
     */
    fun registerTextLineEvent(): Int {
        return encodeEvent("textLine", GROUP.TEXTLINE)
    }

    /**
     * Register a new add-ons tab menu
     * @param menuHandler Tab menu's handler function
     * @return Menu ID
     */
    fun registerTabMenu(menuHandler: String): Int {
        return encodeEvent(menuHandler, GROUP.TAB_MENU)
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
        return when(groupID) {
            GROUP.CUSTOM_SIMTRIGGERSMENU_RELOADSCRIPTS -> -1
            GROUP.CUSTOM_SIMTRIGGERSMENU -> -1
            GROUP.NULL -> 0
            GROUP.SYSTEM -> 1
            GROUP.GENERIC_TRIGGER -> 2
            GROUP.KEYBIND -> 3
            GROUP.MENU -> 4
            GROUP.TEXTLINE -> 5
            GROUP.TAB_MENU -> 6
        }
    }
}