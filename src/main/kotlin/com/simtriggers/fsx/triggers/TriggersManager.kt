package com.simtriggers.fsx.triggers

/**
 * 05/08/2019
 * Store all the registered triggers
 *
 * @author IconPippi
 */
class TriggersManager {

    /**
     * This trigger lists contain all registered tiggers for their respective type
     */
    companion object {
        val genericTriggers = ArrayList<GenericTrigger>()
        val connectionOpenTriggers = ArrayList<ConnectionOpenTrigger>()
        val connectionCloseTriggers = ArrayList<ConnectionCloseTrigger>()
        val keyTriggers = ArrayList<KeyTrigger>()
        val systemTriggers = ArrayList<SystemTrigger>()
    }

    /**
     * Trigger all triggers of a specified type
     * @param triggerType Type of the triggers
     * @param eventName Name of the received event
     */
    fun triggerAll(triggerType: TriggerType, eventName: String?) {
        when(triggerType) {
            TriggerType.GENERIC -> genericTriggers.forEach {
                it.trigger(eventName)
            }
            TriggerType.CONNECTION_OPEN -> connectionOpenTriggers.forEach {
                it.trigger(null)
            }
            TriggerType.CONNECTION_CLOSE -> connectionCloseTriggers.forEach {
                it.trigger(null)
            }
            TriggerType.KEY -> keyTriggers.forEach {
                it.trigger(eventName)
            }
            TriggerType.SYSTEM -> systemTriggers.forEach {
                it.trigger(eventName)
            }
        }
    }

}