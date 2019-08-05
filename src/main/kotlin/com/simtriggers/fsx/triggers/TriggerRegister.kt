package com.simtriggers.fsx.triggers

/**
 * 05/08/2019
 * This object contains method used for trigger registering and unregistering
 *
 * @author IconPippi
 */
object TriggerRegister {

    /**
     * Register a new trigger
     * @eventTrigger The name of the trigger's event
     * @functionName Name of the function
     */
    @JvmStatic fun register(triggerType: TriggerType, eventTrigger: String, functionName: String) {
        //Added triggerType in preparation of connectionOpen and connectionClose triggers
        when (triggerType) {
            TriggerType.GENERIC -> TriggersManager.genericTriggers.add(GenericTrigger(functionName, eventTrigger))
        }
    }

    /**
     * Unregister all triggers
     * NOTE: This function is not meant to be used outside of the source code
     */
    @JvmStatic fun unregisterAll() {
        TriggersManager.genericTriggers.clear()
        //connectionOpen
        //connectionClose
    }

}