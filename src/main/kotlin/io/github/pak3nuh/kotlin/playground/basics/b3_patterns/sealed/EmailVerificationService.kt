package io.github.pak3nuh.kotlin.playground.basics.b3_patterns.sealed

/**
 * Verified case of [Email] only possible to be created by [Service]
 */
class VerifiedEmail private constructor(email: String) : Email(email) {

    class Service {
        private fun isValid(email: String): Boolean = true
        fun verify(email: String): VerifiedEmail? =
                if (isValid(email))
                    VerifiedEmail(email)
                else
                    null
    }

}
