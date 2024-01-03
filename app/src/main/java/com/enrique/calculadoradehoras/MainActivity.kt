package com.enrique.calculadoradehoras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import com.enrique.calculadoradehoras.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    val horasEntrada = binding.editHorasEntrada
    val minutosEntrada = binding.editMinutosEntrada
    val horasTrabalhadas = binding.editHorasTrabalhadas
    val minutosTrabalhados = binding.editMinutosTrabalhados

    handleEditTexts(horasEntrada, minutosEntrada, horasTrabalhadas)

    binding.btnCalculaHoras.setOnClickListener {

      if (isAllFieldsNotEmpty()) {
        if (isValidTime()) {
          val horarioSaida = timeConverse(
            horasEntrada.text.toString().toInt(),
            minutosEntrada.text.toString().toInt(),
            horasTrabalhadas.text.toString().toInt(),
            minutosTrabalhados.text.toString().toInt()
          )

          val messageResult = getString(
            R.string.result_message_time,
            horarioSaida[0].toString(),
            horarioSaida[1].toString()
          )

          cleanFieldAndTakeFocusForFirstField()
          binding.txtResultado.text = messageResult
        } else {
          Toast.makeText(this, getString(R.string.error_message_invalid_format), Toast.LENGTH_SHORT)
            .show()
        }
      } else {
        Toast.makeText(
          this,
          getString(R.string.error_message_fileds_must_be_filled_In), Toast.LENGTH_SHORT
        ).show()
      }
    }
  }

  private fun handleEditTexts(
    horasEntrada: EditText,
    minutosEntrada: EditText,
    horasTrabalhadas: EditText
  ) {
    horasEntrada.setOnEditorActionListener { _, i, _ ->
      if (i == EditorInfo.IME_ACTION_GO) {
        minutosEntrada.requestFocus()
      }
      false
    }

    minutosEntrada.setOnEditorActionListener { _, i, _ ->
      if (i == EditorInfo.IME_ACTION_GO) {
        horasTrabalhadas.requestFocus()
      }
      false
    }
  }

  private fun timeConverse(
    horas: Int, minutos: Int, hTrabalhadas: Int, mTrabalhadas: Int
  ): List<Int> {
    val totalMinutosParaTrabalhar = hTrabalhadas * 60 + mTrabalhadas
    val totalMinutos = horas * 60 + minutos + totalMinutosParaTrabalhar
    val horaSaida = totalMinutos / 60
    val minutoSaida = totalMinutos % 60

    return listOf(horaSaida, minutoSaida)
  }

  private fun isAllFieldsNotEmpty(): Boolean {
    val hEntrada = binding.editHorasEntrada.text.toString()
    val mEntrada = binding.editMinutosEntrada.text.toString()
    val hTrabalhadas = binding.editHorasTrabalhadas.text.toString()
    val mTrabalhados = binding.editMinutosTrabalhados.text.toString()

    return hEntrada.isNotEmpty() &&
            mEntrada.isNotEmpty() &&
            hTrabalhadas.isNotEmpty() &&
            mTrabalhados.isNotEmpty()
  }

  private fun isValidTime(): Boolean {
    val hEntrada = binding.editHorasEntrada.text.toString().toInt()
    val mEntrada = binding.editMinutosEntrada.text.toString().toInt()
    val hTrabalhadas = binding.editHorasTrabalhadas.text.toString().toInt()
    val mTrabalhados = binding.editMinutosTrabalhados.text.toString().toInt()

    return hEntrada in 0..24 &&
            mEntrada in 0..60 &&
            hTrabalhadas in 0..24 &&
            mTrabalhados in 0..60
  }

  private fun cleanFieldAndTakeFocusForFirstField() {
    with(binding) {
      editHorasEntrada.setText("")
      editMinutosEntrada.setText("")
      editHorasTrabalhadas.setText("")
      editMinutosTrabalhados.setText("")
      editHorasEntrada.requestFocus()
    }
  }
}