package com.IconPippi.simtriggers.triggers;

public class ThrottleTrigger extends Trigger {

	private String throttleAction;
	
	protected ThrottleTrigger(String method) {
		super(method);
	}

	protected void trigger(String throttleAction) { //Run the trigger only on a specified throttle action
		if (throttleAction == this.throttleAction) {
			scriptLoader.invokeFunction(method, (Object[]) null);
		}
	}

	public void setThrottleAction(String action) {
		this.throttleAction = action;
	}

	@Override
	protected void trigger() { //Run the method on any throttle action
		if (throttleAction != null) return;
		scriptLoader.invokeFunction(method, (Object[]) null);
	}
	
	public String getThrottleAction() {
		return this.throttleAction;
	}
}
