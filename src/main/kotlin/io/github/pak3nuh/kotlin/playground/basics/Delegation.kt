package io.github.pak3nuh.kotlin.playground.basics

import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/*
type delegation
property delegation
 */

/* Delegation is a software design pattern where we pass method calls through an intermediate before dispatching it
* to the real target. It is tha basis for patterns like Interceptor, Proxy, Adapter and more.
*
* For this patter to work properly, both the delegate and the target must have a common ancestor like an interface or
* a super class.
*
* This pattern is so useful that in Kotlin it is handled at a language level. We can use delegation using the keyword
* `by` and it works with several constructs.
*
* -------- Type delegation
* We can implement type delegation by using the keyword when we define the ancestors of the class we are implementing.
* Since the delegation needs a concrete object to preform delegation, we can only use it on classes and not on interfaces.
*
* When this type of delegation is used, the compiler will issue every method that we do not explicitly override, so the
* class will have bytecode for every method on the ancestor, but we didn't had to implement it by hand.
* */

/**
 * Wrapper that logs every access to a specific key on the [actualMap]
 */
class LoggingMap<K, V>(private val actualMap: Map<K, V>) : Map<K, V> by actualMap {
    override fun get(key: K): V? {
        println("Accessing key $key")
        return actualMap[key]
    }
}

/*-------- Property delegation
* This type of delegation is a bit more complex, but at its core it is the same principle.
*
* Whenever we add the `by` to a property, we need to specify how that property should be accessed and we have two
* "levels" of property access:
*
* 1. Property delegates
* Any type that has some form of the `getValue` operator, either by explicitly overriding the operator or by providing
* an equivalent signature, is a valid delegate for read only properties. A delegate for writable properties must
* also implement the `setValue` operator.
*
* */

/**
 * A VERY naive implementation of the Memoization pattern for costly resources.
 *
 * It extends the [ReadOnlyProperty] interface as a compiler helper to have the correct signatures for a delegation
 * compliant class.
 *
 * @see [ReadWriteProperty] for writable signatures as well.
 * @see [lazy] for the actual implementation
 */
class NaiveLazy<T, V>(private val computation: () -> V) : ReadOnlyProperty<T, V> {
    var value: V? = null
    override fun getValue(thisRef: T, property: KProperty<*>): V {
        return value
                ?: computation.run {
                    println("${System.currentTimeMillis()}: Computing expensive value")
                    val computed = this()
                    println("${System.currentTimeMillis()}: Storing computed value")
                    value = computed
                    computed
                }
    }
}

/* 2. Property delegate providers
* Since property delegates are invoked every time the property is called, we can define a provider for such delegate
* that is called on once, when the property is first invoked.
*
* Again we just need to match the signature of the operator `provideDelegate` for it to work.
* */

/**
 * A provider for [NaiveLazy] delegates that logs when its created.
 *
 * It extends the [PropertyDelegateProvider] interface as a compiler helper to have the correct signatures for a delegation
 * compliant class.
 */
class LoggableLazy<T, V>(private val computation: () -> V): PropertyDelegateProvider<T, NaiveLazy<T, V>> {
    override fun provideDelegate(thisRef: T, property: KProperty<*>): NaiveLazy<T, V> {
        println("${System.currentTimeMillis()}: Creating lazy property")
        return NaiveLazy(computation)
    }
}


/**
 * Example class for property delegation features
 */
class ExpensiveClient {
    val expensiveValue: Long by LoggableLazy { createExpensiveValue() }
    private fun createExpensiveValue(): Long {
        Thread.sleep(1000)
        return 123456789
    }
}

fun main() {
    val client = ExpensiveClient()
    println("Value is ${client.expensiveValue}")
    println("Memoized value ${client.expensiveValue}")
}
