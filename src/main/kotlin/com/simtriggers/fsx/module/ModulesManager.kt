package com.simtriggers.fsx.module

import com.simtriggers.fsx.util.FileUtils
import com.simtriggers.fsx.SimTriggers
import java.io.File

/**
 * 01/08/2019
 * This class manages all modules
 *
 * @author IconPippi
 */
class ModulesManager {

    private val fileUtils: FileUtils = FileUtils()

    /** Modules Foldrr */
    val modulesFolder: File = File("${SimTriggers.simTriggersFolder.absolutePath}/modules")

    /**
     * Create or load modules folder and export simTriggersDevKit.js
     */
    fun initModules() {
        modulesFolder.setWritable(true)
        if (!modulesFolder.exists()) modulesFolder.mkdirs()

        //TODO: Export simTriggersDevKit.js
    }

    /**
     * List all modules
     * @return Modules
     */
    fun getModules(): List<Module> {
        val modules = ArrayList<Module>()

        for (f: File in fileUtils.listFiles(modulesFolder, true)!!) {
            if (f.isDirectory) modules.add(Module(f, Metadata(f, "metadata.json")))
        }

        return modules
    }

}