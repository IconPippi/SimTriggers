package com.simtriggers.fsx.triggers

import com.simtriggers.fsx.event.EventRegisterer
import com.simtriggers.fsx.scripting.ScriptLoader

/**
 * 04/08/2019
 * This abstract class explains itself
 *
 * @author IconPippi
 */
abstract class Trigger(private val function: String, private val eventName: String?) {

    /** Event registerer */
    protected val eventRegister: EventRegisterer = EventRegisterer

    /** Script loader */
    protected val scriptLoader: ScriptLoader = ScriptLoader()

    /** Run the trigger */
    protected abstract fun trigger(eventName: String)

}