package io.github.pak3nuh.kotlin.playground.presentation

class Rectangle(val x: Int, val y: Int) {
    companion object {
        fun square(side: Int): Rectangle {
            return Rectangle(side, side)
        }
    }
}

fun buildSquare(side: Int): Rectangle {
    return Rectangle.square(side)
}

object ObjectDeclaration {
    const val NAME = "Name"
}

val objectExpression = object : Named {
    override val name: String
    get() = ObjectDeclaration.NAME
}