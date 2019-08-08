package dev.iconpippi.logger

import java.util.*

/**
 * 01/08/2019
 * This class contains all the logging methods
 *
 * @author IconPippi
 */
class Logger {

    companion object {
        /** Current time (hours:minutes:seconds) */
        private val time: String = "${Date().hours}:${Date().minutes}:${Date().seconds}"

        /**
         * Colors
         */
        private val RED = "\u001B[31m"
        private val YELLOW = "\u001B[33m"
        private val BLUE = "\u001B[34m"
        private val PURPLE = "\u001B[35m"
        private val RESET = "\u001B[0m"

        /**
         * Print a white log message into the console
         * FORMAT: [$time / LOG] $message
         */
        fun log(msg: String) {
            System.out.println("[$time / LOG] $msg$RESET")
        }

        /**
         * Print a red error message into the console
         * FORMAT: [$time / ERROR] $message
         */
        fun error(msg: String) {
            System.out.println("$RED[$time / ERROR] $msg$RESET")
        }

        /**
         * Print a yellow warning message into the console
         * FORMAT: [$time / WARNING] $message
         */
        fun warning(msg: String) {
            System.out.println("$YELLOW[$time / WARNING] $msg$RESET")
        }

        /**
         * Print a blue major message into the console
         * FORMAT: [$time / MAJOR] $message
         */
        fun major(msg: String) {
            System.out.println("$BLUE[$time / MAJOR] $msg$RESET")
        }

        /**
         * Print a purple debug message into the console
         * FORMAT: [$time / DEBUG] $message
         */
        fun debug(msg: String) {
            System.out.println("$PURPLE[$time / DEBUG] $msg$RESET")
        }
    }

}