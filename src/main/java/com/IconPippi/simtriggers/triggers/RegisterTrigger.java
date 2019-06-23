package com.IconPippi.simtriggers.triggers;

/**
 * This class is delegated to register all kinds of triggers
 * @author IconPippi
 *
 */
public class RegisterTrigger {
	
	/**
	 * Registers a new ConnectionOpenTrigger
	 * @param method Trigger's method
	 */
	public static void registerConnectionOpen(String method) { 
		TriggersManager.connectionOpenTriggers.add(new ConnectionOpenTrigger(method));
	}
	
	/**
	 * Registers a new ConnectionOpenTrigger
	 * @param method Trigger's method
	 */
	public static void registerConnectionClose(String method) { 
		TriggersManager.connectionCloseTriggers.add(new ConnectionCloseTrigger(method));
	}
	
	/**
	 * Registers a new ThrottleTrigger
	 * @param method Trigger's method
	 */
	public static ThrottleTrigger registerThrottle(String method) { //ThrottleTrigger type is because of .setThrottleAction method
		ThrottleTrigger tt = new ThrottleTrigger(method);
		TriggersManager.throttleTriggers.add(tt);
		return tt;
	}

	/**
	 * Registers a new MixtureTrigger
	 * @param method Trigger's method
	 */
	public static MixtureTrigger registerMixture(String method) {
		MixtureTrigger mt = new MixtureTrigger(method);
		TriggersManager.mixtureTriggers.add(mt);
		return mt;
	}
	
	/**
	 * Registers a new KeyTrigger
	 * @param method Trigger's method
	 */
	public static KeyTrigger registerKey(String method) {
		KeyTrigger kt = new KeyTrigger(method);
		TriggersManager.keyTriggers.add(kt);
		return kt;
	}
	
	/**
	 * Registers a new PropellerTrigger
	 * @param method Trigger's method
	 */
	public static PropellerTrigger registerPropeller(String method) {
		PropellerTrigger pt = new PropellerTrigger(method);
		TriggersManager.propellerTriggers.add(pt);
		return pt;
	}
	
	/**
	 * Registers a new MagnetoTrigger
	 * @param method Trigger's method
	 */
	public static MagnetoTrigger registerMagneto(String method) {
		MagnetoTrigger mt = new MagnetoTrigger(method);
		TriggersManager.magnetoTriggers.add(mt);
		return mt;
	}
	  
	/**
	 * Registers a new specified trigger
	 * @param triggerType Trigger to register
	 * @param method Trigger's method
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
			TriggersManager.throttleTriggers.add(new ThrottleTrigger(method));
			break;
		case MIXTURE:
			TriggersManager.mixtureTriggers.add(new MixtureTrigger(method));
			break;
		case KEYS:
			TriggersManager.keyTriggers.add(new KeyTrigger(method));
			break;
		case PROPELLER:
			TriggersManager.propellerTriggers.add(new PropellerTrigger(method));
		case MAGNETO:
			TriggersManager.magnetoTriggers.add(new MagnetoTrigger(method));
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
		TriggersManager.propellerTriggers.clear();
		TriggersManager.magnetoTriggers.clear();
	}
	
}
