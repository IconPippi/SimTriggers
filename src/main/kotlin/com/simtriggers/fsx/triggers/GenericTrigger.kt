package com.simtriggers.fsx.triggers

import com.simtriggers.fsx.event.GROUP

/**
 * 04/08/2019
 * This class represents a generic trigger
 *
 * @author IconPippi
 */
class GenericTrigger(private val function: String, private val eventName: String) : Trigger(function, eventName) {

    /**
     * Register the trigger's associated event
     */
    init {
        eventRegister.registerSimulatorEvent(eventName, eventName, GROUP.GENERIC_TRIGGER)
    }

    /**
     * Run the trigger
     */
    override fun trigger(eventName: String) {
        if (eventName == this.eventName)
            scriptLoader.invokeFunction(function, null as Any)
    }

}