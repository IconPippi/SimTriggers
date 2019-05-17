# SimTriggers
SimTriggers is a Flight Simulator X add-on which allows live scripting in JavaScript providing a series of useful triggers* and wrappers.

# How to install
Simply download the lastest version (can be found in Releases) and run it, it will automatically connect to any open instance of FSX.

# How to install a module
Drag the module's files into the conveniently provided modules folder (can be found in the same folder the .jar file is stored)

# How to make a module
Making a module is very simple; create a new folder inside the modules folder and create a new file called metadata.json which will store all module's information, here's an example of it:
```json
{
    "name":"SuperDuperModule",
    "version":"1.1.1",
    "description":"FIRST SimTriggers module!",
    "authors":["IconPippi"],
    "enabled":true,
    "id":243
}
```
After this step your module will be able to be loaded into FSX, but we still need some code to make it do what we want. SimTriggers scripting system is based on Triggers which are pretty much events, for this example we will be using ConnectionOpen and ConnectionClose triggers (their use is pretty self explanatory).
```js
/*
 * FIRST SimTriggers module!
 * By IconPippi
 * SuperDuperModule
 */

//Import all needed classes
var EVENT = Java.type("com.IconPippi.simtriggers.EVENT");
var TextType = Java.type("flightsim.simconnect.TextType");
var SimTriggers = Java.type("com.IconPippi.simtriggers.wrappers.SimTriggers");
var RegisterTrigger = Java.type("com.IconPippi.simtriggers.triggers.RegisterTrigger");

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
```
After you completed the previous steps you can load up your simulator, run SimTriggers and your module should be up and running!

# Triggers List 
### [Trigger Name // (ENUM_NAME) // Explanation]
- ConnectionOpen (CONNECTION_OPEN) Triggered once connected with the simulator.
- ConnectionClose (CONNECTION_CLOSE) Triggered once the connection with the simulator terminates.