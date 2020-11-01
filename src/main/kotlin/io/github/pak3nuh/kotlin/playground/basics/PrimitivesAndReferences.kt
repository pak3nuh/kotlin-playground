package io.github.pak3nuh.kotlin.playground.basics

/* ------------------------------ PRIMITIVES ----------------------------------
* Primitives are the base building blocks of any language. Every data structure is build by combining the existing
* primitive values into groups that are given a meaningful name.
*
* Primitive are value types and such types are copied around, never modified.
*
* - Primitive types
* Kotlin supports the same primitive types as Java:
* Boolean -> true or false
* Char -> 16 bit Unicode character
* Byte -> 8 bit signed number
* Short -> 16 bit signed number
* Int -> 32 bit signed number
* Long -> 64 bit signed number
* Float -> 32 bit floating point signed number
* Double -> 64 bit floating point signed number
*
* In JVM world all numbers are signed, meaning 1 bit is spent as a signal (+ or -). For instance a Byte type
* can represent all values between -128 to 127.
*
* Unlike Java, all primitives are automatically boxed/unboxed based on the context. There is only one type
* representation per primitive type. A boxed value is a reference representation of an unboxed value type.
*
* - Primitive literals
* Kotlin infers types based on the context so it is important to know what literals represent
*
* 123456 -> Int
* 123456789123 -> Long because overflows Int
* 1L -> Long
* 1.2 -> Double
* 1.2f -> Float
* 'a' -> Char
*
* One can explicitly set the type in the variable or use one of the conversion methods available
* val b: Byte = 1
* val b2 = 123L.toByte()
* */

/**
 * A list of all the primitive types available in the following order
 * 1. Boolean
 * 1. Char
 * 1. Byte
 * 1. Short
 * 1. Int
 * 1. Long
 * 1. Float
 * 1. Double
 */
fun listOfAllPrimitiveTypes(): List<Any> = listOf()

/* ------------------------------ REFERENCES ----------------------------------
* Everything that isn't a primitive (value) type, is a reference type.
* A reference type, as the name implies, is a reference to something stored in a known place. A reference it's the
* objects identity, meaning if two references are equal, they both point to the same object.
*
* In kotlin, examples of reference types are classes like `String` or `ArrayList`, interfaces, enums, etc.
* Technically, boxed primitives are references, but the kotlin compiler handles the nuances for us.
*
* Much like values, references are copied from method to method and are immutable, but since they point to an object
* elsewhere in memory, they all point to the same object. This is why a method can mutate an object variable and
* the new value is seen in other methods.
* */

/**
 * A Pair that contains the same instance of the same type
 */
fun pairWithSameReference(): Pair<Any,Any> {
    // `val` means variable/field/property that can only be assigned once
    val instance = TODO()
    val sameInstance = TODO()
    return Pair(instance, sameInstance)
}

/**
 * A Pair with different instances of the same type
 */
fun pairWithDifferentReferences(): Pair<Any, Any> {
    val instance = TODO()
    val differentInstance = TODO()
    return Pair(instance, differentInstance)
}
