package com.simtriggers.fsx.triggers

/**
 * 09/08/2019
 * This class represents a system type trigger
 */
class SystemTrigger(private val function: String, private val eventName: String) : Trigger(function, eventName) {

    /**
     * Run the trigger
     */
    override fun trigger(eventName: String?) {
        if (eventName == this.eventName) {
            scriptLoader.invokeFunction(function, null)
        }
    }

}