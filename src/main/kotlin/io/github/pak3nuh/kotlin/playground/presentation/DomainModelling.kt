package io.github.pak3nuh.kotlin.playground.presentation

import java.time.LocalDate

// Date guaranteed to be <= current date
inline class BirthDate private constructor(val value: LocalDate) {
    companion object {
        fun create(value: LocalDate): BirthDate? {
            return if (value.isBefore(LocalDate.now()))
                BirthDate(value)
            else
                null
        }
    }
}
// Portuguese phone number
inline class PtPhoneNumber private constructor(val value: Long) {
    companion object {
        fun create(value: Long): PtPhoneNumber? {
            return if (value in 100_000_001..999_999_999)
                PtPhoneNumber(value)
            else
                null
        }
    }
}
// String with a valid email format
inline class StringEmail private constructor(val value: String) {
    companion object {
        private val mailRegex = Regex("[a-zA-Z]*@[a-zA-Z]*\\.[a-zA-Z]*")
        fun create(value: String): StringEmail? {
            return if (mailRegex.matches(value))
                StringEmail(value)
            else
                null
        }
    }
}
// String up to 150 characters
inline class String150 private constructor(val value: String) {
    companion object {
        fun create(value: String): String150? {
            return if (value.length <= 150)
                String150(value)
            else
                null
        }
    }
}
// String up to 50 characters
inline class String50 private constructor(val value: String) {
    companion object {
        fun create(value: String): String50? {
            return if (value.length <= 50)
                String50(value)
            else
                null
        }
    }
}

// Encapsulates validity
sealed class Validatable<T>
class Valid<T>(val value: T): Validatable<T>()
class Invalid<T>(val value: T): Validatable<T>()

// Full model
data class Card (
        val firstName: String50,
        val lastName: String50,
        val birthDate: BirthDate?,
        val address: Validatable<String150>?,
        val phoneNumber: Validatable<PtPhoneNumber>,
        val email: Validatable<StringEmail>?
)