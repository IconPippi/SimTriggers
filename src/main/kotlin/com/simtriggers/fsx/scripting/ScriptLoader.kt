package com.simtriggers.fsx.scripting

import javax.script.ScriptEngine
import com.simtriggers.fsx.module.Module
import com.simtriggers.fsx.module.ModulesManager
import com.simtriggers.fsx.util.FileUtils
import jdk.nashorn.api.scripting.NashornScriptEngine
import jdk.nashorn.api.scripting.NashornScriptEngineFactory
import com.simtriggers.fsx.SimTriggers
import java.net.URL
import java.net.URLClassLoader
import dev.iconpippi.logger.Logger
import javax.script.ScriptException


/**
 * 02/08/2019
 * This class loads and evaluates all modules scripts
 *
 * @author IconPippi
 */

class ScriptLoader {

    /** Nashorn JavaScript engine */
    private lateinit var engine: ScriptEngine

    /**
     * Load all modules inside modules' folder
     */
    fun load() {
        /** @author ChatTriggers development team (https://github.com/ChatTriggers) */
        val jars = ModulesManager.getModules().map {
            it.location.listFiles()?.toList() ?: listOf()
        }.flatten().filter {
            it.name.endsWith(".jar")
        }.map {
            it.toURI().toURL()
        }

        engine = instanceScriptEngine(jars)

        val simTriggersDevKit = FileUtils.saveResource("/simTriggersDevKit.js", ModulesManager.modulesFolder)
        try {
            engine.eval(simTriggersDevKit)
        } catch (e: java.lang.Exception) {
            Logger.error("An unexpected error occurred while loading simTriggersDevKit.js file.")
            e.printStackTrace()
        }

        for (m: Module in ModulesManager.getModules()) {
            try {
                engine.eval(compileScripts(m))
            } catch(e: ScriptException) {
                Logger.error("Error on loading module ${m.metadata.metaName}")
                e.printStackTrace()
            }
        }
    }

    /**
     * Compile all script files for a module into one single string for evaluation
     */
    @Throws(Exception::class)
    private fun compileScripts(m: Module): String {
        return m.getScriptFiles().joinToString("\n") {
            it.readText()
        }
    }

    /**
     * @author ChatTriggers development team (https://github.com/ChatTriggers)
     */
    private fun instanceScriptEngine(files: List<URL>): NashornScriptEngine {
        val ucl = URLClassLoader(files.toTypedArray(), SimTriggers::class.java.classLoader)

        return NashornScriptEngineFactory().getScriptEngine(ucl) as NashornScriptEngine
    }

}