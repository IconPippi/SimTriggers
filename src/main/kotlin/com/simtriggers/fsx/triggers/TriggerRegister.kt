package com.simtriggers.fsx.triggers

/**
 * 05/08/2019
 * This object contains method used for trigger registering and unregistering
 *
 * @author IconPippi
 */
object TriggerRegister {

    /**
     * Register a new generic trigger
     * @eventTrigger Name of the trigger's event
     * @functionName Name of the function
     */
    @JvmStatic fun registerGeneric(eventTrigger: String, functionName: String) {
        TriggersManager.genericTriggers.add(GenericTrigger(functionName, eventTrigger))
    }

    /**
     * Register a new connection open trigger
     * @functionName Name of the function
     */
    @JvmStatic fun registerConnectionOpen(functionName: String) {
        TriggersManager.connectionOpenTriggers.add(ConnectionOpenTrigger(functionName))
    }

    /**
     * Register a new connection close trigger
     * @functionName Name of the function
     */
    @JvmStatic fun registerConnectionClose(functionName: String) {
        TriggersManager.connectionCloseTriggers.add(ConnectionCloseTrigger(functionName))
    }

    /**
     * Unregister all triggers
     * NOTE: This function is not meant to be used outside of the source code
     */
    @JvmStatic fun unregisterAll() {
        TriggersManager.genericTriggers.clear()
        TriggersManager.connectionOpenTriggers.clear()
        TriggersManager.connectionCloseTriggers.clear()
    }

}