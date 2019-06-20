var RegisterTrigger = Java.type("com.IconPippi.simtriggers.triggers.RegisterTrigger");

RegisterTrigger.registerKey("testKey").setKey("Shift+U");

function testKey() {
    print("works");
}