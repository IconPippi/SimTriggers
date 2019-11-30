package com.simtriggers.fsx.triggers

/**
 * 05/08/2019
 * Register and unregister triggers
 *
 * @author IconPippi
 */
object TriggerRegister {

    /**
     * Register a new generic trigger
     * @param eventTrigger Name of the trigger's event
     * @param functionName Name of the function
     */
    @JvmStatic fun registerGeneric(eventTrigger: String, functionName: String) {
        TriggersManager.genericTriggers.add(GenericTrigger(functionName, eventTrigger))
    }

    /**
     * Register a new connection open trigger
     * @param functionName Name of the function
     */
    @JvmStatic fun registerConnectionOpen(functionName: String) {
        TriggersManager.connectionOpenTriggers.add(ConnectionOpenTrigger(functionName))
    }

    /**
     * Register a new connection close trigger
     * @param functionName Name of the function
     */
    @JvmStatic fun registerConnectionClose(functionName: String) {
        TriggersManager.connectionCloseTriggers.add(ConnectionCloseTrigger(functionName))
    }

    /**
     * Register a new keybind
     * @param key Keybind key
     * @param functionName Function name
     */
    @JvmStatic fun registerKey(key: String, functionName: String) {
        TriggersManager.keyTriggers.add(KeyTrigger(functionName, key))
    }

    /**
     * Register a new system event
     * @param eventName Name of the system event
     * @param functionName Name of the handler function
     */
    @JvmStatic fun registerSystem(eventName: String, functionName: String) {
        TriggersManager.systemTriggers.add(SystemTrigger(functionName, eventName))
    }

    /**
     * Unregister all triggers
     * NOTE: This function is not meant to be used outside of the source code
     */
    @JvmStatic fun unregisterAll() {
        TriggersManager.genericTriggers.clear()
        TriggersManager.connectionOpenTriggers.clear()
        TriggersManager.connectionCloseTriggers.clear()
        TriggersManager.keyTriggers.clear()
        TriggersManager.systemTriggers.clear()
    }

}