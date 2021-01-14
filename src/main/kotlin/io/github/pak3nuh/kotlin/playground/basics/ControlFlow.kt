package io.github.pak3nuh.kotlin.playground.basics

/*
* -------- Control flow
*
* As any other language Kotlin as it's base control flow operation that change normal execution of the code.
* Different from most languages, some of those constructs are actually expressions. This improves readability
* when we have multiple branches of execution.
*
* The result of the expression is the last line of the flow branch, similar to lambda expressions.
*/
fun min(left: Long, right: Long): Long {
    return if(left < right) {
        left
    } else {
        right
    }
}
fun minAsExpression(left: Long, right: Long): Long = if(left < right) left else right
/*
* Besides `if` expressions Kotlin provides another expression the handles more than two branches, the `when` expression.
*
* It behaves, in principle, like a chain of `if/else` expressions, every branch being evaluated one at the time. On
* some cases, when the compiler can extract a constant value from the branches, it behaves like Java's `switch`,
* jumping to the exact branch without evaluating others.
*
* When expressions are used by providing multiple pairs of (boolean expression) -> (value) wrapped
* by the `when { }` statement.
*
* When expressions also allow the compiler to exhaust all the possibilities issuing warnings, or even errors, if they
* are incomplete.
*/
fun getFirst(collection: Any): Any? {
    return when (collection) {
        is List<*> -> collection[0]
        is Array<*> -> collection[0]
        is Map<*,*> -> collection.entries.first()
        is Collection<*> -> collection.iterator().next()
        else -> null
    }
}
/*
* -------- Loops
*
* Kotlin provides 2 kinds of loops, finite or expression bound.
*
* The only finite type is a `for` loop, and it works with any kind iterable. The syntax is `for (X in iterator) {}`
* Remember that the `in` an operator the invokes the `iterator()` function.
*
* When working with ranges, there are lots of utility functions that helps improve readability like `downTo`, `step`
* or `reversed`.
*/
fun countDownStepped(from: Int) {
    for (i in (0 .. from).reversed() step 2) {
        println(i)
    }
}
/*
* The other kind of loops is `while {}` and `do {} while` loops. They are essentially the same, that loop indefinitely until
* some condition is met. The only difference between them is that `while` check for the condition at the beginning and
* `do` checks at the end.
*
* In either one of those `break` exits the loop and `continue` jumps to the beginning of the loop.
*/
fun sumMatrix(matrix: Array<IntArray>): Int {
    var counter = 0
    var line = 0
    var column = 0
    while (line <= matrix.size) {
        do {
            counter += matrix[line][column]
            column++
        } while (column < matrix[line].size)
        column = 0
        line++
    }
    return counter
}
