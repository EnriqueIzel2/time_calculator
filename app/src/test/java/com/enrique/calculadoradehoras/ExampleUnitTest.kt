package com.enrique.calculadoradehoras

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
  @Test
  fun retorna_horario_saida() {
    // given
    val h_entrada = 8.00
    val tempo_trabalho = 9.55
    val objectUnderTest = TimeUtils()

    // when
    val result = objectUnderTest.horarioDeSaida(h_entrada, tempo_trabalho)

    // then
    assertEquals(17.55, result, 0.4)
  }
}