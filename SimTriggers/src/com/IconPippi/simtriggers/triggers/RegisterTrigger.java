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
	 * Registers a new MixtureTrigger
	 * @param Trigger's method
	 */
	public static MixtureTrigger registerMixture(String method) {
		MixtureTrigger mt = new MixtureTrigger(method);
		TriggersManager.mixtureTriggers.add(mt);
		return mt;
	}
	
	/**
	 * Registers a new KeyTrigger
	 * @param Trigger's method
	 */
	public static KeyTrigger registerKey(String method) {
		KeyTrigger kt = new KeyTrigger(method);
		TriggersManager.keyTriggers.add(kt);
		return kt;
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
			break;
		case MIXTURE:
			MixtureTrigger mt = new MixtureTrigger(method);
			TriggersManager.mixtureTriggers.add(mt);
			break;
		case KEYS:
			KeyTrigger kt = new KeyTrigger(method);
			TriggersManager.keyTriggers.add(kt);
			break;
		default:
			break; 
		}
	}
	
	/**
	 * Unregister all triggers
	 */
	public static void unregisterAll() {
		TriggersManager.connectionCloseTriggers.clear();
		TriggersManager.connectionCloseTriggers.clear();
		TriggersManager.throttleTriggers.clear();
		TriggersManager.mixtureTriggers.clear();
		TriggersManager.keyTriggers.clear();
	}
	
}
