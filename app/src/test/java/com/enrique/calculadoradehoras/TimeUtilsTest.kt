package com.enrique.calculadoradehoras

import org.junit.Assert.*
import org.junit.Test

class TimeUtilsTest{
  @Test
  fun retorna_16h55_horario_de_saida_com_jornada_iniciada_as_7(){
    // Given
    val funcaoEmTeste = TimeUtils()
    val h_entrada = 8.00
    val jornada = 9.55

    // When
    val result = funcaoEmTeste.horarioDeSaida(h_entrada, jornada)

    // Then
    assertEquals(17.55, result, 0.1)
  }

  @Test
  fun retorna_17h55_horario_de_saida_com_jornada_iniciada_as_8(){
    // Given
    val funcaoEmTeste = TimeUtils()
    val h_entrada = 480
    val jornada = 595

    // When
    val result = funcaoEmTeste.horarioDeSaida(h_entrada, jornada)

    // Then
    assertEquals(17.55, result)
  }

  @Test
  fun retorna_18h55_horario_de_saida_com_jornada_iniciada_as_9(){
    // Given
    val funcaoEmTeste = TimeUtils()
    val h_entrada = 9.00
    val jornada = 9.55

    // When
    val result = funcaoEmTeste.horarioDeSaida(h_entrada, jornada)

    // Then
    assertEquals(18.55, result)
  }
}