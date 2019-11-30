package com.simtriggers.fsx.triggers

/**
 * 05/08/2019
 * Called once the connection is closed
 *
 * @author IconPippi
 */
class ConnectionCloseTrigger(private val function: String) : Trigger(function, null) {

    override fun trigger(eventName: String?) {
        scriptLoader.invokeFunction(function, null)
    }

}