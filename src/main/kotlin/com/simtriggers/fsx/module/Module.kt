package com.simtriggers.fsx.module

import com.google.gson.Gson
import com.simtriggers.fsx.util.FileUtils
import java.io.File
import java.io.FileReader

/**
 * 01/08/2019
 * This class represents a Module
 *
 * @author IconPippi
 */
class Module(val location: File, var metadata: Metadata) {

    init {
        metadata = Gson().fromJson(FileReader(metadata), Metadata::class.java)
    }

    /**
     * Get all script files
     * @return Script files
     */
    fun getScriptFiles(): List<File> {
        return this.location.walkTopDown().filter {
            it.name.endsWith(".js")
        }.filter {
            it.isFile
        }.toList()
    }

}