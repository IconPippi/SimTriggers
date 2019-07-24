package com.IconPippi.simtriggers.triggers;

import java.io.IOException;

import com.IconPippi.simtriggers.events.EVENT;
import com.IconPippi.simtriggers.util.Logger;

public class PropellerTrigger extends Trigger {
	
	private String propellerAction;
	
	protected PropellerTrigger(String method) {
		super(method);
	}

	@Override
	protected void trigger() {
		if (propellerAction != null) return;
		this.getScriptLoader().invokeFunction(method, (Object[]) null);
	}
	
	protected void trigger(String propellerAction) {
		if (propellerAction == this.propellerAction) {
			this.getScriptLoader().invokeFunction(method, (Object[]) null);
		}
	}
	
	public void setPropellerAction(String action) {
		this.propellerAction = action;
		
		try {
			this.getEventFactory().buildClientEvent(action, action, EVENT.GROUP_PROPELLER);
		} catch (IOException e) {
			Logger.error(e.toString());
		}
	}
	
	public String getPropellerAction() {
		return propellerAction;
	}
}
