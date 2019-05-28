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

# More Examples:

## Menus
To create a simple menu you will have to use the take advantage of the Menu wrapper which provides all the methods you need to create and handle a Flight Simulator X interface. Here's an example:
```js
var RegisterTrigger = Java.type("com.IconPippi.simtriggers.triggers.RegisterTrigger");
var Menu = Java.type("com.IconPippi.simtriggers.wrappers.Menu");

RegisterTrigger.registerConnectionOpen("connectionOpen");
RegisterTrigger.registerConnectionClose("connectionClose");

//Create a simple menu.
var exampleMenu = new Menu("menuHandler"); //Create the menu specifying the handler function
throttleMenu.setName("SuperDuperModule"); //Give it a name
throttleMenu.setTitle("Example Menu"); //Give it a title
throttleMenu.addOption("Example Option 1"); //MENU_SELECT_1
throttleMenu.addOption("Example Option 2"); //MENU_SELECT_2

//Handle the menu events
function menuHandler(menuInput) {
    switch (menuInput) {
    case "DISPLAYED": //On menu display
        print("Menu displayed!");
        break;
    case "REMOVED": //On menu hide action
        print("Menu hidden!");
        break;
    case "MENU_SLECT_1": //Example Option 1
        print("Works!");
        break;
    case "MENU_SELECT_2": //Example Option 2
        print("Working!");
        break;
    }
}

function connectionOpen() {
    exampleMenu.show();
}

function connectionClose() {
    exampleMenu.hide();
}
``` 