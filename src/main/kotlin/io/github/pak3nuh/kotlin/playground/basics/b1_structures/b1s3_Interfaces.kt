package io.github.pak3nuh.kotlin.playground.basics.b1_structures

import java.util.function.Supplier


/*
*-------- Interfaces
* An interface in Kotlin is exactly the same as in Java, it is the most decoupled unit of code we can specify.
*
* A Kotlin interface can define properties (not fields) and functions, abstract or default, inherit other interfaces,
* much like a Java interface.
*/
interface Phone {
    val phoneNumber: Long
    fun call(number: Long)
    fun callSelf() {
        call(phoneNumber)
    }
}

/*
* In Kotlin we must explicitly implement an interface except with one exception, an interface with a single abstract
* method (known as FunctionalInterface in Java).
*
* For compatibility reasons, if the interface is defined in Java, the SAM conversion is applied automatically, otherwise
* the interface must be explicitly marked as `fun`.
*
* For SAM conversions we need to prefix the expression with tha name of the interface
 */
val javaSupplier: Supplier<Int> = Supplier { 1 }

fun interface KSupplier<T> {
    fun get(): T
}
val kotlinSupplier: KSupplier<Int> = KSupplier { 1 }

/*
* In the end, SAM conversions work in a very similar to object expressions, but with a much more nicer syntax.
 */

/**
 * Implementation classes [TriangleImpl] and [CircleImpl] can't change their public API. You are only allowed to change
 * modifiers or implement non public code.
 *
 * Change the interfaces so that each property and function is defined in their respective places. It should be
 * possible to create new implementations just by respecting the interface hierarchy.
 */
interface Shape {
    fun area(): Double
}
interface Triangle: Shape {
    val base: Int
    val height: Int
    override fun area(): Double = (base * height).toDouble() / 2
}
interface Circle: Shape {
    val radius: Int
    override fun area(): Double = Math.PI * radius * radius
    fun perimeter(): Double
}

class TriangleImpl(override val base: Int, override val height: Int): Triangle
class CircleImpl(override val radius: Int) : Circle {
    override fun perimeter(): Double = 2 * Math.PI * radius
}
