package com.IconPippi.simtriggers.triggers;

import java.io.IOException;

import com.IconPippi.simtriggers.EVENT;
import com.IconPippi.simtriggers.event.EventFactory;
import com.IconPippi.simtriggers.utils.Logger;

public class ThrottleTrigger extends Trigger {

	private String throttleAction;
	
	private final EventFactory eventFactory = new EventFactory();
	private final Logger logger = new Logger();
	
	protected ThrottleTrigger(String method) {
		super(method);
	}
	
	@Override
	protected void trigger() { //Run the method on any throttle action
		if (throttleAction != null) return;
		scriptLoader.invokeFunction(method, (Object[]) null);
	}
	
	protected void trigger(String throttleAction) { //Run the trigger only on a specified throttle action
		if (throttleAction == this.throttleAction) {
			scriptLoader.invokeFunction(method, (Object[]) null);
		}
	}

	public void setThrottleAction(String action) {
		this.throttleAction = action;
		
		try {
			eventFactory.buildClientEvent(action, action, EVENT.GROUP_THROTTLE);
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	public String getThrottleAction() {
		return this.throttleAction;
	}
}
