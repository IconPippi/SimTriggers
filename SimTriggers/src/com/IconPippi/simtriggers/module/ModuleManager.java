package com.IconPippi.simtriggers.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.IconPippi.simtriggers.utils.FileUtils;
import com.IconPippi.simtriggers.utils.Logger;

public class ModuleManager {
	
	private final Logger logger = new Logger();
	
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
	}
	
	/**
	 * Gets all the modules present in modules directory
	 * @return modules
	 */
	public List<Module> getModules() {
		final FileUtils fileUtils = new FileUtils();
		final List<Module> modules = new ArrayList<>();
		
		for (File f : fileUtils.getFilesInDir(modulesDir, true)) {
			modules.add(new Module(f, new ModuleMetadata(f, "metadata.json")));
		}
		
		return modules;
	}
	
	public Module getModuleByID(int ID) {
		Module toReturn = null;
		
		for (Module m : getModules()) {
			final ModuleMetadata meta = m.getMeta();
			if (meta.getID() == ID) {
				toReturn = m;
				break;
			} else {
				continue;
			}
		}
		
		return toReturn;
	}
}
