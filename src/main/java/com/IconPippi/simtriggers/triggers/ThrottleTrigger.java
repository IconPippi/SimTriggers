package com.IconPippi.simtriggers.triggers;

import java.io.IOException;

import com.IconPippi.simtriggers.events.EVENT;

public class ThrottleTrigger extends Trigger {

	private String throttleAction;
	
	protected ThrottleTrigger(String method) {
		super(method);
	}
	
	@Override
	protected void trigger() { //Run the method on any throttle action
		if (throttleAction != null) return;
		this.getScriptLoader().invokeFunction(method, (Object[]) null);
	}
	
	protected void trigger(String throttleAction) { //Run the trigger only on a specified throttle action
		if (throttleAction == this.throttleAction) {
			this.getScriptLoader().invokeFunction(method, (Object[]) null);
		}
	}

	public void setThrottleAction(String action) {
		this.throttleAction = action;
		
		try {
			this.getEventFactory().buildClientEvent(action, action, EVENT.GROUP_THROTTLE);
		} catch (IOException e) {
			this.getLogger().error(e.toString());
		}
	}
	
	public String getThrottleAction() {
		return this.throttleAction;
	}
}
