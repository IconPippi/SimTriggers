package com.simtriggers.fsx.triggers

/**
 * 05/08/2019
 * This class stores all registered triggers and provides a function to trigger them
 *
 * @author IconPippi
 */
class TriggersManager {

    companion object {
        /** Contains all registered GenericTriggers */
        val genericTriggers = ArrayList<GenericTrigger>()
        //connectionOpen
        //connectionClose
    }

    /**
     * Trigger all triggers of a specified type
     */
    fun triggerAll(triggerType: TriggerType, eventName: String) {
        when(triggerType) {
            TriggerType.GENERIC -> {
                genericTriggers.forEach {
                    it.trigger(eventName)
                }
            }
        }
    }

}