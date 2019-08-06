package com.simtriggers.fsx.simulator

import com.simtriggers.fsx.SimTriggers
import com.simtriggers.fsx.event.EventRegisterer
import flightsim.simconnect.SimConnect
import java.util.ArrayList

/**
 * 06/08/2019
 * This class represents a FSX menu
 *
 * @author IconPippi
 */
class Menu(private val menuHandler: String) {

    /** Simulator constant */
    private val sc: SimConnect = SimTriggers.sc

    /** Event registerer */
    private val eventRegisterer: EventRegisterer = EventRegisterer()

    /** Menu identifier */
    private val menuID: Int

    private var name: String? = null
    private var title: String? = null
    private var options = ArrayList<String>()

    /**
     * Register a new Menu instance
     */
    init {
        menuID = eventRegisterer.registerMenuEvent(menuHandler)
    }

    /**
     * Set the menu's name
     * @param name menu's name
     */
    fun setName(name: String) {
        this.name = name
    }

    /**
     * Set the menu's title
     * @param title menu's title
     */
    fun setTitle(title: String) {
        this.title = title
    }

    /**
     * Add a menu option
     * @param option Option text
     */
    fun addOption(option: String) {
        options.add(option)
    }

    /**
     * Show the menu
     */
    fun show() {
        sc.menu(0f, menuID, name, title, *options.toArray((arrayOfNulls<String>(0))))
    }

    /**
     * Hide the menu
     */
    fun hide() {
        sc.menu(0.0f, menuID, "", "", "")
    }

}