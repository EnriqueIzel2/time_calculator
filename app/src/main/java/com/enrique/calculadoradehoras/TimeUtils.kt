package com.enrique.calculadoradehoras

class TimeUtils {

  fun horarioDeSaida(horarioEntrada: Int, cargaHoraria: Int): Int {

    return horarioEntrada + cargaHoraria
  }

  fun horasParaMinutos(horas: Int): Int{
    return horas * 60
  }

  fun minutosParaHoras(minutos: Int): Double{
    return 1.2
  }
}