package com.IconPippi.simtriggers.scripting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.IconPippi.simtriggers.module.Module;
import com.IconPippi.simtriggers.module.ModuleManager;
import com.IconPippi.simtriggers.triggers.RegisterTrigger;
import com.IconPippi.simtriggers.util.FileUtils;
import com.IconPippi.simtriggers.util.Logger;

/**
 * This class loads all module's scripts
 * @author IconPippi
 *
 */
public class ScriptLoader {
	
	/*
	 * Modules
	 */
	private final ModuleManager mm = new ModuleManager();
	
	/*
	 * Scripting
	 */
	public final static ScriptEngineManager engineManager = new ScriptEngineManager();
	public final static ScriptEngine engine = engineManager.getEngineByName("nashorn");
	
	private String simTriggersDevKit;
	
	public ScriptLoader() {
		try {
			simTriggersDevKit = compileScripts(mm.getSimTriggersDevKit());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads every installed module's scripts
	 */
	public void loadModules() {
		Logger.log("Loading scripts...");
		
		final FileUtils fileUtils = new FileUtils();
		
		for (Module m : mm.getModules()) {
			try {
				for (File f : fileUtils.getFilesInDir(m.getDir(), false)) {
					
					try {
						if (f.getName().toLowerCase().endsWith(".js")
								&& engine.eval(compileScripts(f)) != null) {
							engine.eval(compileScripts(f));
						}
					} catch (Exception e) {
						Logger.error(e.toString());
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	} 
	
	/**
	 * Reloads every installed module's scripts
	 */
	public void reloadModules() {
		Logger.log("Reloading scripts...");
		
		//Unregister all triggers and eventually clear any wrapper
		RegisterTrigger.unregisterAll();
		
		final FileUtils fileUtils = new FileUtils();
		
		for (Module m : mm.getModules()) {
			try {
				for (File f : fileUtils.getFilesInDir(m.getDir(), false)) {
					
					try {
						if (f.getName().toLowerCase().endsWith(".js")
								&& engine.eval(compileScripts(f)) != null) {
							engine.eval(compileScripts(f));
						}
					} catch (Exception e) {
						Logger.error(e.toString());
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Execute a function inside JS code
	 * @param method Function's name
	 * @param args Function's arguments
	 */
	public void invokeFunction(String method, Object... args) {
		final Invocable invoc = (Invocable) engine;
		try {
			invoc.invokeFunction(method, args);
		} catch (NoSuchMethodException | ScriptException e) {
			Logger.error(e.toString());
		}
	}
	
	/*
	 * Utility method to compile scripts from a .js file
	 */
	private String compileScripts(File f) throws IOException {
		if (f.isDirectory()) return null;
	      
	      StringBuilder compiledScript = new StringBuilder();
	     
	      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF8"));
	      String line;
	      while ((line = br.readLine()) != null) {
	          compiledScript.append(line).append("\n");
	      }
	      
	      br.close();
	      
	      return simTriggersDevKit+"\n "+compiledScript.toString();
	}
	
}
