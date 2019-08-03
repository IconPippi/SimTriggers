package com.simtriggers.fsx.util

import java.io.File
import java.lang.NullPointerException
import java.util.ArrayList

/**
 * 02/08/2019
 * This class contains series of helper methods for file-related actions
 *
 * @author IconPippi
 */
object FileUtils {

    /**
     * List all the files inside a directory
     * @param target Target directory
     * @param folders Set it to true if you want sub-directories to be listed aswell
     * @return Listed files
     */
    @Throws(Exception::class)
    @JvmStatic fun listFiles(target: File, folders: Boolean): List<File>? {
        //If the target is not a folder, throw an exception
        if (!target.isDirectory) throw Exception()

        val files = ArrayList<File>()

        try {
            for (f: File in target.listFiles()!!) {
                if (!f.isDirectory) files.add(f) //If the file isn't a directory, add it to the list
                else if (folders) files.add(f) //Else if the file is a directory and sub-folders are requested to be listed aswell, add it to the list
            }
        } catch (e: NullPointerException) {
            //Leave the List empty
        }

        return files
    }

    fun saveResource(resourceName: String?, outputFile: File): String {
        if (resourceName == null || resourceName == "") {
            throw IllegalArgumentException("ResourcePath cannot be null or empty")
        }

        val parsedResourceName = resourceName.replace('\\', '/')
        val resource = this.javaClass.getResourceAsStream(parsedResourceName)
            ?: throw IllegalArgumentException("The embedded resource '$parsedResourceName' cannot be found.")

        val res = resource.bufferedReader().readText()
        outputFile.writeText(res)
        return res
    }

}