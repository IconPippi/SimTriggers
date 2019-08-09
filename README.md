# SimTriggers
<p align="left">
     <a href="https://discord.gg/cKdyggh">
        <img src="https://discordapp.com/api/guilds/609135276874399798/embed.png" alt="Discord" />
    </a>
</p>        
SimTriggers is a Flight Simulator X add-on which allows live scripting in JavaScript providing a series of useful triggers* and functions.

## How to:

### Install this add-on
A proper installer is on the TODO list. :)

### Install a module
Drag the module's files into the conveniently provided ./SimTriggers/modules directory (can be found in the same folder the .jar file is stored)

### Make a module
Making a module is very simple; create a new folder inside the modules folder inside which you'll create a new file called metadata.json which will store all module's information, here's an example of it:
```json
{
    "moduleName":"SuperDuperModule",
    "version":"0.1.0",
    "authors":["IconPippi"],
    "description":"FIRST SimTriggers module!"
}
```
After this step your module will be loaded every time SimTriggers connects with FSX, but we still need some code to make it do what we want. SimTriggers scripting system is based on Triggers which are pretty much events, for this example we will be using ConnectionOpen and ConnectionClose triggers (their use is pretty self explanatory).
```js
/*
 * SuperDuperModule
 * FIRST SimTriggers module!
 * By IconPippi
 */

//Register ConnectionOpen and ConnectionClose triggers
TriggerRegister.registerConnectionOpen("connectionOpen");
TriggerRegister.registerConnectionClose("connectionClose");

function connectionOpen() {
    print("[SuperDuperModule] Module successfully loaded!");
}

function connectionClose() {
    print("[SuperDuperModule] Unloading module!");
}
```
After you completed the previous steps you can load up your simulator, run SimTriggers and your module should be up and running!

## More Examples:

### Keybinds
Keybinding has never been easier. It works just like every other trigger...
```js
TriggerRegister.registerKey("X", "keyX")

function keyX() {
    print("Pressed key X!");
}
```

### Menus
To create a simple menu you will take advantage of the Menu class which provides all the methods you need to create and handle a Flight Simulator X interface. Here's an example:
```js
TriggerRegister.registerKey("Shift+U", "toggleMenu");

var showMenu = false;

/* Create a simple menu. */
var simpleMenu = new Menu("menuHandler"); //Create the menu specifying the handler function
simpleMenu.setName("SuperDuperModule"); //Give it a name
simpleMenu.setTitle("Simple Menu"); //Give it a title
simpleMenu.addOption("Option 1"); //MENU_SELECT_1
simpleMenu.addOption("Option 2"); //MENU_SELECT_2

/* Handle the menu events */
function menuHandler(menuInput) {
    switch (menuInput) {
        case "DISPLAYED": //On menu display
            print("Menu displayed!");
            break;
        case "REMOVED": //On menu hide action
            print("Menu hidden!");
            break;
        case "MENU_SLECT_1": //Example Option 1
            print("Option 1");
            menuShow = false; //Set menuShow to false because once you select an option the menu automatically closes
            break;
        case "MENU_SELECT_2": //Example Option 2
            print("Option 2");
            menuShow = false;
            break;
    }
}

/* Keybind function */
function toggleMenu() {
    if (menuShow) {
        menuShow = false;
        simpleMenu.hide();
    } else {
        menuShow = true;
        simpleMenu.show()
    }
}
```

### Text lines
Text lines are another feature of FSX, they are those green lines with colored text placed in the top part of your screen.
```js
TriggerRegister.registerKey("Shift+U", "textLine");

var speedBroadcast = new TextLine();
speedBroadcast.setTimeout(5.0)
speedBroadcast.setTextColor(TextType.PRINT_RED)
speedBroadcast.setText("Hello world!")

function textLine() {
    speedBroadcast.show()
}
```

### Data requests
The data request process consists in two simple steps: creating the request and handling the response; here's an example:
```js
TriggerRegister.registerKey("M", "requestSpeed");

function requestSpeed() {
    /* Create a request for the aircraft speed specifying the callback function where the data will be delivered */
    Aircraft.requestAirspeed("speed");
}

/* Create the callback function specifying an argument where the data will be passed */
function speed(data) {
    print("Airspeed: "+data);
}
```

### Variable setting
Setting variables is very similar to requesting data, the difference is you don't need a callback function
```js
TriggerRegister.registerKey("Shift+L", "key");

function key() {
    Aircraft.setBank(2.4324325)
}
```

# Credits
- Idea & some code: [ChatTriggers](https://www.chattriggers.com/)
