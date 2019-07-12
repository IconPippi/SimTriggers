package com.IconPippi.simtriggers;

import java.io.IOException;

import com.IconPippi.simtriggers.data.request.DataRequestDecoder;
import com.IconPippi.simtriggers.events.EVENT;
import com.IconPippi.simtriggers.events.EventDecoder;
import com.IconPippi.simtriggers.module.ModuleManager;
import com.IconPippi.simtriggers.scripting.ScriptLoader;
import com.IconPippi.simtriggers.triggers.TriggerType;
import com.IconPippi.simtriggers.triggers.TriggersManager;
import com.IconPippi.simtriggers.utils.Logger;

import flightsim.simconnect.SimConnect;
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
	
	/* Triggers */
	private final TriggersManager triggersManager;
	
	/* Utils */
	private final Logger logger = new Logger();
	
	/*
	 * Modules
	 */
	private final ScriptLoader scriptLoader = new ScriptLoader();
	
	/**
	 * Open a new connection with any running instance of Flight Simulator X
	 */
	public ConnectionOpen() throws IOException {
		initConnection();
		
		/* Sub to sys event */
		sc.subscribeToSystemEvent(EVENT.SIM_START, "SimStart");
		
		dt = new DispatcherTask(sc);
		dt.addOpenHandler(this);
		dt.addQuitHandler(this);
		dt.addExceptionHandler(this);
		dt.addEventHandler(this);
		dt.addSimObjectDataTypeHandler(this);
		
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
		logger.debug("Triggered event: "+event.getEventID());
		
		/*
		 * Trigger events 
		 */
		if (String.valueOf(
				event.getEventID()).startsWith("22")) { //Throttle events
			triggersManager.triggerAll(TriggerType.THROTTLE);
			triggersManager.triggerAllThrottle(new EventDecoder()
					.decode(event.getEventID()));
			
		} else if (String.valueOf(
				event.getEventID()).startsWith("33")) { //Mixture events
			triggersManager.triggerAll(TriggerType.MIXTURE);
			triggersManager.triggerAllMixture(new EventDecoder()
					.decode(event.getEventID()));
			
		} else if (String.valueOf(
				event.getEventID()).startsWith("44")) { //Key events
			triggersManager.triggerAll(TriggerType.KEYS);
			triggersManager.triggerAllKeys(new EventDecoder()
					.decode(event.getEventID()));
			
		} else if (String.valueOf(
				event.getEventID()).startsWith("55")) { //Propeller events
			triggersManager.triggerAll(TriggerType.PROPELLER);
			triggersManager.triggerAllPropeller(new EventDecoder()
					.decode(event.getEventID()));
			
		} else if (String.valueOf(
				event.getEventID()).startsWith("66")) { //Magneto events
			triggersManager.triggerAll(TriggerType.MAGNETO);
			triggersManager.triggerAllMagneto(new EventDecoder()
					.decode(event.getEventID()));
		} else if (String.valueOf(
				event.getEventID()).startsWith("77")) { //Anti Ice events
			triggersManager.triggerAll(TriggerType.ANTI_ICE);
			triggersManager.triggerAllAntiIce(new EventDecoder()
					.decode(event.getEventID()));
			
		}
		
		/*
		 * Menu Events
		 */
		if (String.valueOf(event.getEventID()).startsWith("11")) { //Menu events
			scriptLoader.invokeFunction(new EventDecoder()
					.decode(event.getEventID()), TextResult.type(event).toString());
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
			Main.simTriggersGUI.show();
		}
	} 
	
	
	/*
	 * Triggered on client connection opening
	 */
	@Override
	public void handleOpen(SimConnect sc, RecvOpen open) {
		triggersManager.triggerAll(TriggerType.CONNECTION_OPEN); //Trigger connection open
		
		/* Initialize modules */
		new ModuleManager().initModules();
		
		/* Load module's scripts */
		new ScriptLoader().loadModules();
		
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
			 * Menu events
			 */
			sc.menuAddItem("SimTriggers", EVENT.ADDONSMENU_SIMTRIGGERS, 0); //Add SimTriggers tab in addons menu
	        sc.menuAddSubItem(EVENT.ADDONSMENU_SIMTRIGGERS, "Reload Scripts...", EVENT.SIMTRIGGERSTAB_RELOADSCRIPTS, 0); //Add Reload Scripts option under SimTriggers tab
	        sc.menuAddSubItem(EVENT.ADDONSMENU_SIMTRIGGERS, "Open Modules GUI", EVENT.SIMTRIGGERSTAB_OPENGUI, 0); //Add Open Modules GUI option under SimTriggers tab
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Triggered on client connection termination
	 */
	@Override
	public void handleQuit(SimConnect sc, RecvQuit quit) {
		triggersManager.triggerAll(TriggerType.CONNECTION_CLOSE);
		logger.log("Connection between client terminated: "+quit.getRawID());
		Main.simTriggersGUI.shut();
	}
	
	/*
	 * Handle data flow
	 */
	@Override
	public void handleSimObjectType(SimConnect sc, RecvSimObjectDataByType data) {
		/* Debug */
		logger.debug("Data Flow: "+new DataRequestDecoder()
				.decodeRequestID(data.getRequestID())+", "+new DataRequestDecoder().decodeDataDefinitionID(data.getDefineID()));

		if (String.valueOf(data.getDefineID()).startsWith("12")) { //FLOAT 64
			scriptLoader.invokeFunction(new DataRequestDecoder()
					.decodeRequestID(data.getRequestID()), ""+data.getDataFloat64());
			
		} else if (String.valueOf(data.getDefineID()).startsWith("15")) { //INT 64
			scriptLoader.invokeFunction(new DataRequestDecoder()
					.decodeRequestID(data.getRequestID()), ""+data.getDataInt64());
			
		} else if (String.valueOf(data.getDefineID()).startsWith("23")) { //STRING 260
			scriptLoader.invokeFunction(new DataRequestDecoder()
					.decodeRequestID(data.getRequestID()), data.getDataString260());
			
		} else if (String.valueOf(data.getDefineID()).startsWith("11")) { //FLOAT 32
			scriptLoader.invokeFunction(new DataRequestDecoder()
					.decodeRequestID(data.getRequestID()), data.getDataFloat32());
			
		} else if (String.valueOf(data.getDefineID()).startsWith("13")) { //INITPOSITION
			scriptLoader.invokeFunction(new DataRequestDecoder()
					.decodeRequestID(data.getRequestID()), data.getInitPosition());
			
		} else if (String.valueOf(data.getDefineID()).startsWith("14")) { //INT 32
			scriptLoader.invokeFunction(new DataRequestDecoder()
					.decodeRequestID(data.getRequestID()), data.getDataInt32());
			
		} else if (String.valueOf(data.getDefineID()).startsWith("16")) { //INVALID
			scriptLoader.invokeFunction(new DataRequestDecoder()
					.decodeRequestID(data.getRequestID()), (Object[]) null);
			
		} else if (String.valueOf(data.getDefineID()).startsWith("17")) { //LATLONALT
			scriptLoader.invokeFunction(new DataRequestDecoder()
					.decodeRequestID(data.getRequestID()), data.getLatLonAlt());
			
		} else if (String.valueOf(data.getDefineID()).startsWith("18")) { //MAKERSTATE
			scriptLoader.invokeFunction(new DataRequestDecoder()
					.decodeRequestID(data.getRequestID()), data.getMarkerState());
			
		} else if (String.valueOf(data.getDefineID()).startsWith("19")) { //MAX
			scriptLoader.invokeFunction(new DataRequestDecoder()
					.decodeRequestID(data.getRequestID()), (Object[]) null);
			
		} else if (String.valueOf(data.getDefineID()).startsWith("21")) { //STRING 128
			scriptLoader.invokeFunction(new DataRequestDecoder()
					.decodeRequestID(data.getRequestID()), data.getDataString128());
			
		} else if (String.valueOf(data.getDefineID()).startsWith("22")) { //STRING 256
			scriptLoader.invokeFunction(new DataRequestDecoder()
					.decodeRequestID(data.getRequestID()), data.getDataString256());
			
		} else if (String.valueOf(data.getDefineID()).startsWith("24")) { //STRING 32
			scriptLoader.invokeFunction(new DataRequestDecoder()
					.decodeRequestID(data.getRequestID()), data.getDataString32());
			
		} else if (String.valueOf(data.getDefineID()).startsWith("25")) { //STRING 64
			scriptLoader.invokeFunction(new DataRequestDecoder()
					.decodeRequestID(data.getRequestID()), data.getDataString64());
			
		} else if (String.valueOf(data.getDefineID()).startsWith("26")) { //STRING 8
			scriptLoader.invokeFunction(new DataRequestDecoder()
					.decodeRequestID(data.getRequestID()), data.getDataString8());
			
		} else if (String.valueOf(data.getDefineID()).startsWith("27")) { //STRING V
			scriptLoader.invokeFunction(new DataRequestDecoder()
					.decodeRequestID(data.getRequestID()), data.getDataStringV());
			
		} else if (String.valueOf(data.getDefineID()).startsWith("28")) { //WAYPOINT
			scriptLoader.invokeFunction(new DataRequestDecoder()
					.decodeRequestID(data.getRequestID()), data.getWaypoint());
			
		} else if (String.valueOf(data.getDefineID()).startsWith("29")) { //XYZ
			scriptLoader.invokeFunction(new DataRequestDecoder()
					.decodeRequestID(data.getRequestID()), data.getXYZ());
			
		}
	}
}
