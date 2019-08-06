package com.simtriggers.fsx

import com.simtriggers.fsx.data.DataHandler
import com.simtriggers.fsx.event.EventHandler
import com.simtriggers.fsx.event.GROUP
import com.simtriggers.fsx.module.ModulesManager
import com.simtriggers.fsx.scripting.ScriptLoader
import com.simtriggers.fsx.triggers.TriggerType
import com.simtriggers.fsx.triggers.TriggersManager
import dev.iconpippi.logger.Logger
import flightsim.simconnect.SimConnect
import flightsim.simconnect.config.Configuration
import flightsim.simconnect.recv.*
import flightsim.simconnect.config.ConfigurationManager
import java.io.File
import java.io.IOException

/**
 * 01/08/2019
 * This class handles the communication with the simulator
 *
 * @author IconPippi
 */
class SimTriggers : OpenHandler, ExceptionHandler, QuitHandler {

    companion object {
        /** Simconnect constant */
        lateinit var sc: SimConnect

        /** SimTriggers folder */
        val simTriggersFolder: File = File("SimTriggers")
    }

    /** SimConnect dispatcher task */
    private val dt: DispatcherTask

    /** Triggers manager */
    private val tm: TriggersManager = TriggersManager()

    /**
     * Modules constants
     */
    private val sl: ScriptLoader = ScriptLoader()
    private val mm: ModulesManager = ModulesManager()

    /**
     * Open a new connection
     */
    init {
        //Load / Create "SimTriggers folder"
        if (!simTriggersFolder.exists()) simTriggersFolder.mkdirs()

        Logger.log("Connecting to FSX")
        initConnection()
        Logger.log("Connection established")

        sc.subscribeToSystemEvent(GROUP.SIM_START, "SimStart")

        dt = DispatcherTask(sc)
        dt.addOpenHandler(this)
        dt.addQuitHandler(this)
        dt.addExceptionHandler(this)
        dt.addEventHandler(EventHandler())
        dt.addSimObjectDataTypeHandler(DataHandler())

        //Load scripts
        Logger.log("Loading modules...")
        mm.initModules()
        Logger.log("Loading scripts...")
        sl.load()
    }

    @Throws(IOException::class)
    /**
     * Do some weird stuff
     */
    private fun initConnection() {
        //
        // get a configuration block if user provided a simconnect.cfg
        //
        val cfg: Configuration? = try {
            ConfigurationManager.getConfiguration(0)
        } catch (cfgEx: Exception) {
            Configuration()
        }

        // fix port number (with automatic settings)
        var port = cfg!!.getInt(Configuration.PORT, -1)
        if (port == -1) {
            port = Configuration.findSimConnectPortIPv4()
            if (port <= 0) {
                port = Configuration.findSimConnectPortIPv6()
                cfg.setProtocol(6)
            } else {
                cfg.setProtocol(4)
            }
            cfg.setPort(port)
        }

        // fix host
        val host = cfg.get(Configuration.ADDRESS, null)
        if (host == null) {
            if (cfg.getInt(Configuration.PROTOCOL, 4) == 6)
                cfg.setAddress("::1")
            else
                cfg.setAddress("localhost")
        }

        // force simconnect version 0x3
        sc = SimConnect("SimTriggers", cfg, 0x3)
    }

    /**
     * Run the connection thread
     */
    fun run() {
        dt.run()
    }

    override fun handleQuit(sc: SimConnect?, quit: RecvQuit?) {
        tm.triggerAll(TriggerType.CONNECTION_CLOSE, null)

        Logger.major("Connection terminated")
    }

    override fun handleException(sc: SimConnect?, exception: RecvException?) {
        Logger.error(""+exception!!.exception.message+" "+exception.rawID+" "+exception.index+" "+exception.sendID)
    }

    override fun handleOpen(sc: SimConnect?, open: RecvOpen?) {
        tm.triggerAll(TriggerType.CONNECTION_OPEN, null)

        Logger.major("Connected to " +
                open!!.applicationName +
                " version " +
                open.applicationVersionMajor + "." +
                open.applicationVersionMinor + "." +
                open.applicationBuildMajor + "." +
                open.applicationBuildMinor +
                " simconnect " +
                open.simConnectVersionMajor + "." +
                open.simConnectVersionMinor + "." +
                open.simConnectBuildMajor + "." +
                open.simConnectBuildMinor
        )
        Logger.major("Protocol version: " + open.version)
    }

}