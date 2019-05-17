package com.IconPippi.simtriggers.triggers;

public class RegisterTrigger {
	
	/**
	 * Registers a new ConnectionOpenTrigger
	 * @param Trigger's method
	 */
	public static void registerConnectionOpen(String method) { 
		TriggersManager.connectionOpenTriggers.add(new ConnectionOpenTrigger(method));
	}
	
	/**
	 * Registers a new ConnectionOpenTrigger
	 * @param Trigger's method
	 */
	public static void registerConnectionClose(String method) { 
		TriggersManager.connectionCloseTriggers.add(new ConnectionCloseTrigger(method));
	}
	
	/**
	 * Registers a new ThrottleTrigger
	 * @param Trigger's method
	 */
	public static ThrottleTrigger registerThrottle(String method) { //ThrottleTrigger type is because of .setThrottleAction method
		ThrottleTrigger tt = new ThrottleTrigger(method);
		TriggersManager.throttleTriggers.add(tt);
		return tt;
	}
	
	/**
	 * Registers a new specified trigger
	 * @param Trigger to register
	 * @param Trigger's method
	 */
	public static void register(TriggerType triggerType, String method) {
		switch (triggerType) {
		case CONNECTION_OPEN:
			TriggersManager.connectionOpenTriggers.add(new ConnectionOpenTrigger(method));
			break;
		case CONNECTION_CLOSE:
			TriggersManager.connectionCloseTriggers.add(new ConnectionCloseTrigger(method));
			break;
		case THROTTLE:
			ThrottleTrigger tt = new ThrottleTrigger(method);
			TriggersManager.throttleTriggers.add(tt);
		default:
			break; 
		}
	}
}
