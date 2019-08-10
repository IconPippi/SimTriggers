// File name: simTriggersDevKit.js
// Author: IconPippi
// Description: This JavaScript file contains all functions and libraries provided by the SimTriggers add-on so you don't have to import them each time

/* SimConnect API */
var SimConnect = Java.type("flightsim.simconnect.SimConnect");
var TextType = Java.type("flightsim.simconnect.TextType");

/*
 * SimTriggers
 */

/* Triggers */
var TriggerRegister = Java.type("com.simtriggers.fsx.triggers.TriggerRegister");

/* Simulator */
var Menu = Java.type("com.simtriggers.fsx.simulator.Menu");
var TextLine = Java.type("com.simtriggers.fsx.simulator.TextLine");
var TabMenu = Java.type("com.simtriggers.fsx.simulator.TabMenu");

/* Simulator Objects */
var Aircraft = Java.type("com.simtriggers.fsx.simulator.objects.Aircraft");

/* Wrappers */
var RequestData = Java.type("com.simtriggers.fsx.data.request.RequestData");
var SetVariable = Java.type("com.simtriggers.fsx.data.set.SetVariable");