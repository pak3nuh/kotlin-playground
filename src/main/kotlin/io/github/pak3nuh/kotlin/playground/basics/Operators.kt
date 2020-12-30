package io.github.pak3nuh.kotlin.playground.basics

/*-------- Operators
* In Kotlin operators are just functions that have a special signature.
*
* We can define or redefine an operator by defining a function with the correct signature. The `operator` keyword
* is optional, but if defined the compiler with check the signature for us.
* The full list of operators is here https://kotlinlang.org/docs/reference/keyword-reference.html
* */

class CarLot {
    private val carList = ArrayList<Car>()

    operator fun plus(car: Car) {
        carList.add(car)
    }

    operator fun invoke(action: (Car) -> Unit) {
        carList.forEach(action)
    }
}

fun addCarToLotAndPrint(lot: CarLot, car: Car) {
    lot + car
    lot {
        println(it)
    }
}