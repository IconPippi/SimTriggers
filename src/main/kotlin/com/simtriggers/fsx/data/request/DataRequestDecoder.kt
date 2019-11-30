package com.simtriggers.fsx.data.request

/**
 * 06/08/2019
 * Decode the received data
 *
 * @author IconPippi
 */
class DataRequestDecoder {

    /**
     * Decode a data definition's ID
     * @param dataDefinitionID Input ID
     * @return Decoded string
     */
    fun decodeDataDefinitionID(dataDefinitionID: Int): String? {
        var toReturn: String? = null
        var count = 0

        for (i in RequestData.dataDefinitionIDs) {
            count++
            if (i == dataDefinitionID) {
                toReturn = RequestData.dataDefinitions[count - 1]
                break
            } else {
                continue
            }
        }
        return toReturn
    }

    /**
     * Decode a data request's ID
     * @param requestID Input requestID
     * @return Decoded string
     */
    fun decodeRequestID(requestID: Int): String? {
        var toReturn: String? = null
        var count = 0

        for (i in RequestData.requestIDs) {
            count++
            if (i == requestID) {
                toReturn = RequestData.requests[count - 1]
                break
            } else {
                continue
            }
        }
        return toReturn
    }

}