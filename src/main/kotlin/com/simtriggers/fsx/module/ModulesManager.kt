package com.simtriggers.fsx.module

import main.kotlin.com.simtriggers.fsx.SimTriggers
import java.io.File

/**
 * 01/08/2019
 * This class manages all modules
 *
 * @author IconPippi
 */
class ModulesManager {

    /** Modules Foldrr */
    private val modulesFolder: File = File("${SimTriggers.simTriggersFolder.absolutePath}/modules")

    /**
     * Create or load modules folder and export simTriggersDevKit.js
     */
    fun initModules() {
        if (!modulesFolder.exists()) modulesFolder.mkdirs()

        //TODO: Export simTriggersDevKit.js
    }


}