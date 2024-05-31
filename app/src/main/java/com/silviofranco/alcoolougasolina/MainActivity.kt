package com.silviofranco.alcoolougasolina

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var textAlcool: TextInputLayout
    private lateinit var editAlcool: TextInputEditText

    private lateinit var textGasolina: TextInputLayout
    private lateinit var editGasolina: TextInputEditText

    private lateinit var btnCalcular: Button
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        inicializarComponentesInterface()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        btnCalcular.setOnClickListener {
            calcularMelhorPreco()
        }
    }


    private fun calcularMelhorPreco() {
        val precoAlcool = editAlcool.text.toString()
        val precoGasolina = editGasolina.text.toString()
        
        val resultadoValidacao = validarCampos(precoAlcool, precoGasolina)
        if (resultadoValidacao){
            val resultado = precoAlcool.toDouble() / precoGasolina.toDouble()
            if (resultado >= 0.7) {
                textResultado.text = "Melhor utilizar Gasolina!"
            } else {
                textResultado.text = "Melhor utilizar Álcool!"
            }
        }
    }

    private fun validarCampos(pAlcool: String, pGasolina: String): Boolean {
        textResultado.error = null

        if (pAlcool.isEmpty() || pGasolina.isEmpty()) {
            textResultado.error = "Preencha os preços primeiro!"
            return false
        }
        return true
    }

    private fun inicializarComponentesInterface() {
        textAlcool = findViewById(R.id.text_alcool)
        editAlcool = findViewById(R.id.edit_alcool)

        textGasolina = findViewById(R.id.text_gasolina)
        editGasolina = findViewById(R.id.edit_gasolina)

        btnCalcular = findViewById(R.id.btn_calcular)
        textResultado = findViewById(R.id.text_resultado)
    }
}