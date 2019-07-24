package com.IconPippi.simtriggers.triggers;

import java.io.IOException;

import com.IconPippi.simtriggers.events.EVENT;
import com.IconPippi.simtriggers.util.Logger;

public class MixtureTrigger extends Trigger {
	
	private String mixtureAction;
	
	protected MixtureTrigger(String method) {
		super(method);
	}

	@Override
	protected void trigger() {
		if (mixtureAction != null) return;
		this.getScriptLoader().invokeFunction(method, (Object[]) null);
	}
	
	protected void trigger(String mixtureAction) {
		if (mixtureAction == this.mixtureAction) {
			this.getScriptLoader().invokeFunction(method, (Object[]) null);
		}
	}
	
	public void setMixtureAction(String action) {
		this.mixtureAction = action;
		
		try {
			this.getEventFactory().buildClientEvent(action, action, EVENT.GROUP_MIXTURE);
		} catch (IOException e) {
			Logger.error(e.toString());
		}
	}
	
	public String getMixtureAction() {
		return this.mixtureAction;
	}
}
