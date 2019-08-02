package com.simtriggers.fsx.module

import java.io.File

/**
 * 01/08/2019
 * This data class represents the metadata.json file
 *
 * @author IconPippi
 */
class Metadata (parent: File, child: String) : File(parent, child) {

    val metaName: String? = null
    val metaVersion: String? = null
    val metaAuthor: String? = null
    val metaDescription: String? = null

}