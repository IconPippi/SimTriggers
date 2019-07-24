package com.IconPippi.simtriggers.module;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.IconPippi.simtriggers.util.FileUtils;
import com.IconPippi.simtriggers.util.Logger;

/**
 * This class manages all modules
 * @author IconPippi
 *
 */
public class ModuleManager {
	
	/* Utils */
	private final FileUtils fileUtils = new FileUtils();
	
	/* Modules folder */
	private final File modulesDir = new File("modules");
	
	/* SimTriggers dev kit */
	private final File simTriggersDevKit = new File(fileUtils.exportResourceToModulesFolder("/simtriggersDevKit.js"));
	
	public File getModulesDir() {
		return modulesDir;
	}
	
	/**
	 * Creates or loads the modules folder
	 */
	public void initModules() {
		if (modulesDir.exists()) {
			Logger.log("Loaded Modules folder ("+modulesDir.getAbsolutePath()+")");
		} else { 
			modulesDir.mkdirs();
			Logger.log("Created a new Modules folder ("+modulesDir.getAbsolutePath()+") "+modulesDir.exists());
		}
		simTriggersDevKit.mkdirs();
	}
	
	/**
	 * Gets all the modules present in modules directory
	 * @return All modules inside the modules directory
	 */
	public List<Module> getModules() {
		if (!modulesDir.exists()) return null;
		
		final FileUtils fileUtils = new FileUtils(); //Util
		final List<Module> modules = new ArrayList<>();
		
		//Loop through modules folder files
		try {
			for (File f : fileUtils.getFilesInDir(modulesDir, true)) {
				modules.add(new Module(f, new ModuleMetadata(f, "metadata.json")));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return modules;
	}
	
	/**
	 * Returns the module object for the specified ID
	 * @param ID module ID
	 * @return Module Object
	 */
	public Module getModuleByID(int ID) {
		Module toReturn = null;
		
		for (Module m : getModules()) {
			final ModuleMetadata meta = m.getMeta(); //Init Meta
			if (meta.getID() == ID) {
				m = new Module(m.getDir(), m.getMeta());
				toReturn = m;
				break;
			} else {
				continue;
			}
		}
		
		return toReturn;
	}
	
	/**
	 * Returns the module object for the specified name
	 * @param name module's name
	 * @return Module Object
	 */
	public Module getModuleByName(String name) {
		Module toReturn = null;
		
		for (Module m : getModules()) {
			final ModuleMetadata meta = m.getMeta();
			if (meta != null) {
				if (meta.getName().contains(name)) {
					m = new Module(m.getDir(), m.getMeta());
					toReturn = m;
					break;
				} else {
					continue;
				}
			} else {
				continue;
			}
		}
		
		return toReturn;
	}
	
	public File getSimTriggersDevKit() {
		return simTriggersDevKit;
	}
}
