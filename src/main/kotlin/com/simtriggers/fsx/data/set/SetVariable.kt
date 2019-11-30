package com.simtriggers.fsx.data.set

import com.simtriggers.fsx.SimTriggers
import flightsim.simconnect.SimConnect
import flightsim.simconnect.SimConnectDataType
import flightsim.simconnect.wrappers.DataWrapper

/**
 * 08/08/2019
 * Simple variable setter
 *
 * @param cid Not sure, just set it to zero
 * @param dataWrapperSize Not sure, set it to a random number, I usually do 8
 *
 * @author IconPippi
 */
class SetVariable(private val cid: Int, dataWrapperSize: Int) {

    //TODO: FIX BUFFEROVERFLOW EXCEPTION

    /** SimConnect constant */
    private val sc: SimConnect = SimTriggers.sc

    /**
     * Object IDs count
     */
    companion object {
        var count: Int = 0
    }

    /** DataWrapper value */
    private val dw: DataWrapper = DataWrapper(dataWrapperSize)

    /**
     * Set a variable
     * @param varName Name of the target variable
     * @param value New value for the target variable
     * @param units Units of the target variable
     */
    fun set(varName: String, value: Any, units: String) {
        count++

        when (value) {
            is String -> {
                sc.addToDataDefinition(count, varName, units, SimConnectDataType.STRING260)
                dw.putString260(value)
            }
            is Int -> {
                sc.addToDataDefinition(count, varName, units, SimConnectDataType.INT32)
                dw.putInt32(value)
            }
            is Long -> {
                sc.addToDataDefinition(count, varName, units, SimConnectDataType.INT64)
                dw.putInt64(value)
            }
            is Float -> {
                sc.addToDataDefinition(count, varName, units, SimConnectDataType.FLOAT32)
                dw.putFloat32(value)
            }
            is Double -> {
                sc.addToDataDefinition(count, varName, units, SimConnectDataType.INT64)
                dw.putFloat64(value)
            }
        }

        sc.setDataOnSimObject(count, cid, false, 0, dw)
    }
}