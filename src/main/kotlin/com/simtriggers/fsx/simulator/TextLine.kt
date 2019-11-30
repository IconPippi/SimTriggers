package com.simtriggers.fsx.simulator

import com.simtriggers.fsx.SimTriggers
import com.simtriggers.fsx.event.RegisterEvent
import flightsim.simconnect.SimConnect
import flightsim.simconnect.TextType

/**
 * 06/08/2019
 * FSX text line
 *
 * @author IconPippi
 */
class TextLine {

    /** SimTriggers constant */
    private val sc: SimConnect = SimTriggers.sc

    /** Event registerer constant */
    private val eventRegisterer: RegisterEvent = RegisterEvent()

    /** Text line identifier */
    private val textLineID: Int


    private var text: String? = null
    private var timeout: Float? = null
    private var textColor: TextType? = null

    /**
     * Create a new text line
     */
    init {
        textLineID = eventRegisterer.registerTextLineEvent()
    }

    /**
     * Set the text line's text
     * @param text Text line's text
     */
    fun setText(text: String) {
        this.text = text
    }

    /**
     * Set the text line's timeout
     * @param timeout Text line's timeout
     */
    fun setTimeout(timeout: Float) {
        this.timeout = timeout
    }

    /**
     * Set the text line's color
     * @param color Text line's color
     */
    fun setTextColor(color: TextType) {
        this.textColor = color
    }

    /**
     * Show the text line
     */
    fun show() {
        sc.text(textColor, timeout!!, textLineID, text)
    }

}