package com.IconPippi.simtriggers.triggers;

import java.util.ArrayList;
import java.util.List;

public class TriggersManager {
	
	/*
	 * Triggers lists, contains all registered triggers for each TriggerType
	 */
	protected static List<ConnectionOpenTrigger> connectionOpenTriggers = new ArrayList<>(); //ConnectionOpenTrigger
	protected static List<ConnectionCloseTrigger> connectionCloseTriggers = new ArrayList<>(); //ConnectionCloseTrigger
	protected static List<ThrottleTrigger> throttleTriggers = new ArrayList<>(); //ThrottleTrigger
	
	/**
	 * Triggers all the triggers for the specified TriggerType
	 * @param Type of trigger
	 */
	public void triggerAll(TriggerType triggerType) {
		switch(triggerType) {
		case CONNECTION_OPEN:
			for (ConnectionOpenTrigger connectionOpenTrigger : connectionOpenTriggers) {
				connectionOpenTrigger.trigger();
			}
			break;
		case CONNECTION_CLOSE:
			for (ConnectionCloseTrigger connectionCloseTrigger : connectionCloseTriggers) {
				connectionCloseTrigger.trigger();
			}
			break;
		case THROTTLE:
			for (ThrottleTrigger throttleTrigger : throttleTriggers) {
				throttleTrigger.trigger(); //Trigger without specifying a throttle action
			}
			break;
		}
	}
	
	/**
	 * Triggers all Throttle triggers, used for triggers with a throttle action set
	 * @param Throttle Action
	 */
	public void triggerAllThrottle(String throttleAction) {
		for (ThrottleTrigger throttleTrigger : throttleTriggers) {
			throttleTrigger.trigger(throttleAction);
		}
	}
}
