package com.simtriggers.fsx.module

import java.io.File

/**
 * 01/08/2019
 * metadata.json file
 *
 * @param parent Metadata module's folder
 * @param child metadata.json file
 *
 * @author IconPippi
 */
class Metadata (parent: File, child: String) : File(parent, child) {

    val moduleName: String? = null
    val version: String? = null
    val authors: Array<String>? = null
    val description: String? = null

}