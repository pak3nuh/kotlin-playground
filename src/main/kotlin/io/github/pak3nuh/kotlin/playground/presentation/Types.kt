package io.github.pak3nuh.kotlin.playground.presentation

import java.time.LocalDate

interface Named {
    val name: String
    fun present() = "Hello, my name is $name"
}

sealed class Person(override val name: String, val age: Int): Named {
    override fun present() = "Hello, my name is $name and I'm $age"
    
    class Worker(name: String, age: Int, val jobData: JobData): Person(name, age) {
        final override fun present() =
        "Hello, my name is $name, I'm $age and my work is $jobData"
    }
}

data class JobData(val started: LocalDate, val job: Job) {
    override fun toString() = "$job started at $started"
}


enum class Job { Teacher }