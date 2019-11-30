package com.simtriggers.fsx.simulator

import com.simtriggers.fsx.SimTriggers
import com.simtriggers.fsx.event.RegisterEvent
import flightsim.simconnect.SimConnect

/**
 * 10/08/2019
 * FSX tab menu (will be displayed under Add-Ons tab)
 *
 * @author IconPippi
 */
class TabMenu(private val handlerFunction: String) {

    /** SimConnect constant */
    private val sc: SimConnect = SimTriggers.sc

    /** Event registerer */
    private val registerEvent: RegisterEvent = RegisterEvent()

    /** Tab menu event ID */
    private val tabMenuID: Int

    /**
     * Register the menu's event
     */
    init {
        tabMenuID = registerEvent.registerTabMenu(handlerFunction)
    }

    private var name: String? = null
    private val subTabs = HashMap<String, String>()

    /**
     * Set the menu's name
     */
    fun setName(name: String) {
        this.name = name
    }

    /**
     * Add sub-tabs specifying their name and handler function
     * @param name Name of the tab
     * @param handlerFunction Handler's function
     */
    fun addSubTab(name: String, handlerFunction: String) {
        subTabs.put(name, handlerFunction)
    }

    /**
     * Show the menu
     */
    fun show() {
        sc.menuAddItem(name, tabMenuID, 0)
        subTabs.forEach {
            sc.menuAddSubItem(tabMenuID, it.component1(), registerEvent.registerTabMenu(it.component2()), 0)
        }
    }

}