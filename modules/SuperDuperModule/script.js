/*
 * FIRST SimTriggers module!
 * By IconPippi
 * SuperDuperModule
 */

//Import all needed classes
var EVENT = Java.type("com.IconPippi.simtriggers.events.EVENT");
var TextType = Java.type("flightsim.simconnect.TextType");
var SimTriggers = Java.type("com.IconPippi.simtriggers.wrappers.SimTriggers");
var RegisterTrigger = Java.type("com.IconPippi.simtriggers.triggers.RegisterTrigger");
var TriggerType = Java.type("com.IconPippi.simtriggers.triggers.TriggerType");
var Menu = Java.type("com.IconPippi.simtriggers.wrappers.Menu");
var Costants = Java.type("flightsim.simconnect.SimConnectConstants");

//Register ConnectionOpen and ConnectionClose triggers
RegisterTrigger.registerConnectionOpen("connectionOpen");
RegisterTrigger.registerConnectionClose("connectionClose");

function connectionOpen() {
    print("[SuperDuperModule] Module successfully loaded!");
    
    //Print on FSX screen "[SuperDuperModule] Module successfully loaded!" for 5 seconds
    SimTriggers.getSimulator().text(TextType.PRINT_RED, 5, EVENT.RELOADSCRIPTS_TEXT, "[SuperDuperModule] Module successfully loaded!");
}

function connectionClose() {
    print("[SuperDuperModule] Unloading module!");
}

//Register a Throttle trigger with a throttle action specification
RegisterTrigger.registerThrottle("throttleFull").setThrottleAction("THROTTLE_FULL");
RegisterTrigger.registerThrottle("throttleDecrease").setThrottleAction("THROTTLE_DECR");

RegisterTrigger.registerMixture("mixture").setMixtureAction("MIXTURE_RICH");

function mixture() {
    print("Mixture increased");
}

//Create a simple menu.
var throttleMenu = new Menu("menuHandler");
throttleMenu.setName("SuperDuperModule");
throttleMenu.setTitle("Throttle Full!");
throttleMenu.addOption("Throttle Cut");

function menuHandler(menuInput) {
    switch (menuInput) {
    case "DISPLAYED":
        print("Menu displayed!");
        break;
    case "REMOVED":
        print("Menu hidden!");
        break;
    default:
        print("Selected "+menuInput+" option!");
        break;
    }
}

function throttleFull() {
    print("Throttle Full");

    throttleMenu.show();
}

function throttleDecrease() {
    print("Throttle Decrease");

    throttleMenu.hide();
}

function cutThrottle() {
    //TODO: Cut throttle
}