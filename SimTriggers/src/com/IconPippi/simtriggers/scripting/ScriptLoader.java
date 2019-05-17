package com.IconPippi.simtriggers.scripting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.IconPippi.simtriggers.module.Module;
import com.IconPippi.simtriggers.module.ModuleManager;
import com.IconPippi.simtriggers.utils.FileUtils;
import com.IconPippi.simtriggers.utils.Logger;

public class ScriptLoader {
	
	private ModuleManager mm;
	private final Logger logger = new Logger();
	
	public final static ScriptEngineManager engineManager = new ScriptEngineManager();
	public final static ScriptEngine engine = engineManager.getEngineByName("nashorn");
	
	/**
	 * Loads every installed module's scripts
	 */
	public void loadModules() {
		logger.log("Loading scripts...");
		
		mm = new ModuleManager();
		
		final FileUtils fileUtils = new FileUtils();
		
		for (Module m : mm.getModules()) {
			for (File f : fileUtils.getFilesInDir(m.getDir(), false)) {
				
				try {
					if (f.getName().toLowerCase().endsWith(".js") && engine.eval(compileScripts(f)) != null) {
						engine.eval(compileScripts(f));
					}
				} catch (Exception e) {
					logger.log(e.toString());
				}
			}
		}
	}
	
	/**
	 * Execute a function inside JS code
	 * @param Function's name
	 * @param Function's arguments
	 */
	public void invokeFunction(String method, Object... args) {
		final Invocable invoc = (Invocable) engine;
		try {
			invoc.invokeFunction(method, args);
		} catch (NoSuchMethodException | ScriptException e) {
			logger.error(e.toString());
		}
	}
	
	/*
	 * Util method to compile scripts from a .js file
	 */
	private String compileScripts(File f) throws IOException {
		if (f.isDirectory()) return null;
	      
	      StringBuilder compiledScript = new StringBuilder();
	      

	      @SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF8"));
	      String line;
	      while ((line = br.readLine()) != null) {
	          compiledScript.append(line).append("\n");
	      }
	      
	      return compiledScript.toString();
	}
	
}
