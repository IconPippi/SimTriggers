package com.IconPippi.simtriggers.triggers;

public class RegisterTrigger {
	
	private static TriggerManager triggerManager = new TriggerManager(); //Triggers Manager
	
	/**
	 * Registers a new TestTrigger
	 * @param Trigger's method
	 */
	public static void registerTest(Object method) { 
		triggerManager.testTriggers.add(new TestTrigger(method));
	}
	
	/**
	 * Registers a new specified trigger
	 * @param Trigger to register
	 * @param Trigger's method
	 */
	public static void register(TriggerType triggerType, Object method) {
		switch (triggerType) {
		case TEST:
			triggerManager.testTriggers.add(new TestTrigger(method)); 
		}
	}
}
