package com.simtriggers.fsx.module

import com.google.gson.Gson
import java.io.File
import java.io.FileReader

/**
 * 01/08/2019
 * This class represents a Module
 *
 * @author IconPippi
 */
class Module(private val location: File, var metadata: Metadata) {

    init {
        metadata = Gson().fromJson(FileReader(metadata), Metadata::class.java)
    }

}