package io.github.pak3nuh.kotlin.playground.presentation

import java.util.function.Consumer

fun interface KConsumer<T> {
    fun accept(input: T)
}

fun consume(consumer: KConsumer<String>) {
    consumer.accept("Hello world")
}

fun consume(consumer: java.util.function.Consumer<String>) {
    consumer.accept("Hello world")
}

fun tryout() {
    consume(Consumer { print(it) })
    consume(KConsumer { print(it) })
}