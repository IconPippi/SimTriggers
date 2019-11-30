package com.simtriggers.fsx.triggers

/**
 * 09/08/2019
 * System trigger, possible events passed:
 * SimStart, SimStop, Frame
 *
 * @author IconPippi
 */
class SystemTrigger(private val function: String, private val eventName: String) : Trigger(function, eventName) {

    override fun trigger(eventName: String?) {
        if (eventName == this.eventName) {
            scriptLoader.invokeFunction(function, null)
        }
    }

}