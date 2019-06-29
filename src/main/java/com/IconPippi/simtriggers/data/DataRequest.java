package com.IconPippi.simtriggers.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.IconPippi.simtriggers.wrappers.SimTriggers;

import flightsim.simconnect.SimConnect;
import flightsim.simconnect.SimConnectDataType;
import flightsim.simconnect.SimObjectType;

/**
 * This class provides methods for data request purposes
 * @author IconPippi
 * 
 */
public class DataRequest {
	
	/** SimConnect constant */
	private final SimConnect sc = SimTriggers.getSimulator();
	
	/*
	 * Data definitions storage
	 */
	protected static List<Integer> dataDefinitionIDs = new ArrayList<>();
	protected static int dataDefinitionsCount = 0;
	protected static List<String> dataDefinitions = new ArrayList<>();
	
	/*
	 * Data requests storage
	 */
	protected static List<Integer> requestIDs = new ArrayList<>();
	protected static int requestCount = 0;
	protected static List<String> requests = new ArrayList<>();
	
	/**
	 * Create a new float data request
	 * @param variable variable you want to retrieve data from
	 * @param units units you want the data to be in (e.g. feet, knots)
	 * @param callbackFunction the request callback function (where the data will be delivered)
	 * @throws IOException
	 */
	public void requestFloatData(String variable, String units, String callbackFunction) throws IOException {
		int encodedData = encodeDataDefinition(variable, SimConnectDataType.FLOAT64);
		int encodedRequestID = encodeDataRequestID(callbackFunction);
		
		sc.addToDataDefinition(
				encodedData, variable, units, SimConnectDataType.FLOAT64);
		
		sc.requestDataOnSimObjectType(
				encodedRequestID,
				encodedData,
				0, SimObjectType.USER);
	}
	
	/**
	 * Create a new integer data request
	 * @param variable variable you want to retrieve data from
	 * @param units units you want the data to be in (e.g. radians)
	 * @param callbackFunction the request callback function (where the data will be delivered)
	 * @throws IOException
	 */
	public void requestIntData(String variable, String units, String callbackFunction) throws IOException {
		int encodedData = encodeDataDefinition(variable, SimConnectDataType.INT64);
		int encodedRequestID = encodeDataRequestID(callbackFunction);
		
		sc.addToDataDefinition(
				encodedData, variable, units, SimConnectDataType.FLOAT64);
		
		sc.requestDataOnSimObjectType(
				encodedData,
				encodedRequestID,
				0, SimObjectType.USER);
	}
	
	/**
	 * Create a new string data request
	 * @param variable variable you want to retrieve data from
	 * @param units units you want the data to be in (boolean)
	 * @param callbackFunction the request callback function (where the data will be delivered)
	 * @throws IOException
	 */
	public void requestStringData(String variable, String units, String callbackFunction) throws IOException {
		int encodedData = encodeDataDefinition(variable, SimConnectDataType.STRING260);
		int encodedRequestID = encodeDataRequestID(callbackFunction);
		
		sc.addToDataDefinition(
				encodedData, variable, units, SimConnectDataType.STRING260);
		
		sc.requestDataOnSimObjectType(
				encodedData,
				encodedRequestID,
				0, SimObjectType.USER);
	}

	private int encodeDataDefinition(String dataDefinition, SimConnectDataType dataType) {
		dataDefinitionsCount++;
		int count = dataDefinitionsCount;
		dataDefinitions.add(dataDefinition);
		
		int end = Integer.valueOf(getDataTypeIDByType(dataType)+""+count);
		dataDefinitionIDs.add(end);
		
		return end;
	}
	
	private int encodeDataRequestID(String request) {
		requestCount++;
		int count = requestCount;
		requests.add(request);
		
		requestIDs.add(count);
		
		return count;
	}
	
	private int getDataTypeIDByType(SimConnectDataType type) {
		int id = -1;
		
		switch (type) {
		case FLOAT32:
			id = 11;
			break;
		case FLOAT64:
			id = 12;
			break;
		case INITPOSITION:
			id = 13;
			break;
		case INT32:
			id = 14;
			break;
		case INT64:
			id = 15;
			break;
		case INVALID:
			id = 16;
			break;
		case LATLONALT:
			id = 17;
			break;
		case MARKERSTATE:
			id = 18;
			break;
		case MAX:
			id = 19;
			break;
		case STRING128:
			id = 21;
			break;
		case STRING256:
			id = 22;
			break;
		case STRING260:
			id = 23;
			break;
		case STRING32:
			id = 24;
			break;
		case STRING64:
			id = 25;
			break;
		case STRING8:
			id = 26;
			break;
		case STRINGV:
			id = 27;
			break;
		case WAYPOINT:
			id = 28;
			break;
		case XYZ:
			id = 29;
			break;
		default:
			break;
		
		}
		
		return id;
	}
	
}
