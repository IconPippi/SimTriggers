package com.IconPippi.simtriggers;

import java.io.IOException;

import com.IconPippi.simtriggers.gui.SimTriggersGUI;
import com.IconPippi.simtriggers.module.Module;
import com.IconPippi.simtriggers.module.ModuleManager;
import com.IconPippi.simtriggers.utils.Logger;

import flightsim.simconnect.SimConnect;
import flightsim.simconnect.TextType;
import flightsim.simconnect.config.Configuration;
import flightsim.simconnect.config.ConfigurationManager;
import flightsim.simconnect.recv.DispatcherTask;
import flightsim.simconnect.recv.EventHandler;
import flightsim.simconnect.recv.ExceptionHandler;
import flightsim.simconnect.recv.OpenHandler;
import flightsim.simconnect.recv.QuitHandler;
import flightsim.simconnect.recv.RecvEvent;
import flightsim.simconnect.recv.RecvException;
import flightsim.simconnect.recv.RecvOpen;
import flightsim.simconnect.recv.RecvQuit;

public class ConnectionOpen implements
	OpenHandler,
	EventHandler,
	ExceptionHandler,
	QuitHandler {
	
	//SimConnect connect
	private SimConnect sc;
	private final DispatcherTask dt;
	
	//Utils
	private final Logger logger = new Logger();
	
	//Modules
	@SuppressWarnings("unused")
	private final ModuleManager mm;
	
	public ConnectionOpen() throws IOException {
		initConnection();
		
		dt = new DispatcherTask(sc);
		dt.addHandlers(this);
		
		mm = new ModuleManager();
	}
	
	/**
	 * @author bily (https://github.com/bily)
	 */
	private void initConnection() throws IOException {
		logger.log("Connection initialized");
		//
		// get a configuration block if user provided a simconnect.cfg
		//
		Configuration cfg = null;
		try {
			cfg = ConfigurationManager.getConfiguration(0);
		} catch (Exception cfgEx) {
			cfg = new Configuration();
		}
		
		// fix port number (with automatic settings)
		int port = cfg.getInt(Configuration.PORT, -1);
		if (port == -1) {
			port = Configuration.findSimConnectPortIPv4();
			if (port <= 0) {
				port = Configuration.findSimConnectPortIPv6();
				cfg.setProtocol(6);
			} else {
				cfg.setProtocol(4);
			}
			cfg.setPort(port);
		}
		
		// fix host
		String host = cfg.get(Configuration.ADDRESS, null);
		if (host == null) {
			if (cfg.getInt(Configuration.PROTOCOL, 4) == 6)
				cfg.setAddress("::1");
			else
				cfg.setAddress("localhost");
		}
		
		// force simconnect version 0x3
		sc = new SimConnect("SimTriggers", cfg, 0x3);
		logger.log("Connection established");
	}
	
	public void run() {
		dt.createThread().run();
	}
	
	public DispatcherTask getDispatcher() {
		return dt;
	}
	
	public SimConnect getSimConnect() {
		return sc;
	}
	
	/*
	 * Utils method
	 */
	public String getAuthorsList(Module m) {
		final StringBuilder sb = new StringBuilder();
		
		for (String author : m.getMeta().getAuthors()) {
			if (sb.length() == 0) {
				sb.append(author);
			} else {
				sb.append(", "+author);
			}
		}
		
		return sb.toString();
	}
	
	/*
	 * Triggered on thrown client/connection exception
	 */
	@Override
	public void handleException(SimConnect sc, RecvException exception) {
		logger.error(exception.toString());
	}
	
	/*
	 * Triggered on received event
	 */
	@Override
	public void handleEvent(SimConnect sc, RecvEvent event) {
		logger.log("Triggered event: "+event.getEventID());
		if (EVENT.SIMTRIGGERSTAB_RELOADSCRIPTS.isEvent(event)) {
			try {
				sc.text(TextType.PRINT_RED, 5, EVENT.RELOADSCRIPTS_TEXT, "Reloading Scripts...");
			} catch (UnsupportedOperationException | IOException e) {
				logger.error(e.toString());
			}
			//final ScriptLoader sl = new ScriptLoader();
			//sl.loadModules();
		} else if (EVENT.SIMTRIGGERSTAB_OPENGUI.isEvent(event)) {
			final SimTriggersGUI gui = new SimTriggersGUI(this);
			gui.show();
		}
	}
	
	/*
	 * Triggered on client connection opening
	 */
	@Override
	public void handleOpen(SimConnect sc, RecvOpen open) {
		logger.log("Connected to " + 
				open.getApplicationName() + 
				" version " +
				open.getApplicationVersionMajor() + "." +
				open.getApplicationVersionMinor() + "." +
				open.getApplicationBuildMajor() + "." +
				open.getApplicationBuildMinor() + 
				" simconnect " +
				open.getSimConnectVersionMajor() + "." +
				open.getSimConnectVersionMinor() + "." +
				open.getSimConnectBuildMajor() + "." +
				open.getSimConnectBuildMinor()
		);
		logger.log("Protocol version: " + open.getVersion());
		
		try {
			sc.menuAddItem("SimTriggers", EVENT.ADDONSMENU_SIMTRIGGERS, 0); //Add SimTriggers tab in addons menu
			/*
	        for (Module m : mm.getModules()) { //Add a sub tab for each module
	        	final ModuleMetadata meta = m.getMeta();
	        	sc.menuAddSubItem(1234, meta.getName(), meta.getID(), 0);
	        }
	        */
	        sc.menuAddSubItem(EVENT.ADDONSMENU_SIMTRIGGERS, "Reload Scripts...", EVENT.SIMTRIGGERSTAB_RELOADSCRIPTS, 0);
	        sc.menuAddSubItem(EVENT.ADDONSMENU_SIMTRIGGERS, "Open Modules GUI", EVENT.SIMTRIGGERSTAB_OPENGUI, 0);
		} catch (IOException e) {
			logger.error("Exception on adding item to addons menu: \n"+e.toString());
		}
	}
	
	/*
	 * Triggered on client connection termination
	 */
	@Override
	public void handleQuit(SimConnect sc, RecvQuit quit) {
		logger.log("Connection between client terminated: "+quit.getRawID());
	}
	
	
}
