package main.kotlin.com.simtriggers.fsx

import dev.iconpippi.logger.Logger
import flightsim.simconnect.SimConnect

/**
 * 01/08/2019
 * Main class
 *
 * @author IconPippi
 */
class Main {

    lateinit var simConnect: SimConnect
    lateinit var st: SimTriggers

    fun main() {
        Logger.log("Initializing application...")

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

}