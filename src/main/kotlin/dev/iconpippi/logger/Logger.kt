package dev.iconpippi.logger

import java.util.*

/**
 * 01/08/2019
 * This class contains all the logging methods
 *
 * @author IconPippi
 */
object Logger {

    private const val RED = "\u001B[31m"
    private const val YELLOW = "\u001B[33m"
    private const val BLUE = "\u001B[34m"
    private const val PURPLE = "\u001B[35m"
    private const val RESET = "\u001B[0m"

    /**
     * Print a white log message into the console
     * FORMAT: [$time / LOG] $message
     */
    @JvmStatic fun log(msg: String) {
        System.out.println("[${Date().hours}:${Date().minutes}:${Date().seconds} / LOG] $msg$RESET")
    }

    /**
     * Print a red error message into the console
     * FORMAT: [$time / ERROR] $message
     */
    @JvmStatic fun error(msg: String) {
        System.out.println("$RED[${Date().hours}:${Date().minutes}:${Date().seconds} / ERROR] $msg$RESET")
    }

    /**
     * Print a yellow warning message into the console
     * FORMAT: [$time / WARNING] $message
     */
    @JvmStatic fun warning(msg: String) {
        System.out.println("$YELLOW[${Date().hours}:${Date().minutes}:${Date().seconds} / WARNING] $msg$RESET")
    }

    /**
     * Print a blue major message into the console
     * FORMAT: [$time / MAJOR] $message
     */
    @JvmStatic fun major(msg: String) {
        System.out.println("$BLUE[${Date().hours}:${Date().minutes}:${Date().seconds} / MAJOR] $msg$RESET")
    }

    /**
     * Print a purple debug message into the console
     * FORMAT: [$time / DEBUG] $message
     */
    @JvmStatic fun debug(msg: String) {
        System.out.println("$PURPLE[${Date().hours}:${Date().minutes}:${Date().seconds} / DEBUG] $msg$RESET")
    }

}