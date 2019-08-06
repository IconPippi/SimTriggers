package com.simtriggers.fsx.data

import com.simtriggers.fsx.data.request.DataRequestDecoder
import com.simtriggers.fsx.scripting.ScriptLoader
import dev.iconpippi.logger.Logger
import flightsim.simconnect.SimConnect
import flightsim.simconnect.recv.RecvSimObjectDataByType
import flightsim.simconnect.recv.SimObjectDataTypeHandler

/**
 * 06/08/2019
 * This class handles all data coming from the simulator
 *
 * @author IconPippi
 */
class DataHandler : SimObjectDataTypeHandler {

    /** Data decoder constant */
    private val decoder: DataRequestDecoder = DataRequestDecoder()

    /** Script loader constant */
    private val scriptLoader: ScriptLoader = ScriptLoader()

    /**
     * Handle received data
     */
    override fun handleSimObjectType(sc: SimConnect, data: RecvSimObjectDataByType) {
        Logger.debug("Data received: request ID:${data.requestID}, definition ID:${data.defineID}")

        /* Sort the data by its type and invoke the callback functions */
        when {
            //Float 64
            "${data.defineID}".startsWith("12") -> scriptLoader.invokeFunction(
                decoder.decodeRequestID(data.requestID), "${data.dataFloat64}")

            //Int 64
            "${data.defineID}".startsWith("15") -> scriptLoader.invokeFunction(
                decoder.decodeRequestID(data.requestID), "${data.dataInt64}")

            //String 260
            "${data.defineID}".startsWith("23") -> scriptLoader.invokeFunction(
                decoder.decodeRequestID(data.requestID), data.dataString260)

            //Float 32
            "${data.defineID}".startsWith("11") -> scriptLoader.invokeFunction(
                decoder.decodeRequestID(data.requestID), "${data.dataFloat32}")

            //Init position
            "${data.defineID}".startsWith("13") -> scriptLoader.invokeFunction(
                decoder.decodeRequestID(data.requestID), "${data.initPosition}")

            //Int 32
            "${data.defineID}".startsWith("14") -> scriptLoader.invokeFunction(
                decoder.decodeRequestID(data.requestID), "${data.dataInt32}")

            //Invalid
            "${data.defineID}".startsWith("16") -> scriptLoader.invokeFunction(
                decoder.decodeRequestID(data.requestID), null)

            //Latitude, longitude, altitude
            "${data.defineID}".startsWith("17") -> scriptLoader.invokeFunction(
                decoder.decodeRequestID(data.requestID), "${data.latLonAlt}")

            //Marker state
            "${data.defineID}".startsWith("18") -> scriptLoader.invokeFunction(
                decoder.decodeRequestID(data.requestID), "${data.markerState}")

            //Maximum
            "${data.defineID}".startsWith("19") -> scriptLoader.invokeFunction(
                decoder.decodeRequestID(data.requestID), null)

            //String 128
            "${data.defineID}".startsWith("21") -> scriptLoader.invokeFunction(
                decoder.decodeRequestID(data.requestID), data.dataString128)

            //String 256
            "${data.defineID}".startsWith("22") -> scriptLoader.invokeFunction(
                decoder.decodeRequestID(data.requestID), data.dataString256)

            //String 32
            "${data.defineID}".startsWith("24") -> scriptLoader.invokeFunction(
                decoder.decodeRequestID(data.requestID), data.dataString32)

            //String 64
            "${data.defineID}".startsWith("25") -> scriptLoader.invokeFunction(
                decoder.decodeRequestID(data.requestID), data.dataString64)

            //String 8
            "${data.defineID}".startsWith("26") -> scriptLoader.invokeFunction(
                decoder.decodeRequestID(data.requestID), data.dataString8)

            //String V
            "${data.defineID}".startsWith("27") -> scriptLoader.invokeFunction(
                decoder.decodeRequestID(data.requestID), data.dataStringV)

            //Way point
            "${data.defineID}".startsWith("28") -> scriptLoader.invokeFunction(
                decoder.decodeRequestID(data.requestID), data.waypoint)

            //X, Y, Z
            "${data.defineID}".startsWith("29") -> scriptLoader.invokeFunction(
                decoder.decodeRequestID(data.requestID), data.xyz)
        }
    }

}