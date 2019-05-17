package com.IconPippi.simtriggers.triggers;

import com.IconPippi.simtriggers.scripting.ScriptLoader;

public abstract class Trigger {
	
	protected final String method;
	
	protected final ScriptLoader scriptLoader = new ScriptLoader();
	
	protected Trigger(String method) {
		this.method = method;
	}
	
	protected abstract void trigger(); //Run the trigger
}
