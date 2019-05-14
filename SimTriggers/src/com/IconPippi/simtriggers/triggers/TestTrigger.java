package com.IconPippi.simtriggers.triggers;

public class TestTrigger {
	
	private Object method;
	
	public TestTrigger(Object method) {
		this.method = method;
	}
	
	public void trigger() {
		method.notifyAll(); //Runs the Trigger's method?
	}
	
}
