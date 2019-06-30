package com.IconPippi.simtriggers.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.IconPippi.simtriggers.utils.FileUtils;
import com.IconPippi.simtriggers.utils.Logger;

/**
 * This class manages all modules
 * @author IconPippi
 *
 */
public class ModuleManager {
	
	/* Utils */
	private final Logger logger = new Logger();
	private final FileUtils fileUtils = new FileUtils();
	
	/* Modules folder */
	private final File modulesDir = new File("modules");
	
	public File getModulesDir() {
		return modulesDir;
	}
	
	/**
	 * Creates or loads the modules folder
	 */
	public void initModules() {
		if (modulesDir.exists()) {
			logger.log("Loaded Modules folder ("+modulesDir.getAbsolutePath()+")");
		} else { 
			modulesDir.mkdir();
			logger.log("Created a new Modules folder ("+modulesDir.getAbsolutePath()+")");
		}
		try {
			fileUtils.exportResourceToModulesFolder("/simtriggersDevKit.js");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets all the modules present in modules directory
	 * @return All modules inside the modules directory
	 */
	public List<Module> getModules() {
		final FileUtils fileUtils = new FileUtils(); //Util
		final List<Module> modules = new ArrayList<>();
		
		//Loop through modules folder files
		for (File f : fileUtils.getFilesInDir(modulesDir, true)) {
			modules.add(new Module(f, new ModuleMetadata(f, "metadata.json")));
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
}
