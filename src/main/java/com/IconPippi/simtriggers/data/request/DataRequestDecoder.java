package com.IconPippi.simtriggers.data.request;

/**
 * This class (similar to EventDecoder) decodes data definitions IDs and data request IDs into strings
 * @author IconPippi
 * 
 */
public class DataRequestDecoder {
	
	/**
	 * Decode a data definition's ID
	 * @param dataDefinitionID input ID
	 * @return decoded string
	 */
	public String decodeDataDefinitionID(int dataDefinitionID) {
		String toReturn = null;
		int count = 0;
		
		for (int i : DataRequest.dataDefinitionIDs) {
			count++;
			if (i == dataDefinitionID) {
				toReturn = DataRequest.dataDefinitions.get(count-1);
				break;
			} else {
				continue;
			}
		}
		return toReturn;
	}
	
	/**
	 * Decode a data request's ID
	 * @param requestID input requestID
	 * @return decoded string
	 */
	public String decodeRequestID(int requestID) {
		String toReturn = null;
		int count = 0;
		
		for (int i : DataRequest.requestIDs) {
			count++;
			if (i == requestID) {
				toReturn = DataRequest.requests.get(count-1);
				break;
			} else {
				continue;
			}
		}
		return toReturn;
	}
}
