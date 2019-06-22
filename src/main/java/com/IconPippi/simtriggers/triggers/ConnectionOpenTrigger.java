package com.IconPippi.simtriggers.triggers;

public class ConnectionOpenTrigger extends Trigger {
	
	protected ConnectionOpenTrigger(String method) {
		super(method);
	}

	@Override
	protected void trigger() {
		this.getScriptLoader().invokeFunction(method, (Object[]) null);
	}

}
