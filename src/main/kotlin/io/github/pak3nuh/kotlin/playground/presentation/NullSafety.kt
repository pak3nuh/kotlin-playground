package io.github.pak3nuh.kotlin.playground.presentation

import java.io.PrintStream

fun platformTypeExample() {
    val platformPrinter = System.out
    val stillPlatform = platformPrinter
    val noLongerPlatform: PrintStream = platformPrinter
}