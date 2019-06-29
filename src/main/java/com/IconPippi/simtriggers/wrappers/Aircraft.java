package com.IconPippi.simtriggers.wrappers;

import java.io.IOException;

import com.IconPippi.simtriggers.data.request.DataRequest;

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
	 * Initialize a request for the aircraft altitude
	 * @param callbackFunction function where the data will be delivered
	 */
	public static void requestAltitude(String callbackFunction) {
		try {
			dataRequest.requestFloatData("Plane Altitude", "feet", callbackFunction);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize a request for the aircraft airspeed
	 * @param callbackFunction function where the data will be delivered
	 */
	public static void requestAirspeed(String callbackFunction) {
		try {
			dataRequest.requestFloatData("Airspeed True", "knots", callbackFunction);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
