package com.simtriggers.fsx.event

import com.simtriggers.fsx.triggers.TriggerType
import com.simtriggers.fsx.triggers.TriggersManager
import dev.iconpippi.logger.Logger
import flightsim.simconnect.SimConnect
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

    /**
     * Handles all events
     */
    override fun handleEvent(sc: SimConnect?, e: RecvEvent) {
        Logger.debug("Triggered event, event ID:${e.eventID}, group ID:${e.groupID}")

        //Since connection open & close triggers are not event directly trigger all generic triggers
        triggersManager.triggerAll(TriggerType.GENERIC, eventDecoder.decode(e.eventID))
    }

}