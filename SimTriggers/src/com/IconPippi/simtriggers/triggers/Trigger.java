package com.IconPippi.simtriggers.triggers;

import com.IconPippi.simtriggers.scripting.ScriptLoader;

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
	 * Scirpt loader to invoke functions
	 */
	protected final ScriptLoader scriptLoader = new ScriptLoader();
	
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
}
