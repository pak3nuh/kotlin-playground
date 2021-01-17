package io.github.pak3nuh.kotlin.playground.basics.b2_language

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/*
* In computer science, pattern matching is a technique in which assumptions can be inferred once certain patterns
* are identified.
*
* In programming language terms, this means that the compiler can infer some properties on the code if certain
* conditions are met.
*
* Kotlin supports pattern matching in a lot of places, and this translates to higher developer productivity.
*
* -------- Smart cast
* For immutable values, Kotlin does an automatic cast when make an instanceof if check. This holds true for values
* that Kotlin knows are immutable. If we try to apply pattern matching to an interface `val` property, it won't work
* because the implementation may be mutable.
*/
fun printTrimmed(any: Any) {
    if (any is String) {
        print(any.trim()) // smart cast to String
    }
    print(any)
}
/*
* The same type of assumptions can be made when we chain boolean operations with && operator.
* */
class Holder<T>(val value: T) {
    override fun equals(other: Any?): Boolean {
        return other is Holder<*> && other.value == value
    }
}
/*
* Nullability works the same way, since the nullable type is different from the non nullable version.
* If, during the flow of the program, we assure the value is non null, then the compiler infers the value is
* non nullable after that point and we need not to use null safe operators.
* */
fun printAndSumPair(pair: Pair<Int, Int>?): Int {
    print(pair!!.toString()) // we assert the value can't be null here
    return pair.first + pair.second
}

/*
* -------- Contracts API
* Sometimes we want to express a function, that we know will make some assertion about it's values. It would be very
* helpful if the compiler itself would be aware of this.
* The Contracts API is a way for us as developers to tell the compiler additional metadata about some piece of code.
*
* For instance, if we analyze the `require` function in the standard library, we can see that it specifies a contract
* that tells the compiler that the input condition is true when the function returns.
*
* Let's see an actual example:
* */
@ExperimentalContracts
fun myRequire(condition: Boolean) {
    contract { returns() implies condition }
    if(!condition) throw IllegalArgumentException()
}
interface Message
data class MessageImpl(val data: String): Message
@ExperimentalContracts
fun processMessage(message: Message) {
    myRequire(message is MessageImpl)
    print(message.data)
}
/*
* As you noticed, `processMessage` only allows messages of a certain implementation and the compiler can use
* the assertion on the rest of the code.
*
* Currently the Contracts API is in experimental status, but it is quite stable and is unlikely to be removed without,
* any replacement, but use at your own risk. See https://kotlinlang.org/docs/reference/evolution/components-stability.html
* for the stability of each Kotlin component.
* */
