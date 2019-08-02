package com.simtriggers.fsx

import com.simtriggers.fsx.scripting.ScriptLoader
import dev.iconpippi.logger.Logger
import flightsim.simconnect.SimConnect

/**
 * 01/08/2019
 * Main file
 *
 * @author IconPippi
 */

private lateinit var simConnect: SimConnect
private lateinit var st: SimTriggers

private val sc: ScriptLoader = ScriptLoader()

fun main() {
    Logger.log("Initializing application...")

    //Load scripts
    Logger.log("Loading modules' scripts...")
    sc.load()

    //Establish connection
    try {
        st = SimTriggers
        simConnect = st.sc
    } catch (e: Exception) {
        Logger.error("Could not connect to the simulator: ")
        e.printStackTrace()
        return
    }


    st.run() //Run connection thread
}