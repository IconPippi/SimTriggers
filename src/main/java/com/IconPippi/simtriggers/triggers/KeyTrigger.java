package com.IconPippi.simtriggers.triggers;

import java.io.IOException;

import com.IconPippi.simtriggers.events.EVENT;
import com.IconPippi.simtriggers.util.Logger;

public class KeyTrigger extends Trigger {
	
	private String key;
	
	protected KeyTrigger(String method) {
		super(method);
	}

	@Override
	protected void trigger() {
		if (key != null) return;
		this.getScriptLoader().invokeFunction(method, (Object[]) null);
	}
	
	protected void trigger(String key) {
		if (key == this.key) {
			this.getScriptLoader().invokeFunction(method, (Object[]) null);
		}
	}
	
	public void setKey(String key) {
		this.key = key;
		
		try {
			this.getEventFactory().buildInputEvent(key, EVENT.GROUP_KEYS);
		} catch (IOException e) {
			Logger.error(e.toString());
		}
	}
	
}
