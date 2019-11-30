package com.simtriggers.fsx.event

import flightsim.simconnect.recv.RecvEvent



/**
 * 03/08/2019
 * Event types
 *
 * @author IconPippi
 */
enum class GROUP {

    NULL,

    SYSTEM,

    KEYBIND,

    MENU,

    TAB_MENU,

    TEXTLINE,

    GENERIC_TRIGGER,

    //Specific GROUPS
    CUSTOM_SIMTRIGGERSMENU,
    CUSTOM_SIMTRIGGERSMENU_RELOADSCRIPTS;

    /**
     * Returns true if it matches the SimConnect received event
     * @param e SimConnect event
     * @return true or false
     */
    fun isEvent(e: RecvEvent): Boolean {
        return e.eventID == ordinal
    }

}