package com.simtriggers.fsx.data.request

import com.simtriggers.fsx.SimTriggers
import flightsim.simconnect.SimConnect
import flightsim.simconnect.SimObjectType
import flightsim.simconnect.SimConnectDataType

/**
 * 06/08/2019
 * This class provides functions for data requesting purposes
 *
 * @author IconPippi
 */
class RequestData {

    /** SimConenct constant */
    private val sc: SimConnect = SimTriggers.sc

    /**
     * Data definitions & requests storage
     */
    companion object {
        val dataDefinitionIDs = ArrayList<Int>()
        var dataDefinitionsCount: Int = 0
        val dataDefinitions = ArrayList<String>()

        val requestIDs = ArrayList<Int>()
        var requestsCount: Int = 0
        val requests = ArrayList<String>()
    }

    /**
     * Make a new data request
     * @param variableName Name of the variable you want to retrieve data from
     * @param units Units you want the returned data to be in
     * @param callbackFunction The javascript function where your data will be delivered
     * @param dataType The data type of the variable
     */
    fun requestData(variableName: String, units: String, callbackFunction: String, dataType: String) {
        val simConnectDataType: SimConnectDataType = when (dataType) {
            "string" -> SimConnectDataType.STRING260
            "int" -> SimConnectDataType.INT32
            "long" -> SimConnectDataType.INT64
            "float" -> SimConnectDataType.FLOAT32
            "double" -> SimConnectDataType.FLOAT64
            else -> SimConnectDataType.INVALID
        }

        val encodedData: Int = encodeDataDefinition(variableName, simConnectDataType)
        val encodedRequestID: Int = encodeDataRequest(callbackFunction)

        sc.addToDataDefinition(
            encodedData, variableName, units, simConnectDataType
        )

        sc.requestDataOnSimObjectType(
            encodedRequestID,
            encodedData,
            0, SimObjectType.USER
        )
    }

    /**
     * Encode your data definition
     * @param dataDefinition Data definition string you want to encode
     * @param dataType The definition dataType
     * @return Encoded data definition
     */
    private fun encodeDataDefinition(dataDefinition: String, dataType: SimConnectDataType): Int {
        dataDefinitionsCount++
        val count = dataDefinitionsCount
        dataDefinitions.add(dataDefinition)

        val end = Integer.valueOf("${getDataTypeIDByType(dataType)}$count")
        dataDefinitionIDs.add(end)

        return end
    }

    /**
     * Encode your data request
     * @param request The request you want to encode
     * @return Encoded request
     */
    private fun encodeDataRequest(request: String): Int {
        requestsCount++
        val count = requestsCount
        requests.add(request)

        requestIDs.add(count)

        return count
    }

    /**
     * Get a unique numeric ID for each data type
     * @param dataType Data type
     * @return Numeric ID
     */
    private fun getDataTypeIDByType(dataType: SimConnectDataType): Int {
        var id = -1


        when (dataType) {
            SimConnectDataType.FLOAT32 -> id = 11
            SimConnectDataType.FLOAT64 -> id = 12
            SimConnectDataType.INITPOSITION -> id = 13
            SimConnectDataType.INT32 -> id = 14
            SimConnectDataType.INT64 -> id = 15
            SimConnectDataType.INVALID -> id = 16
            SimConnectDataType.LATLONALT -> id = 17
            SimConnectDataType.MARKERSTATE -> id = 18
            SimConnectDataType.MAX -> id = 19
            SimConnectDataType.STRING128 -> id = 21
            SimConnectDataType.STRING256 -> id = 22
            SimConnectDataType.STRING260 -> id = 23
            SimConnectDataType.STRING32 -> id = 24
            SimConnectDataType.STRING64 -> id = 25
            SimConnectDataType.STRING8 -> id = 26
            SimConnectDataType.STRINGV -> id = 27
            SimConnectDataType.WAYPOINT -> id = 28
            SimConnectDataType.XYZ -> id = 29
        }

        return id
    }

}