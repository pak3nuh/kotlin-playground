package io.github.pak3nuh.kotlin.playground.basics.b3_patterns.sealed

import arrow.core.Either
import arrow.core.left
import arrow.core.right

/**
 * Sealed class with 2 possible states: verified and unverified.
 *
 * Anyone can create the unverified state, but the verified one is only possible to be created
 * by a service call on [VerifiedEmail.Service]
 *
 * This modelling makes it impossible to create invalid states
 */
sealed class Email(val email: String)
class UnverifiedEmail(email: String): Email(email)

/**
 * Users need to provide behavior for both cases
 */
fun passwordReset(email: Email): Either<Unit, IllegalArgumentException> {
    return when(email) {
        is UnverifiedEmail -> IllegalArgumentException("Unverified email").right()
        is VerifiedEmail -> sendVerificationEmail(email).left()
    }
}

fun sendVerificationEmail(email: VerifiedEmail)  {
    // some sending logic
}
