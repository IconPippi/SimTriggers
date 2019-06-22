package com.IconPippi.simtriggers.triggers;

public class ConnectionCloseTrigger extends Trigger {

	protected ConnectionCloseTrigger(String method) {
		super(method);
	}

	@Override
	protected void trigger() {
		this.getScriptLoader().invokeFunction(method, (Object[]) null);
	}

}
