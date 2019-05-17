package com.IconPippi.simtriggers.triggers;

public class ConnectionOpenTrigger extends Trigger {
	
	protected ConnectionOpenTrigger(String method) {
		super(method);
	}

	@Override
	protected void trigger() {
		scriptLoader.invokeFunction(method, (Object[]) null);
	}

}
