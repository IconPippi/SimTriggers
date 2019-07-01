package com.IconPippi.simtriggers.wrappers;

import java.io.IOException;

import com.IconPippi.simtriggers.data.request.DataRequest;

import flightsim.simconnect.SimConnectDataType;

/**
 * This class wraps all aircraft variables
 * @author IconPippi
 *
 */
public class Aircraft {
	//TODO: Add more methods
	
	/* DataRequest constant */
	private final static DataRequest dataRequest = new DataRequest();
	
	/**
	 * Open a request for the aircraft altitude
	 * @param callbackFunction function where the data will be delivered
	 * @throws IOException 
	 */
	public static void requestAltitude(String callbackFunction) throws IOException {
		dataRequest.requestData("Plane Altitude", "feet", callbackFunction, SimConnectDataType.FLOAT64);
	}

	/**
	 * Open a request for the aircraft latitude
	 * @param callbackFunction function where the data will be delivered
	 * @throws IOException 
	 */
	public static void requestLatitude(String callbackFunction) throws IOException {
		dataRequest.requestData("Plane Latitude", "radians", callbackFunction, SimConnectDataType.FLOAT64);
	}
	
	/**
	 * Open a request for the aircraft longitude
	 * @param callbackFunction function where the data will be delivered
	 * @throws IOException 
	 */
	public static void requestLongitude(String callbackFunction) throws IOException {
		dataRequest.requestData("Plane Longitude", "radians", callbackFunction, SimConnectDataType.FLOAT64);
	}
	
	/**
	 * Open a request for the aircraft heading
	 * @param callbackFunction function where the data will be delivered
	 * @throws IOException
	 */
	public static void requestHeading(String callbackFunction) throws IOException {
		dataRequest.requestData("Plane Heading Degrees True", "radians", callbackFunction, SimConnectDataType.FLOAT64);
	}
	
	/**
	 * Open a request for the aircraft pitch angle
	 * @param callbackFunction function where the data will be delivered
	 * @throws IOException
	 */
	public static void requestPitch(String callbackFunction) throws IOException {
		dataRequest.requestData("Plane Pitch Degrees", "radians", callbackFunction, SimConnectDataType.FLOAT64);
	}
	
	/**
	 * Open a request for the aircraft bank angle
	 * @param callbackFunction function where the data will be delivered
	 * @throws IOException
	 */
	public static void requestBank(String callbackFunction) throws IOException {
		dataRequest.requestData("Plane Bank Degrees", "radians", callbackFunction, SimConnectDataType.FLOAT64);
	}
	
	/**
	 * Open a request for the surface type the aircraft is flying over
	 * NOTE: The units for this variable is listed as "enum". That means opening a integer request is going to return a bunch of numbers which will indicate different surface types.
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
	 * @param callbackFunction function where the data will be delivered
	 * @throws IOException
	 */
	public static void requestSurfaceType(String callbackFunction) throws IOException {
		dataRequest.requestData("Surface Type", "enum", callbackFunction, SimConnectDataType.INT32);
	}
	
	/**
	 * Open a request for the aircraft airspeed
	 * @param callbackFunction function where the data will be delivered
	 * @throws IOException 
	 */
	public static void requestAirspeed(String callbackFunction) throws IOException {
		dataRequest.requestData("Airspeed True", "knots", callbackFunction, SimConnectDataType.FLOAT64);
	}
	
	/**
	 * Open a request for the aircraft mach airspeed
	 * @param callbackFunction function where the data will be delivered
	 * @throws IOException function where the data will be delivered
	 */
	public static void requestAirspeedMach(String callbackFunction) throws IOException {
		dataRequest.requestData("Airspeed Mach", "mach", callbackFunction, SimConnectDataType.FLOAT64);
	}
	
	/**
	 * Opens a request for the aircraft overspeed warning status
	 * @param callbackFunction function where the data will be delivered
	 * @throws IOException
	 */
	public static void requestOverspeedWarningStatus(String callbackFunction) throws IOException {
		dataRequest.requestData("Overspeed Warning", "bool", callbackFunction, SimConnectDataType.INT32);
	}
}
