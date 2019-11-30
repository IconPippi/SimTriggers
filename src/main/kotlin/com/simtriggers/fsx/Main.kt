package com.simtriggers.fsx

import dev.iconpippi.logger.Logger
import flightsim.simconnect.SimConnect

/**
 * 01/08/2019
 * Main file
 *
 * @author IconPippi
 */

private lateinit var sc: SimConnect
private lateinit var st: SimTriggers

fun main() {
    Logger.log("Initializing application...")

    //Establish connection
    try {
        st = SimTriggers
        sc = SimTriggers.sc
    } catch (e: Exception) {
        Logger.error("Could not connect to the simulator: ")
        e.printStackTrace()
        return
    }

    st.run() //Run connection thread
}