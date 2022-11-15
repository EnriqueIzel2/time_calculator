package com.enrique.calculadoradehoras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.enrique.calculadoradehoras.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnCalculaHoras.setOnClickListener {
      val horasEntrada = binding.editHorasEntrada.text.toString()
      val minutosEntrada = binding.editMinutosEntrada.text.toString()
      val horasTrabalhadas = binding.editHorasTrabalhadas.text.toString()
      val minutosTrabalhados = binding.editMinutosTrabalhados.text.toString()

      if (
        horasEntrada.isNotEmpty() &&
        minutosEntrada.isNotEmpty() &&
        horasTrabalhadas.isNotEmpty() &&
        minutosTrabalhados.isNotEmpty()
      ) {
        if (isValidTime(
            horasEntrada.toInt(),
            minutosEntrada.toInt(),
            horasTrabalhadas.toInt(),
            minutosTrabalhados.toInt()
          )
        ) {
          val horarioSaida =
            timeConversor(
              horasEntrada.toInt(),
              minutosEntrada.toInt(),
              horasTrabalhadas.toInt(),
              minutosTrabalhados.toInt()
            )
          val message = "Horário de saída ${horarioSaida[0]}:${horarioSaida[1]}"
          limpaCampos()
          binding.txtResultado.setText(message)
        } else {
          Toast.makeText(this, "Tipo de horário errado, tente novamente", Toast.LENGTH_SHORT).show()
        }
      } else {
        Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT)
          .show()
      }
    }
  }

  private fun timeConversor(
    horas: Int,
    minutos: Int,
    hTrabalhadas: Int,
    mTrabalhadas: Int
  ): List<Int> {
    val totalMinutosParaTrabalhar = hTrabalhadas * 60 + mTrabalhadas
    val totalMinutos = horas * 60 + minutos + totalMinutosParaTrabalhar
    val horaSaida = totalMinutos / 60
    val minutoSaida = totalMinutos % 60

    return listOf(horaSaida, minutoSaida)
  }

  private fun isValidTime(
    hEntrada: Int,
    mEntrada: Int,
    hTrabalhadas: Int,
    mTrabalhados: Int
  ): Boolean {
    if (
      hEntrada >= 0 && hEntrada <= 24 &&
      mEntrada >= 0 && mEntrada <= 60 &&
      hTrabalhadas >= 0 && hTrabalhadas <= 24 &&
      mTrabalhados >= 0 && mTrabalhados <= 60
    ) {
      return true
    }
    return false
  }

  private fun limpaCampos() {
    with(binding) {
      editHorasEntrada.setText("")
      editMinutosEntrada.setText("")
      editHorasTrabalhadas.setText("")
      editMinutosTrabalhados.setText("")
    }
  }
}