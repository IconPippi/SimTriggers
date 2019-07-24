package com.IconPippi.simtriggers.triggers;

import java.io.IOException;

import com.IconPippi.simtriggers.events.EVENT;
import com.IconPippi.simtriggers.util.Logger;

public class AntiIceTrigger extends Trigger {
	
	private String antiIceAction;
	
	protected AntiIceTrigger(String method) {
		super(method);
	}

	@Override
	protected void trigger() {
		if (antiIceAction != null) return;
		this.getScriptLoader().invokeFunction(method, (Object[]) null);
	}
	
	protected void trigger(String antiIceAction) {
		if (antiIceAction == this.antiIceAction) {
			this.getScriptLoader().invokeFunction(method, (Object[]) null);
		}
	}
	
	public void setAntiIceAction(String action) {
		this.antiIceAction = action;
		
		try {
			this.getEventFactory().buildClientEvent(action, action, EVENT.GROUP_ANTI_ICE);
		} catch (IOException e) {
			Logger.error(e.toString());
		}
	} 
	
	public String getAntiIceAction() {
		return antiIceAction;
	}
}
