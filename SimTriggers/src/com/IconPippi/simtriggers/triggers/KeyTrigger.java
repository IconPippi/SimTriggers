package com.IconPippi.simtriggers.triggers;

import java.io.IOException;

import com.IconPippi.simtriggers.EVENT;
import com.IconPippi.simtriggers.event.EventFactory;
import com.IconPippi.simtriggers.utils.Logger;

public class KeyTrigger extends Trigger {
	
	private String key;
	
	private final EventFactory eventFactory = new EventFactory();
	private final Logger logger = new Logger();
	
	protected KeyTrigger(String method) {
		super(method);
	}

	@Override
	protected void trigger() {
		if (key != null) return;
		scriptLoader.invokeFunction(method, (Object[]) null);
	}
	
	protected void trigger(String key) {
		if (key == this.key) {
			scriptLoader.invokeFunction(method, (Object[]) null);
		}
	}
	
	public void setKey(String key) {
		this.key = key;
		
		try {
			eventFactory.buildInputEvent(key, EVENT.GROUP_KEYS);
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
}
