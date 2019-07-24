package com.IconPippi.simtriggers.triggers;

import com.IconPippi.simtriggers.events.EventFactory;
import com.IconPippi.simtriggers.scripting.ScriptLoader;
import com.IconPippi.simtriggers.util.Logger;

/**
 * This class represents a generic trigger
 * @author IconPippi
 *
 */
public abstract class Trigger {
	
	/*
	 * Trigger's method name
	 */
	protected final String method;
	
	/*
	 * EventFactory
	 */
	private final EventFactory eventFactory = new EventFactory();
	/*
	 * Logger
	 */
	private final Logger logger = new Logger();
	/*
	 * Scirpt loader to invoke functions
	 */
	private final ScriptLoader scriptLoader = new ScriptLoader();
	
	/**
	 * Initialize trigger
	 * @param method method's name
	 */
	protected Trigger(String method) {
		this.method = method;
	}
	
	/**
	 * Runs the trigger
	 */
	protected abstract void trigger();
	
	public EventFactory getEventFactory() {
		return eventFactory;
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	public ScriptLoader getScriptLoader() {
		return scriptLoader;
	}
}
