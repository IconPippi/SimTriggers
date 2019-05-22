package com.IconPippi.simtriggers.triggers;

public class KeyTrigger extends Trigger {
	
	private String key;
	
	protected KeyTrigger(String method) {
		super(method);
	}

	@Override
	protected void trigger() {
		if (key != null) return;
		scriptLoader.invokeFunction(method, (Object[]) null);
	}
	
	public void setkey(String key) {
		this.key = key;
	}
	
	protected void trigger(String key) {
		if (key == this.key) {
			scriptLoader.invokeFunction(method, (Object[]) null);
		}
	}

}
