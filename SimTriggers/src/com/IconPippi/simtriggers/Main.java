package com.IconPippi.simtriggers;

import com.IconPippi.simtriggers.module.ModuleManager;
import com.IconPippi.simtriggers.scripting.ScriptLoader;
import com.IconPippi.simtriggers.utils.Logger;

public class Main {
	
	public static void main(String[] args) {
		final Logger logger = new Logger();
		logger.log("Initializing SimTriggers");
		
		final ConnectionOpen co; //Connection class
		final ModuleManager mm; //Modules manager
		final ScriptLoader sl; //Module script loader
		
		//Initialize modules
		mm = new ModuleManager();
		mm.initModules();
		
		//Load module's scripts
		sl = new ScriptLoader();
		sl.loadModules();
		
		//Establish connection
		try {
			co = new ConnectionOpen();
		} catch (Exception e) {
			logger.error("Connection could not be initalized: \n"+e.toString());
			return;
		}
		
		co.run(); //Run connection thread
	} 
}
