
/* Data */
var DataRequest = Java.type("com.IconPippi.simtriggers.data.request.DataRequest");
var DataSetter = Java.type("com.IconPippi.simtriggers.data.set.DataSetter");

/* Triggers */
var AntiIceTrigger = Java.type("com.IconPippi.simtriggers.triggers.AntiIceTrigger");
var ConnectionOpenTrigger = Java.type("com.IconPippi.simtriggers.triggers.ConnectionOpenTrigger");
var ConnectionCloseTrigger = Java.type("com.IconPippi.simtriggers.triggers.ConnectionCloseTrigger");
var KeyTrigger = Java.type("com.IconPippi.simtriggers.triggers.KeyTrigger");
var MagnetoTrigger = Java.type("com.IconPippi.simtriggers.triggers.MagnetoTrigger");
var MixtureTrigger = Java.type("com.IconPippi.simtriggers.triggers.MixtureTrigger");
var PropellerTrigger = Java.type("com.IconPippi.simtriggers.triggers.PropellerTrigger");
var ThrottleTrigger = Java.type("com.IconPippi.simtriggers.triggers.ThrottleTrigger");

var RegisterTrigger = Java.type("com.IconPippi.simtriggers.triggers.RegisterTrigger");
var TriggerType = Java.type("com.IconPippi.simtriggers.triggers.TriggerType");

/* Wrappers */
var Menu = Java.type("com.IconPippi.simtriggers.wrappers.Menu");
var SimTriggers = Java.type("com.IconPippi.simtriggers.wrappers.SimTriggers");
var Aircraft = Java.type("com.IconPippi.simtriggers.wrappers.Aircraft");
var TextLine = Java.type("com.IconPippi.simtriggers.wrappers.TextLine");

/* SimConnect */
var TextType = Java.type("flightsim.simconnect.TextType");

/* SimTriggers */
var Console = Java.type("com.IconPippi.simtriggers.gui.console.Console");
var Logger = Java.type("com.IconPippi.simtriggers.util.Logger");

/**
 * Console util
 */
function print(s) {
    Console.println(s);
} 