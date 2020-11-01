package io.github.pak3nuh.kotlin.playground.basics

/* Kotlin classes are like any other OO programming language. They serve as encapsulation units that
* may contain state (fields and properties) and behavior (functions).
*
* Classes can have some variants and modifiers
* open -> class is opened for extension. Kotlin classes are final by default
* enum -> enumerated values that are guaranteed to be a single instance of each
* abstract -> a class that cannot be instantiated (implicitly open)
* sealed -> a class hierarchy root where all inheritors are known at compile time
* inline -> a synthetic class "boxes" a single value and the compiler optimizes the use cases
* data -> a data holder that automatically generates code for equals(), hashCode(), a copy constructor and deconstructing
*
* A class can inherit from another by invoking the super class constructor when is defined.
* class Student: Human()
*
* Inheritance works like any other OO language. When a class is inherited it makes part of a "is a" relationship
* and can access or override functions and properties defined in the super class.
* */

/**
 * Make the class open
 */
open class Person

/**
 * Define an enumerated class that has values **MALE** and **FEMALE**
 */
enum class Gender {
    MALE, FEMALE
}

/**
 * Make the class abstract and associate the male gender value
 */
abstract class MalePerson: Person() {
    val gender = Gender.MALE
}

/**
 * Make the class sealed
 */
sealed class MaleWorker: MalePerson()

/**
 * Make both classes part of the [MaleWorker] sealed hierarchy
 */
class MaleTeacher: MaleWorker()
class MaleStudent: MaleWorker()

/*
* -------- Fields & properties
* For internal state, classes can have properties and backing fields. A field is the actual state storage mechanism
* and a property is how we access that field. In kotlin, fields aren't explicitly declared, they are inferred from
* the declared properties.
*
* A property is declared by specifying the mutability, name and type, which can be inferred:
* var studentName = "John Doe"
*
* There are defaults that can be overridden like visibility, explicit types and getter/setter. Next there is an example
* of the same property but with all the defaults explicitly written:
* public var studentName: String = "John Doe"
*   get() {
*       // "field" is the name of an auto-generated backing field
*       return field
*   }
*   set(value) {
*       field = value
*   }
*
* Properties can be overridden in extended classes, increase visibility and even add mutability.
* */

/**
 * Change the class to have the following properties
 * ```
 * |Mutability|Name      |Type  |Value|
 * |Immutable |make      |String|BMW  |
 * |Immutable |model     |String|M3   |
 * |Immutable |madeInYear|Int   |2020 |
 * |Mutable   |owners    |Int   |1    |
 * ```
 *
 */
class Car {
    val make = "BMW"
    val model = "M3"
    val madeInYear = 2020
    var owners = 1
}

/**
 * Change the class to have a single public property `makeAndModel` that returns the String `Ducati Monster`.
 * This property must not have the default backing field, but must obtain it's value from 2 private fields
 * named `make` and `model`.
 *
 * Hint: you will need to provide a custom getter.
 */
class Motorcycle {
    private val make = "Ducati"
    private val model = "Monster"
    val makeAndModel
        get() = "$make $model"
}

/*
* -------- Functions
* Functions are defined using the keyword `fun`, specifying the name, optional arguments and return type.
* fun sum(a: Int, b: Int): Int {
*   return a + b
* }
*
* More on function on file Functions.kt.
* */

/**
 * Change the class to contain a function named `makeAndModel` with the same output as [Motorcycle] class.
 * The function must accept parameters for model only. The make should remain a property.
 */
class FunctionalMotorcycle {
    val make = "Ducati"
    fun makeAndModel(model: String) = "$make $model"
}

/*
* -------- Constructor
* To create an instance of a class we must use a class constructor.
* In kotlin all classes have a primary constructor, which may be implicit, and optional secondary constructors.
*
* The primary constructor is defined next to the class name. Primary constructors can also contain parameters
* prefixed with val/var making them properties on the class.
* class Student(val name: String)
*
* The constructor keyword may be used if we need to add annotations or change visibility
* class Student protected @Inject constructor(val name: String)
*
* Secondary constructors are defined in the class body and must delegate to the primary
* class Student(val name: String) {
*   constructor(otherStudent: Student): this(otherStudent.name)
* }
* */

/**
 * Change the class to contain 2 properties
 * ```
 * |Mutability|Name     |Type   |
 * |Immutable |depth    |Int    |
 * |Immutable |type     |String |
 * ```
 * And two constructors, one primary with all the necessary parameters, and one secondary `protected`
 * with no parameters, that sets up defaults.
 */
open class Node(val depth: Int, val type: String) {
    protected constructor(): this(0, "leaf")
}

/**
 * Change the class to inherit from [Node] using the constructor with zero parameters and mark the primary
 * constructor with the annotation [Important].
 */
class ImportantNode @Important constructor(): Node()

/*
* -------- Overriding
* It is possible to override functions and properties on an open class.
* Unlike Java, Kotlin forces the usage of the `override` keyword making them explicit. It is impossible to override
* a member property or function by mistake.
*
* One can also increase visibility of a symbol or add mutability to an immutable property. By default the visibility
* is also inherited, so to change it one must be explicit.
* When overriding properties or functions, return types are covariant, meaning it is possible to define a more specific
* type when overridden
*
* On an `open` class, we can mark symbols as `final` so that they can't be overridden by inheritors.
* */

/**
 * Given this definition
 */
abstract class House {
    open val numberOfStories: Number = 1
    protected abstract val owner: Person
    open fun toJson(): String = """{ "type": "House", "numberOfStories": 1, "owner": "Person" }"""
}

/**
 * Change the [Duplex] class to override all symbols like
 * ```
 * |Mutability  |Name           |Type       |Visibility   |Open   |
 * |Immutable   |numberOfStories|Int        |public       |No     |
 * |Mutable     |owner          |MaleStudent|public       |Yes    |
 * |N/A         |toJson         |String     |public       |Yes    |
 * ```
 * And the `toJson` to the correct value.
 */
open class Duplex: House() {
    final override val numberOfStories: Int = 2
    public override var owner: MaleStudent = MaleStudent()
    override fun toJson(): String = """{ "type": "Duplex", "numberOfStories": 2, "owner": "MaleStudent" }"""
}


/*
* -------- Companion objects
* A class may contain a special object definition called `Companion`. This object is shared across all instances
* of the class it is defined.
*
* A companion object is something like an extension to a class without being bound to a specific instance. It can
* mimic the same behavior of static methods on Java.
*
* A companion object is defined by the keyword `companion` and can be accessed without explicitly being referenced
* like in the next example
* class Rectangle(val width: Int, val height: Int) {
*   companion {
*       fun square(size: Int): Rectangle = Rectangle(size, size)
*   }
* }
*
* val square = Rectangle.square(5)
* val otherSquare = Rectangle.Companion.square(10)
* */


