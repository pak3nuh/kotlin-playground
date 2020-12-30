package io.github.pak3nuh.kotlin.playground.basics

import java.io.PrintStream

/*-------- Nullability
* One of the main selling points of Kotlin on launch was integrating nullability on the type system.
* In Kotlin every type has up to 3 variants, non null, nullable and platform.
*
* In essence the compiler will forbid illegal usages of nullable types on non null call sites, making
* NullPointerExceptions almost impossible in Kotlin only environment. A nullable type is used by suffixing any type
* with a `?`.
*
* Since the compiler checks every access to a nullable type, we need to use the safe access expression `?.` to interact
* with them. The safe access preforms a coalesce from left to right and returns the first null or the result of the
* expression chain.
*/
fun trimCapitalized(input: String?): String? {
    return input?.trim()?.capitalize()
}
/*
* Platform types are both nullable and non null and can't be typed explicitly, they are used when Java interop comes
* into play. Platform types aren't subject to the compiler checks and should be converted to Kotlin types as soon
* as possible.
*/
fun systemConsole(): PrintStream {
    val consolePlatform = System.out // Platform type
    // Here the compiler asserts the console is not null and after that it never needs to
    val consoleKotlin: PrintStream = consolePlatform
    return consoleKotlin
}
fun kPrint(value: Any?) {
    // no null checks emitted by the compiler because it is ensured by the type system
    systemConsole().println(value)
}

/*
* To avoid abusing `if` expressions to convert nullable values in non null Kotlin has the elvis operator `?:`.
* It returns the left side of the expression if non null, else returns the right side.
* A throw expression is also a valid right side expression.
* */
fun stringOrEmpty(input: String?): String {
    return input ?: ""
}
fun stringOrThrow(input: String?): String {
    return input ?: throw IllegalArgumentException()
}