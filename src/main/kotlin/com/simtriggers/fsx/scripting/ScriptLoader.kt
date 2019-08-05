package com.simtriggers.fsx.scripting

import javax.script.ScriptEngine
import com.simtriggers.fsx.module.Module
import com.simtriggers.fsx.module.ModulesManager
import com.simtriggers.fsx.SimTriggers
import java.net.URL
import java.net.URLClassLoader
import dev.iconpippi.logger.Logger
import javax.script.ScriptException
import javax.script.Invocable


/**
 * 02/08/2019
 * This class loads and evaluates all modules scripts
 *
 * @author IconPippi
 */
class ScriptLoader {

    companion object {
        /** Nashorn JavaScript engine */
        private lateinit var engine: ScriptEngine
    }

    private val mm: ModulesManager = ModulesManager()

    /**
     * Load all modules inside modules' folder
     */
    fun load() {
        /** @author ChatTriggers development team (https://github.com/ChatTriggers) */
        val jars = mm.getModules().map {
            it.location.listFiles()?.toList() ?: listOf()
        }.flatten().filter {
            it.name.endsWith(".jar")
        }.map {
            it.toURI().toURL()
        }

        engine = instanceScriptEngine(jars)

        /* Doesn't find the resource and throws IllegalArgument
        val simTriggersDevKit = FileUtils.saveResource("/simTriggersDevKit.js", ModulesManager.modulesFolder)

        try {
            engine.eval(simTriggersDevKit)
        } catch (e: java.lang.Exception) {
            Logger.error("An unexpected error occurred while loading simTriggersDevKit.js file.")
            e.printStackTrace()
        }
         */

        for (m: Module in mm.getModules()) {
            try {
                engine.eval(compileScripts(m))
            } catch(e: ScriptException) {
                Logger.error("Error on loading module ${m.metadata.moduleName}")
                e.printStackTrace()
            }
        }
    }

    /**
     * Execute a function inside JS code
     * @param method Function's name
     * @param args Function's arguments
     */
    fun invokeFunction(method: String, vararg args: Any?) {
        val invoc = engine as Invocable

        try {
            invoc.invokeFunction(method, *args)
        } catch (e: NoSuchMethodException) {
            Logger.error(e.toString())
        } catch (e: ScriptException) {
            Logger.error(e.toString())
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
    @Suppress("DEPRECATION")
    private fun instanceScriptEngine(files: List<URL>): jdk.nashorn.api.scripting.NashornScriptEngine {
        val ucl = URLClassLoader(files.toTypedArray(), SimTriggers::class.java.classLoader)

        return jdk.nashorn.api.scripting.NashornScriptEngineFactory().getScriptEngine(ucl) as jdk.nashorn.api.scripting.NashornScriptEngine
    }

}