package com.simtriggers.fsx.event

import com.simtriggers.fsx.scripting.ScriptLoader
import com.simtriggers.fsx.triggers.TriggerType
import com.simtriggers.fsx.triggers.TriggersManager
import dev.iconpippi.logger.Logger
import flightsim.simconnect.SimConnect
import flightsim.simconnect.TextResult
import flightsim.simconnect.recv.EventHandler
import flightsim.simconnect.recv.RecvEvent

/**
 * 05/08/2019
 * This class handles all simulator events
 *
 * @author IconPippi
 */
class EventHandler : EventHandler {

    /** Event decoder */
    private val eventDecoder: EventDecoder = EventDecoder()

    /** Triggers manager */
    private val triggersManager: TriggersManager = TriggersManager()

    /** Script loader */
    private val scriptLoader: ScriptLoader = ScriptLoader()

    /**
     * Handles all events
     */
    override fun handleEvent(sc: SimConnect?, e: RecvEvent) {
        Logger.debug("Triggered event, event ID:${e.eventID}, group ID:${e.groupID}")

        when {
            "${e.eventID}".startsWith("2") -> //Generic Trigger
                triggersManager.triggerAll(TriggerType.GENERIC, eventDecoder.decode(e.eventID))
            "${e.eventID}".startsWith("3") -> //KeyBind
                triggersManager.triggerAll(TriggerType.KEY, eventDecoder.decode(e.eventID))
            "${e.eventID}".startsWith("4") -> //Menu
                scriptLoader.invokeFunction(eventDecoder.decode(e.eventID), TextResult.type(e).toString())
        }
    }

}