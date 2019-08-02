package main.kotlin.com.simtriggers.fsx

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
object SimTriggers : OpenHandler, EventHandler, ExceptionHandler, QuitHandler, SimObjectDataTypeHandler {

    /**
     * SimConnect constants
     */
    lateinit var sc: SimConnect
    private val dt: DispatcherTask

    /**
     * SimTriggers folder
     */
    @JvmStatic val simTriggersFolder: File = File("SimTriggers")

    /**
     * Open a new connection
     */
    init {
        //Load / Create "SimTriggers folder"
        if (!simTriggersFolder.exists()) simTriggersFolder.mkdirs()

        Logger.log("Connecting to FSX")
        initConnection()
        Logger.log("Connection established")

        dt = DispatcherTask(sc)
        dt.addOpenHandler(this)
        dt.addQuitHandler(this)
        dt.addExceptionHandler(this)
        dt.addEventHandler(this)
        dt.addSimObjectDataTypeHandler(this)
    }

    @Throws(IOException::class)
    /**
     * Do some weird stuff
     */
    private fun initConnection() {
        //
        // get a configuration block if user provided a simconnect.cfg
        //
        var cfg: Configuration? = null
        try {
            cfg = ConfigurationManager.getConfiguration(0)
        } catch (cfgEx: Exception) {
            cfg = Configuration()
        }

        // fix port number (with automatic settings)
        var port = cfg!!.getInt(Configuration.PORT, -1)
        if (port == -1) {
            port = Configuration.findSimConnectPortIPv4()
            if (port <= 0) {
                port = Configuration.findSimConnectPortIPv6()
                cfg!!.setProtocol(6)
            } else {
                cfg!!.setProtocol(4)
            }
            cfg!!.setPort(port)
        }

        // fix host
        val host = cfg!!.get(Configuration.ADDRESS, null)
        if (host == null) {
            if (cfg!!.getInt(Configuration.PROTOCOL, 4) === 6)
                cfg!!.setAddress("::1")
            else
                cfg!!.setAddress("localhost")
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

    override fun handleSimObjectType(sc: SimConnect?, dataType: RecvSimObjectDataByType?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun handleQuit(sc: SimConnect?, quit: RecvQuit?) {
        Logger.major("Connection terminated")
    }

    override fun handleException(sc: SimConnect?, exception: RecvException?) {
        Logger.error(""+exception!!.exception.message+" "+exception.rawID+" "+exception.index+" "+exception.sendID)
    }

    override fun handleEvent(sc: SimConnect?, event: RecvEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun handleOpen(sc: SimConnect?, open: RecvOpen?) {
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