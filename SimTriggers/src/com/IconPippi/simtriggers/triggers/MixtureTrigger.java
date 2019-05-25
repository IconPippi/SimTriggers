package com.IconPippi.simtriggers.triggers;

import java.io.IOException;

import com.IconPippi.simtriggers.EVENT;
import com.IconPippi.simtriggers.event.EventFactory;
import com.IconPippi.simtriggers.utils.Logger;

public class MixtureTrigger extends Trigger {
	
	private String mixtureAction;
	
	private final EventFactory eventFactory = new EventFactory();
	private final Logger logger = new Logger();
	
	protected MixtureTrigger(String method) {
		super(method);
	}

	@Override
	protected void trigger() {
		if (mixtureAction != null) return;
		scriptLoader.invokeFunction(method, (Object[]) null);
	}
	
	protected void trigger(String mixtureAction) {
		if (mixtureAction == this.mixtureAction) {
			scriptLoader.invokeFunction(method, (Object[]) null);
		}
	}
	
	public void setMixtureAction(String action) {
		this.mixtureAction = action;
		
		try {
			eventFactory.buildClientEvent(action, action, EVENT.GROUP_MIXTURE);
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}
	
	public String getMixtureAction() {
		return this.mixtureAction;
	}
}
