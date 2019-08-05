package com.simtriggers.fsx.triggers

/**
 * 05/08/2019
 * This class represents a connection close trigger
 *
 * @author IconPippi
 */
class ConnectionCloseTrigger(private val function: String) : Trigger(function, null) {

    /**
     * Run the trigger
     */
    override fun trigger(eventName: String?) {
        scriptLoader.invokeFunction(function, null)
    }

}