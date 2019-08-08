package com.simtriggers.fsx.simulator.objects

import com.simtriggers.fsx.data.request.RequestData
import flightsim.simconnect.SimConnectDataType

/**
 * 07/08/2019
 * This simulator object represents the player's aircraft
 *
 * @author IconPippi
 */
object Aircraft {

    /** Data requestData constant */
    private val requestData: RequestData = RequestData()

    /**
     * Open a new altitude requestData
     * @param callbackFunction Function where the data will be delivered
     */
    @JvmStatic fun requestAltitude(callbackFunction: String) {
        requestData.requestData("Plane Altitude", "feet", callbackFunction, SimConnectDataType.FLOAT64)
    }

    /**
     * Open a requestData for the aircraft latitude
     * @param callbackFunction Function where the data will be delivered
     */
    @JvmStatic fun requestLatitude(callbackFunction: String) {
        requestData.requestData("Plane Latitude", "radians", callbackFunction, SimConnectDataType.FLOAT64)
    }

    /**
     * Open a requestData for the aircraft longitude
     * @param callbackFunction Function where the data will be delivered
     */
    @JvmStatic fun requestLongitude(callbackFunction: String) {
        requestData.requestData("Plane Longitude", "radians", callbackFunction, SimConnectDataType.FLOAT64)
    }

    /**
     * Open a requestData for the aircraft heading
     * @param callbackFunction Function where the data will be delivered
     */
    @JvmStatic fun requestHeading(callbackFunction: String) {
        requestData.requestData("Plane Heading Degrees True", "radians", callbackFunction, SimConnectDataType.FLOAT64)
    }

    /**
     * Open a requestData for the aircraft pitch angle
     * @param callbackFunction Function where the data will be delivered
     */
    @JvmStatic fun requestPitch(callbackFunction: String) {
        requestData.requestData("Plane Pitch Degrees", "radians", callbackFunction, SimConnectDataType.FLOAT64)
    }

    /**
     * Open a requestData for the aircraft bank angle
     * @param callbackFunction Function where the data will be delivered
     */
    @JvmStatic fun requestBank(callbackFunction: String) {
        requestData.requestData("Plane Bank Degrees", "radians", callbackFunction, SimConnectDataType.FLOAT64)
    }

    /**
     * Open a requestData for the surface type the aircraft is flying over
     * NOTE: The units for this variable is listed as "enum". That means opening a integer requestData is going to return a bunch of numbers which will indicate different surface types.
     * Here's a list of them:
     *
     * - 0 = Concrete
     * - 1 = Grass
     * - 2 = Water
     * - 3 = Grass_bumpy
     * - 4 = Asphalt
     * - 5 = Short_grass
     * - 6 = Long_grass
     * - 7 = Hard_turf
     * - 8 = Snow
     * - 9 = Ice
     * - 10 = Urban
     * - 11 = Forest
     * - 12 = Dirt
     * - 13 = Coral
     * - 14 = Gravel
     * - 15 = Oil_treated
     * - 16 = Steel_mats
     * - 17 = Bituminus
     * - 18 = Brick
     * - 19 = Macadam
     * - 20 = Planks
     * - 21 = Sand
     * - 22 = Shale
     * - 23 = Tarmac
     * - 24 = Wright_flyer_track
     *
     * @param callbackFunction Function where the data will be delivered
     */
    @JvmStatic fun requestSurfaceType(callbackFunction: String) {
        requestData.requestData("Surface Type", "enum", callbackFunction, SimConnectDataType.INT32)
    }

    /**
     * Open a requestData for the aircraft airspeed
     * @param callbackFunction Function where the data will be delivered
     */
    @JvmStatic fun requestAirspeed(callbackFunction: String) {
        requestData.requestData("Airspeed True", "knots", callbackFunction, SimConnectDataType.FLOAT64)
    }

    /**
     * Open a requestData for the aircraft mach airspeed
     * @param callbackFunction Function where the data will be delivered
     */
    @JvmStatic fun requestAirspeedMach(callbackFunction: String) {
        requestData.requestData("Airspeed Mach", "mach", callbackFunction, SimConnectDataType.FLOAT64)
    }

    /**
     * Opens a requestData for the aircraft overspeed warning status
     * @param callbackFunction Function where the data will be delivered
     */
    @JvmStatic fun requestOverspeedWarningStatus(callbackFunction: String) {
        requestData.requestData("Overspeed Warning", "bool", callbackFunction, SimConnectDataType.INT32)
    }

    /**
     * Open a requestData for the aircraft stall warning status
     * @param callbackFunction Function where the data will be delivered
     */
    @JvmStatic fun requestStallWarningStatus(callbackFunction: String) {
        requestData.requestData("Stall Warning", "bool", callbackFunction, SimConnectDataType.INT32)
    }

}