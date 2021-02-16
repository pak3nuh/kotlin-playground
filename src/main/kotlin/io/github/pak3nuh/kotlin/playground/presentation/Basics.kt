package io.github.pak3nuh.kotlin.playground.presentation

val name: String = "John Wick"
private var age = 35

fun sayHello(name: String, age: Int) =
"Hello, my name is $name and I'm $age"

val johnWickPresentation: () -> String =  { sayHello(name, age) }

fun presentJohnWick() {
    println(johnWickPresentation())
}

lateinit var toBeUpdated: String