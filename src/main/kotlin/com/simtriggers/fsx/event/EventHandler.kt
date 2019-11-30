package com.simtriggers.fsx.event

import com.simtriggers.fsx.scripting.ScriptLoader
import com.simtriggers.fsx.simulator.TextLine
import com.simtriggers.fsx.triggers.TriggerType
import com.simtriggers.fsx.triggers.TriggersManager
import dev.iconpippi.logger.Logger
import flightsim.simconnect.SimConnect
import flightsim.simconnect.TextResult
import flightsim.simconnect.TextType
import flightsim.simconnect.recv.EventFrameHandler
import flightsim.simconnect.recv.EventHandler
import flightsim.simconnect.recv.RecvEvent
import flightsim.simconnect.recv.RecvEventFrame

/**
 * 05/08/2019
 * This class handles all simulator events
 *
 * @author IconPippi
 */
class EventHandler : EventHandler, EventFrameHandler {

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

        if (GROUP.CUSTOM_SIMTRIGGERSMENU_RELOADSCRIPTS.isEvent(e)) {
            val tl = TextLine()
            tl.setText("Reloading Scripts...")
            tl.setTextColor(TextType.SCROLL_BLUE)
            tl.setTimeout(3.toFloat())
            tl.show()
            scriptLoader.load()
        }

        when {
            "${e.eventID}".startsWith("1") -> //System event
                triggersManager.triggerAll(TriggerType.SYSTEM, eventDecoder.decode(e.eventID))
            "${e.eventID}".startsWith("2") -> //Generic Trigger
                triggersManager.triggerAll(TriggerType.GENERIC, eventDecoder.decode(e.eventID))
            "${e.eventID}".startsWith("3") -> //KeyBind
                triggersManager.triggerAll(TriggerType.KEY, eventDecoder.decode(e.eventID))
            "${e.eventID}".startsWith("4") -> //Menu
                scriptLoader.invokeFunction(eventDecoder.decode(e.eventID), TextResult.type(e).toString())
            "${e.eventID}".startsWith("6") -> //Tab Menu
                scriptLoader.invokeFunction(eventDecoder.decode(e.eventID), null)
        }
    }

    /**
     * Handles each frame
     */
    override fun handleEventFrame(sc: SimConnect?, e: RecvEventFrame?) {
        //The only event which gets handled by this function is a system trigger marked by the "Frame" event name
        triggersManager.triggerAll(TriggerType.SYSTEM, "Frame")
    }

}