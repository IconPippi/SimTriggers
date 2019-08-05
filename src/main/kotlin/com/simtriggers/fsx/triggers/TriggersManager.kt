package com.simtriggers.fsx.triggers

/**
 * 05/08/2019
 * This class stores all registered triggers and provides a function to trigger them
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
    }

    /**
     * Trigger all triggers of a specified type
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
        }
    }

}