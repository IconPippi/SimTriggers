package com.simtriggers.fsx.module

import java.io.File

/**
 * 01/08/2019
 * This data class represents the metadata.json file
 *
 * @author IconPippi
 */
class Metadata (parent: File, child: String) : File(parent, child) {

    val moduleName: String? = null
    val version: String? = null
    val author: Array<String>? = null
    val description: String? = null

}