package com.IconPippi.simtriggers;

import java.io.IOException;

import com.IconPippi.simtriggers.events.EVENT;
import com.IconPippi.simtriggers.events.EventDecoder;
import com.IconPippi.simtriggers.gui.SimTriggersGUI;
import com.IconPippi.simtriggers.module.ModuleManager;
import com.IconPippi.simtriggers.scripting.ScriptLoader;
import com.IconPippi.simtriggers.triggers.TriggerType;
import com.IconPippi.simtriggers.triggers.TriggersManager;
import com.IconPippi.simtriggers.utils.Logger;

import flightsim.simconnect.SimConnect;
import flightsim.simconnect.SimConnectDataType;
import flightsim.simconnect.SimObjectType;
import flightsim.simconnect.TextResult;
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
import flightsim.simconnect.recv.RecvSimObjectDataByType;
import flightsim.simconnect.recv.SimObjectDataTypeHandler;

/**
 * This class handles all the communication with the simulator
 * @author IconPippi
 *
 */
public class ConnectionOpen implements
	OpenHandler,
	EventHandler,
	ExceptionHandler,
	QuitHandler,
	SimObjectDataTypeHandler {
	
	/*
	 * SimConnect
	 */
	private SimConnect sc;
	private final DispatcherTask dt;
	
	/*
	 * Triggers
	 */
	private final TriggersManager triggersManager;
	
	/*
	 * Utils
	 */
	private final Logger logger = new Logger();
	
	/*
	 * Modules
	 */
	private final ModuleManager moduleManager = new ModuleManager();
	private final ScriptLoader scriptLoader = new ScriptLoader();
	
	/**
	 * Open a new connection with any running instance of Flight Simulator X
	 */
	public ConnectionOpen() throws IOException {
		initConnection();
		
		dt = new DispatcherTask(sc);
		dt.addHandlers(this);
		
		triggersManager = new TriggersManager();
	}
	
	/*
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
	
	/**
	 * Run connection thread
	 */
	public void run() {
		dt.createThread().run();
	}
	
	/**
	 * Get the dispatcher
	 * @return dispatcher
	 */
	public DispatcherTask getDispatcher() {
		return dt;
	}
	
	/**
	 * Get the simulator
	 * @return the simulator
	 */
	public SimConnect getSimConnect() {
		return sc;
	}
	
	/*
	 * Triggered on thrown client/connection exception
	 */
	@Override
	public void handleException(SimConnect sc, RecvException exception) {
		logger.error(""+exception.getException().getMessage()+" "+exception.getRawID()+" "+exception.getIndex()+" "+exception.getSendID());
	}
	
	/*
	 * Triggered on received event
	 */
	@Override
	public void handleEvent(SimConnect sc, RecvEvent event) {
		logger.log("Triggered event: "+event.getEventID());
		
		/*
		 * Trigger events 
		 */
		if (String.valueOf(event.getEventID()).startsWith("22")) { //Throttle events
			triggersManager.triggerAll(TriggerType.THROTTLE);
			triggersManager.triggerAllThrottle(new EventDecoder().decode(event.getEventID()));
		} else if (String.valueOf(event.getEventID()).startsWith("33")) { //Mixture events
			triggersManager.triggerAll(TriggerType.MIXTURE);
			triggersManager.triggerAllMixture(new EventDecoder().decode(event.getEventID()));
		} else if (String.valueOf(event.getEventID()).startsWith("44")) { //Key events
			triggersManager.triggerAll(TriggerType.KEYS);
			triggersManager.triggerAllKeys(new EventDecoder().decode(event.getEventID()));
		} else if (String.valueOf(event.getEventID()).startsWith("55")) { //Propeller events
			triggersManager.triggerAll(TriggerType.PROPELLER);
			triggersManager.triggerAllPropeller(new EventDecoder().decode(event.getEventID()));
		}
		
		/*
		 * Menu Events
		 */
		if (String.valueOf(event.getEventID()).startsWith("11")) { //Menu events
			scriptLoader.invokeFunction(new EventDecoder().decode(event.getEventID()), TextResult.type(event).toString());
		}
		
		/*
		 * Client events 
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
		
		//TODO: TEST2
		try {
			sc.requestDataOnSimObjectType(EVENT.REQUEST_PLANE_LATITUTE,
					EVENT.PLANE_LATITUDE, 0, SimObjectType.USER);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//TODO: TEST2
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
			/*
			 * System events
			 */
			sc.subscribeToSystemEvent(EVENT.SIM_START, "SimStart");
		
			
			/*
			 * Menu events
			 */
			sc.menuAddItem("SimTriggers", EVENT.ADDONSMENU_SIMTRIGGERS, 0); //Add SimTriggers tab in addons menu
	        sc.menuAddSubItem(EVENT.ADDONSMENU_SIMTRIGGERS, "Reload Scripts...", EVENT.SIMTRIGGERSTAB_RELOADSCRIPTS, 0); //Add Reload Scripts option under SimTriggers tab
	        sc.menuAddSubItem(EVENT.ADDONSMENU_SIMTRIGGERS, "Open Modules GUI", EVENT.SIMTRIGGERSTAB_OPENGUI, 0); //Add Open Modules GUI option under SimTriggers tab
		
	        //TODO: TEST3
			sc.addToDataDefinition(EVENT.PLANE_LATITUDE, "Plane Latitude",
					"degrees", SimConnectDataType.FLOAT64);
			//TODO: TEST3
		} catch (IOException e) {
			e.printStackTrace();
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
	
	//TODO: TEST1
	@Override
	public void handleSimObjectType(SimConnect sc, RecvSimObjectDataByType data) {
		if (data.getRequestID() == EVENT.REQUEST_PLANE_LATITUTE.ordinal()) { //Here throws java.nio.BufferUnderflowException 
																				 //Fuck it I tried everyhing fucvl off
			//
			// notice that we cannot cast directly a RecvSimObjectDataByType 
			// to a structure. this is forbidden by java language
			//
				
			System.out.println("ObjectID=" + data.getObjectID() + " Title='"
					+ data.getDataString256() + "'");
			System.out.println("Lat=" + data.getDataFloat64() + " Lon="
					+ data.getDataFloat64() + " Alt=" + data.getDataFloat64()
					+ " Kohlsman=" + data.getDataFloat64());
		}
	}
	//TODO: TEST1
}
