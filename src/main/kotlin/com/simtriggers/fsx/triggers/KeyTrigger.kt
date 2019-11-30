package com.simtriggers.fsx.triggers

import com.simtriggers.fsx.event.GROUP

/**
 * 05/08/2019
 * Register new key binds
 *
 * @param function Function name
 * @param keyName The key you want to bind
 *
 * @author IconPippi
 */
class KeyTrigger(private val function: String, private val keyName: String) : Trigger(function, keyName) {

    /**
     * Register the key event
     */
    init {
        eventRegister.registerClientEvent(keyName, GROUP.KEYBIND)
    }

    override fun trigger(eventName: String?) {
        if (eventName == keyName) {
            scriptLoader.invokeFunction(function, null)
        }
    }

}