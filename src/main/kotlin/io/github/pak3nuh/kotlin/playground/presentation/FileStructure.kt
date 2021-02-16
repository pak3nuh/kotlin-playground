package io.github.pak3nuh.kotlin.playground.presentation

fun interface Functor<T, Y> {
    fun mapTo(input: T): Y
}

class Stringifier : Functor<Any?, String> {
    override fun mapTo(input: Any?): String {
        return input?.toString() ?: ""
    }
}

val stringifier = Stringifier()
fun toString(input: Any?) = stringifier.mapTo(input)