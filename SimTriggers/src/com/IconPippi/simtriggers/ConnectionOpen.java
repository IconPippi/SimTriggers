package com.IconPippi.simtriggers;

import java.io.IOException;

import com.IconPippi.simtriggers.gui.SimTriggersGUI;
import com.IconPippi.simtriggers.module.Module;
import com.IconPippi.simtriggers.module.ModuleManager;
import com.IconPippi.simtriggers.scripting.ScriptLoader;
import com.IconPippi.simtriggers.triggers.TriggerType;
import com.IconPippi.simtriggers.triggers.TriggersManager;
import com.IconPippi.simtriggers.utils.Logger;

import flightsim.simconnect.NotificationPriority;
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
	
	//Triggers
	private final TriggersManager triggersManager;
	
	//Utils
	private final Logger logger = new Logger();
	
	//Modules
	private final ModuleManager moduleManager = new ModuleManager();
	private final ScriptLoader scriptLoader = new ScriptLoader();
	
	//Modules
	@SuppressWarnings("unused")
	private final ModuleManager mm;
	
	public ConnectionOpen() throws IOException {
		initConnection();
		
		dt = new DispatcherTask(sc);
		dt.addHandlers(this);
		
		mm = new ModuleManager();
		
		triggersManager = new TriggersManager();
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
		logger.error(""+exception.getException().getMessage());
	}
	
	/*
	 * Triggered on received event
	 */
	@Override
	public void handleEvent(SimConnect sc, RecvEvent event) {
		//logger.log("Triggered event: "+event.getEventID()); TODO: Disabled because it spams some random event that I couldn't figure out which one it is
		/*
		 * Menu Events
		 */
		if (EVENT.SIMTRIGGERSTAB_RELOADSCRIPTS.isEvent(event)) {
			try {
				sc.text(TextType.PRINT_RED, 5, EVENT.RELOADSCRIPTS_TEXT, "Reloading Scripts...");
				scriptLoader.reloadModules();
			} catch (UnsupportedOperationException | IOException e) {
				logger.error(e.toString());
			}
		} else if (EVENT.SIMTRIGGERSTAB_OPENGUI.isEvent(event)) {
			final SimTriggersGUI gui = new SimTriggersGUI(this);
			gui.show();
		} 
			
		/*
		 * Throttle events
		 */
		if (EVENT.THROTTLE_FULL.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); //Trigger with no throttle action specification
			triggersManager.triggerAllThrottle("THROTTLE_FULL"); //Trigger with throttle action specification
		} else if (EVENT.THROTTLE_CUT.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE_CUT");
		} else if (EVENT.THROTTLE_SET.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE_SET");
		} else if (EVENT.THROTTLE_INCR.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE_INCR");
		} else if (EVENT.THROTTLE_INCR_SMALL.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE_INCR_SMALL");
		} else if (EVENT.THROTTLE_DECR.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE_DECR");
		} else if (EVENT.THROTTLE_DECR_SMALL.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE_DECR_SMALL");
		} else if (EVENT.THROTTLE1_SET.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE1_SET");
		} else if (EVENT.THROTTLE1_FULL.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE1_FULL");
		} else if (EVENT.THROTTLE1_CUT.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE1_CUT");
		} else if (EVENT.THROTTLE1_INCR.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE1_INCR");
		} else if (EVENT.THROTTLE1_INCR_SMALL.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE1_INCR_SMALL");
		} else if (EVENT.THROTTLE1_DECR.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE1_DECR");
		} else if (EVENT.THROTTLE1_DECR_SMALL.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE1_DECR_SMALL");
		} else if (EVENT.THROTTLE2_SET.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE2_SET");
		} else if (EVENT.THROTTLE2_FULL.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE2_FULL");
		} else if (EVENT.THROTTLE2_CUT.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE2_CUT");
		} else if (EVENT.THROTTLE2_INCR.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE2_INCR");
		} else if (EVENT.THROTTLE2_INCR_SMALL.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE2_INCR_SMALL");
		} else if (EVENT.THROTTLE2_DECR.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE2_DECR");
		} else if (EVENT.THROTTLE2_DECR_SMALL.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE2_DECR_SMALL");
		} else if (EVENT.THROTTLE3_SET.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE3_SET");
		} else if (EVENT.THROTTLE3_FULL.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE3_FULL");
		} else if (EVENT.THROTTLE3_CUT.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE3_CUT");
		} else if (EVENT.THROTTLE3_INCR.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE3_INCR");
		} else if (EVENT.THROTTLE3_INCR_SMALL.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE3_INCR_SMALL");
		} else if (EVENT.THROTTLE3_DECR.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE3_DECR");
		} else if (EVENT.THROTTLE3_DECR_SMALL.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE3_DECR_SMALL");
		} else if (EVENT.THROTTLE4_SET.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE4_SET");
		} else if (EVENT.THROTTLE4_FULL.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE4_FULL");
		} else if (EVENT.THROTTLE4_CUT.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE4_CUT");
		} else if (EVENT.THROTTLE4_INCR.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE4_INCR");
		} else if (EVENT.THROTTLE4_INCR_SMALL.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE4_INCR_SMALL");
		} else if (EVENT.THROTTLE4_DECR.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE4_DECR");
		} else if (EVENT.THROTTLE4_DECR_SMALL.isEvent(event)) {
			triggersManager.triggerAll(TriggerType.THROTTLE); 
			triggersManager.triggerAllThrottle("THROTTLE4_DECR_SMALL");
		}
	}   
	
	
	/*
	 * Triggered on client connection opening
	 */
	@Override
	public void handleOpen(SimConnect sc, RecvOpen open) {
		//Initialize modules
		moduleManager.initModules();
		
		//Load module's scripts
		scriptLoader.loadModules();
		
		triggersManager.triggerAll(TriggerType.CONNECTION_OPEN); //Trigger connection open
		
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
	        sc.menuAddSubItem(EVENT.ADDONSMENU_SIMTRIGGERS, "Reload Scripts...", EVENT.SIMTRIGGERSTAB_RELOADSCRIPTS, 0); //Add Reload Scripts option under SimTriggers tab
	        sc.menuAddSubItem(EVENT.ADDONSMENU_SIMTRIGGERS, "Open Modules GUI", EVENT.SIMTRIGGERSTAB_OPENGUI, 0); //Add Open Modules GUI option under SimTriggers tab
		} catch (IOException e) {
			logger.error("Exception on adding item to addons menu: \n"+e.toString());
		}
		
		try {
			/*
			 * Get a Sim event and map it to a EVENT enum
			 */
			
			//Throttle Group
			sc.mapClientEventToSimEvent(EVENT.THROTTLE_FULL, "THROTTLE_FULL");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE_CUT, "THROTTLE_CUT");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE_SET, "THROTTLE_SET");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE_INCR, "THROTTLE_INCR");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE_INCR_SMALL, "THROTTLE_INCR_SMALL");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE_DECR, "THROTTLE_DECR");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE_DECR_SMALL, "THROTTLE_DECR_SMALL");
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE_FULL);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE_CUT);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE_SET);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE_INCR);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE_INCR_SMALL);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE_DECR);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE_DECR_SMALL);
			
			sc.mapClientEventToSimEvent(EVENT.THROTTLE1_FULL, "THROTTLE1_FULL");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE1_CUT, "THROTTLE1_CUT");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE1_SET, "THROTTLE1_SET");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE1_INCR, "THROTTLE1_INCR");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE1_INCR_SMALL, "THROTTLE1_INCR_SMALL");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE1_DECR, "THROTTLE1_DECR");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE1_DECR_SMALL, "THROTTLE1_DECR_SMALL");
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE1_FULL);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE1_CUT);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE1_SET);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE1_INCR);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE1_INCR_SMALL);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE1_DECR);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE1_DECR_SMALL);
			
			sc.mapClientEventToSimEvent(EVENT.THROTTLE2_FULL, "THROTTLE2_FULL");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE2_CUT, "THROTTLE2_CUT");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE2_SET, "THROTTLE2_SET");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE2_INCR, "THROTTLE2_INCR");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE2_INCR_SMALL, "THROTTLE2_INCR_SMALL");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE2_DECR, "THROTTLE2_DECR");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE2_DECR_SMALL, "THROTTLE2_DECR_SMALL");
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE2_FULL);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE2_CUT);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE2_SET);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE2_INCR);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE2_INCR_SMALL);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE2_DECR);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE2_DECR_SMALL);
			
			sc.mapClientEventToSimEvent(EVENT.THROTTLE3_FULL, "THROTTLE3_FULL");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE3_CUT, "THROTTLE3_CUT");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE3_SET, "THROTTLE3_SET");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE3_INCR, "THROTTLE3_INCR");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE3_INCR_SMALL, "THROTTLE3_INCR_SMALL");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE3_DECR, "THROTTLE3_DECR");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE3_DECR_SMALL, "THROTTLE3_DECR_SMALL");
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE3_FULL);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE3_CUT);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE3_SET);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE3_INCR);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE3_INCR_SMALL);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE3_DECR);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE3_DECR_SMALL);
			
			sc.mapClientEventToSimEvent(EVENT.THROTTLE4_FULL, "THROTTLE4_FULL");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE4_CUT, "THROTTLE4_CUT");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE4_SET, "THROTTLE4_SET");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE4_INCR, "THROTTLE4_INCR");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE4_INCR_SMALL, "THROTTLE4_INCR_SMALL");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE4_DECR, "THROTTLE4_DECR");
			sc.mapClientEventToSimEvent(EVENT.THROTTLE4_DECR_SMALL, "THROTTLE4_DECR_SMALL");
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE4_FULL);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE4_CUT);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE4_SET);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE4_INCR);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE4_INCR_SMALL);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE4_DECR);
			sc.addClientEventToNotificationGroup(EVENT.GROUP_THROTTLE, EVENT.THROTTLE4_DECR_SMALL);
			
			sc.setNotificationGroupPriority(EVENT.GROUP_THROTTLE, NotificationPriority.HIGHEST);
			//Throttle Group
		} catch (IOException e) {
			logger.error(e.toString());
		}
		//All Flight Sim Events: https://docs.microsoft.com/en-us/previous-versions/microsoft-esp/cc526980(v=msdn.10)
	}
	
	/*
	 * Triggered on client connection termination
	 */
	@Override
	public void handleQuit(SimConnect sc, RecvQuit quit) {
		triggersManager.triggerAll(TriggerType.CONNECTION_CLOSE);
		logger.log("Connection between client terminated: "+quit.getRawID());
	}
	
	
}
