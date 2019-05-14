package com.IconPippi.simtriggers.triggers;

import java.util.List;

public class TriggerManager {
	
	/*
	 * Triggers lists, contains all registered triggers for each TriggerType
	 */
	public List<TestTrigger> testTriggers; //TestTrigger
	
	/**
	 * Triggers all the triggers for the specified TriggerType
	 * @param Type of trigger
	 */
	public void triggerAll(TriggerType triggerType) {
		switch(triggerType) {
		case TEST:
			/*
			 * for (TestTrigger testTrigger : test) { 
			 * 		testTrigger.trigger();
			 * }
			 */
		}
	}
}
