package com.simtriggers.fsx.triggers

import com.simtriggers.fsx.event.RegisterEvent
import com.simtriggers.fsx.scripting.ScriptLoader

/**
 * 04/08/2019
 * This abstract class explains itself
 *
 * @author IconPippi
 */
abstract class Trigger(private val function: String, private val eventName: String?) {

    /** Event registerer */
    protected val eventRegister: RegisterEvent = RegisterEvent()

    /** Script loader */
    protected val scriptLoader: ScriptLoader = ScriptLoader()

    /**
     * Run the trigger
     * @param eventName Trigger's event
     */
    abstract fun trigger(eventName: String?)

}