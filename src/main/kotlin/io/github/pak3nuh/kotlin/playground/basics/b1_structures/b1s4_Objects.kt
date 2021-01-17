package io.github.pak3nuh.kotlin.playground.basics.b1_structures

/*
* -------- Objects
* In kotlin objects is a keyword that defines a new type with special semantics. There are three kinds of objects.
* 1. Object definitions
* 2. Companion objects
* 3. Object expressions
*
* All object kinds can implement interfaces and be accessed anywhere the scope they are defined allows.
*
* -------- Object definition
* An object definition creates both a new type and the only possible instance of that type. It is mostly the
* singleton pattern implemented at the language level.
*/
interface Calculator {
    fun add(x1: Int, x2: Int): Int
}
private object SingletonCalculator : Calculator {
    override fun add(x1: Int, x2: Int): Int = x1 + x2
}

/*
* -------- Companion objects
* A class may contain a special object definition called `Companion`. This object is shared across all instances
* of the class it is defined.
*
* A companion object is something like an extension to a class without being bound to a specific instance. It can
* mimic the same behavior of static methods on Java.
*
* A companion object is defined by the keyword `companion object {name}` and can be accessed without explicitly being referenced
* like in the next example. If the object name is omitted, it will be called `Companion`.
* */
class Rectangle(val width: Int, val height: Int) {
    override fun toString(): String = printRectangle(this)

    companion object {
        fun square(size: Int): Rectangle = Rectangle(size, size)

        fun printRectangle(rectangle: Rectangle): String {
            val builder = StringBuilder()
            val line = ".".repeat(rectangle.width) + System.lineSeparator()
            for (lineIdx in 0..rectangle.height) {
                builder.append(line)
            }
            return builder.toString()
        }
    }
}

val square = Rectangle.square(5)
val otherSquare = Rectangle.Companion.square(10)

/*
* -------- Object expressions
* The last object type we have in kotlin is an expression. It maps to anonymous types in Java. This is the only
* type of object that we can have multiple instances.
*
* They are defined in the same place they are used whenever a an expression is possible.
*/
val objectExpression = object {
    val PI = 3.14
}
// not accessible because this is an anonymous type that extends only `Any`
// val pi = objectExpression.PI

/**
 * Implement a function that returns [Calculator] instances through object expressions.
 */
fun calculatorFactory(): Calculator = object : Calculator {
    override fun add(x1: Int, x2: Int): Int = x1 + x2
}
