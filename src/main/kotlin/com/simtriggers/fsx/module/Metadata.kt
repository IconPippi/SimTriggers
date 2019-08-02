package com.simtriggers.fsx.module

import java.io.File

/**
 * 01/08/2019
 * This data class represents the metadata.json file
 *
 * @author IconPippi
 */
data class Metadata (
    var file: File? = null,
    val metaName: String? = null,
    val metaVersion: String? = null,
    val metaAuthor: String? = null,
    val metaDescription: String? = null
) : File(file!!.absolutePath)