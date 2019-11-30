package com.simtriggers.fsx.module

import com.simtriggers.fsx.util.FileUtils
import com.simtriggers.fsx.SimTriggers
import java.io.File

/**
 * 01/08/2019
 * Module manager
 *
 * @author IconPippi
 */
class ModulesManager {

    /** Modules Folder */
    val modulesFolder: File = File("${SimTriggers.simTriggersFolder.absolutePath}/modules")

    /**
     * Create or load modules folder and export simTriggersDevKit.js
     */
    fun initModules() {
        modulesFolder.setWritable(true)
        if (!modulesFolder.exists()) modulesFolder.mkdirs()

            FileUtils.saveResource("/simTriggersDevKit.js", modulesFolder)
    }

    /**
     * List all modules
     * @return Modules
     */
    fun getModules(): List<Module> {
        val modules = ArrayList<Module>()

        FileUtils.listFiles(modulesFolder, true)?.forEach {
            if (it.isDirectory) modules.add(Module(it, Metadata(it, "metadata.json")))
        }

        return modules
    }

}