package com.simtriggers.fsx.triggers

/**
 * 05/08/2019
 * This class represents a connection open trigger
 *
 * @author IconPippi
 */
class ConnectionOpenTrigger(private val function: String) : Trigger(function, null) {

    /**
     * Run the trigger
     */
    override fun trigger(eventName: String?) {
        scriptLoader.invokeFunction(function, null)
    }

}