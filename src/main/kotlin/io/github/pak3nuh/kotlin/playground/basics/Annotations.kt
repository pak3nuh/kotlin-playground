package io.github.pak3nuh.kotlin.playground.basics

import kotlin.reflect.KClass

/*
* -------- Annotations
* These work pretty much like any other form of metadata augmentation in any other language. Compared to Java, Kotlin
* annotations are very similar, albeit providing more options mainly because Kotlin is a more complex language.
*
* An annotation is a special kind of class and is defined by the `annotation` modifier. An annotation class can
* only define constant members, because it can be compiled into bytecode, therefore any value that would be
* computed at compile time is forbidden.
* */
annotation class Example(val name: String, val isActive: Boolean, val classes: Array<KClass<*>>)
/*
* Unlike Java, annotations are concrete classes and not interfaces that can be implemented. Of course that, under
* the hood, these are compiled into `@interface` types the JVM understands, so it is possible to implement a custom
* Kotlin annotation with a concrete class in Java. See [this](./CustomExampleImplementation.java) for reference.
*
* -------- Meta annotations
* Meta annotations are annotations about annotations and they are fairly used.
* The most common meta annotations are:
* - Retention: If the annotation should be erased at compile time
* - Target: Where the annotation can be applied
* - Repeatable: If the annotation can be applied multiple times on the same target
* */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CONSTRUCTOR)
annotation class Important
/*
* -------- kotlin.Metadata
* Now to the bare metal of the annotation system in Kotlin.
*
* As you must have already concluded, Kotlin runs code on platforms that do not provided every feature it needs
* at the runtime level.
* For instance, `infix` functions are syntactic sugar for a static function with two arguments, but there is no
* way of storing this information into the signature at the bytecode level (on JVM runtimes) so that it can be
* extracted later.
*
* Kotlin developed an ingenious solution for this, the `kotlin.Metatada` annotation. This special annotation is
* present in every Kotlin type and provides introspection capabilities into every aspect of the type from a language
* perspective.
* The compiler itself uses this annotation to provide additional information about what is present in the bytecode
* without having to hack its way into the runtime itself.
* Additional uses for this annotation are `kotlin.reflect` module and annotation processing.
*
* If you are interested in this, check out this [repository](https://github.com/JetBrains/kotlin/tree/master/libraries/kotlinx-metadata/jvm)
* for the parsing library and this other [repository](https://github.com/pak3nuh/hollywood)
* for a usage on a code generator for coroutines.
*
* -------- Annotation processors
* As you know, the JVM provides annotation processing capabilities that allow us to intercept compilation steps
* and implement components that range from code assertions to code generation.
*
* This tutorial does not aim to teach how to use annotation processing in the JVM but how to enable Kotlin annotation
* processing.
*
* The first thing to know is that it has a special name `kapt`. To use it we need to tell the compiler to enable it.
* The concrete way to do it depends on the build system used and it is out of scope. Check
* [this](https://kotlinlang.org/docs/reference/kapt.html) for reference.
* Kotlin and Java compilation are actually separate processed that have to be compiled separately. If you need to
* interop with both in the same project check [this](https://discuss.kotlinlang.org/t/compiling-mixed-java-and-kotlin-files-on-the-command-line/1553)
*
* What we need to know is that, unlike Java annotation processing that is round based, meaning we have more rounds if
* we generate code during compilation, Kotlin only provides on single round so we can't generate code recursively
* using Kotlin annotation processing.
*
* Nevertheless we can generate code with the first round we have available and the folder to dump the generated
* code is in the property `processingEnv.options["kapt.kotlin.generated"]`. This property is setup by the
* build system of your choice.
* */
