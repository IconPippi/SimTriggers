package com.simtriggers.fsx.scripting

import javax.script.ScriptEngine
import com.simtriggers.fsx.module.Module
import com.simtriggers.fsx.module.ModulesManager
import com.simtriggers.fsx.SimTriggers
import com.simtriggers.fsx.data.request.RequestData
import com.simtriggers.fsx.event.RegisterEvent
import com.simtriggers.fsx.triggers.TriggerRegister
import com.simtriggers.fsx.triggers.TriggersManager
import com.simtriggers.fsx.util.FileUtils
import java.net.URL
import java.net.URLClassLoader
import dev.iconpippi.logger.Logger
import jdk.nashorn.api.scripting.NashornScriptEngine
import java.io.File
import javax.script.ScriptException
import javax.script.Invocable


/**
 * 02/08/2019
 * Handles the loading of all module's scripts
 *
 * @author IconPippi
 */
class ScriptLoader {

    companion object {
        /** Nashorn JavaScript engine */
        private lateinit var engine: NashornScriptEngine
    }

    // Module manager
    private val mm: ModulesManager = ModulesManager()

    /**
     * Load all modules inside modules' folder
     */
    fun load() {
        // Clear triggers
        TriggerRegister.unregisterAll()

        /** @author ChatTriggers development team (https://github.com/ChatTriggers) */
        val jars = mm.getModules().map {
            it.location.listFiles()?.toList() ?: listOf()
        }.flatten().filter {
            it.name.endsWith(".jar")
        }.map {
            it.toURI().toURL()
        }

        engine = instanceScriptEngine(jars)

        val simTriggersDevKit = FileUtils.saveResource(
            "/simTriggersDevKit.js", File("${mm.modulesFolder}/simTriggersDevKit.js")
        )

        val modulesThread = Thread({
            try {
                engine.eval(simTriggersDevKit)
            } catch (e: java.lang.Exception) {
                Logger.error("An unexpected error occurred while loading simTriggersDevKit.js file.")
                e.printStackTrace()
            }

            mm.getModules().forEach {
                try {
                    engine.eval(compileScripts(it))
                } catch(e: Exception) {
                    Logger.error("Error on loading module: ${it.metadata.moduleName}")
                    e.printStackTrace()
                }
            }
        },"SimTriggers/Modules-Thread")
        modulesThread.stop()
        modulesThread.run()
    }

    /**
     * Execute a function inside JS code
     * @param method Function's name
     * @param args Function's arguments
     */
    fun invokeFunction(method: String?, vararg args: Any?) {
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
     * @param m Target module
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