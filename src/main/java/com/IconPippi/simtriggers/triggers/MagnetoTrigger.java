package com.IconPippi.simtriggers.triggers;

import java.io.IOException;

import com.IconPippi.simtriggers.events.EVENT;
import com.IconPippi.simtriggers.util.Logger;

public class MagnetoTrigger extends Trigger {

	private String magnetoAction;
	
	protected MagnetoTrigger(String method) {
		super(method);
	}

	@Override
	protected void trigger() {
		if (magnetoAction != null) return;
		this.getScriptLoader().invokeFunction(method, (Object[]) null);
	}
	
	protected void trigger(String magnetoAction) {
		if (magnetoAction == this.magnetoAction) {
			this.getScriptLoader().invokeFunction(method, (Object[]) null);
		}
	}
	
	public void setMagnetoAction(String action) {
		this.magnetoAction = action;
		
		try {
			this.getEventFactory().buildClientEvent(action, action, EVENT.GROUP_MAGNETO);
		} catch (IOException e) {
			Logger.error(e.toString());
		}
	}
	
	public String getMagnetoAction() {
		return magnetoAction;
	}
}
