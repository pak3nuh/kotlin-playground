package io.github.pak3nuh.kotlin.playground.basics

import assertk.all
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.index
import assertk.assertions.isInstanceOf
import org.junit.jupiter.api.Test

internal class PrimitivesAndReferencesKtTest {
    @Test
    fun `should get primitives in order`() {
        val listOfAllPrimitiveTypes = listOfAllPrimitiveTypes()
        assertThat(listOfAllPrimitiveTypes).all {
            hasSize(8)
            index(0).isInstanceOf(Boolean::class)
            index(1).isInstanceOf(Char::class)
            index(2).isInstanceOf(Byte::class)
            index(3).isInstanceOf(Short::class)
            index(4).isInstanceOf(Int::class)
            index(5).isInstanceOf(Long::class)
            index(6).isInstanceOf(Float::class)
            index(7).isInstanceOf(Double::class)
        }
    }
}
