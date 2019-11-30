package com.simtriggers.fsx.triggers

import com.simtriggers.fsx.event.GROUP

/**
 * 04/08/2019
 * Generic trigger
 *
 * @see Event IDS: http://www.prepar3d.com/SDKv3/LearningCenter/utilities/variables/event_ids.html
 *
 * @param function Function name
 * @param eventName Name of the trigger's event
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

    override fun trigger(eventName: String?) {
        if (eventName == this.eventName)
            scriptLoader.invokeFunction(function, null)
    }

}