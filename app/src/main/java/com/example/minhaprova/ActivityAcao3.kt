package com.example.minhaprova

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.delay

class ActivityAcao3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acao3)

        var db = LivroDBOpener(this)

        var id = 1;
        var countLivros = db.findAll().size

        var titulo = findViewById<TextView>(R.id.tituloResult)
        var autor = findViewById<TextView>(R.id.autorResult)
        var ano = findViewById<TextView>(R.id.anoResult)
        var nota = findViewById<TextView>(R.id.notaResult)

        var proximo = findViewById<Button>(R.id.proximo)
        var anterior = findViewById<Button>(R.id.anterior)

        if (countLivros >= 1) {
            var registro = db.findById(id)

            titulo.setText(registro.nome)
            autor.setText(registro.autor)
            ano.setText(registro.ano.toString())
            nota.setText(registro.nota.toString())

            visibilityButton(id)

            proximo.setOnClickListener {
                var registroProximo = db.findById(++id)

                titulo.setText(registroProximo.nome)
                autor.setText(registroProximo.autor)
                ano.setText(registroProximo.ano.toString())
                nota.setText(registroProximo.nota.toString())

                visibilityButton(++id)
            }

            anterior.setOnClickListener {
                var registroAnterior = db.findById(--id)

                titulo.setText(registroAnterior.nome)
                autor.setText(registroAnterior.autor)
                ano.setText(registroAnterior.ano.toString())
                nota.setText(registroAnterior.nota.toString())

                visibilityButton(--id)
            }
        } else {
            Toast.makeText(this, "Não há registros de livros", Toast.LENGTH_SHORT)
            finish()
        }
    }

    fun visibilityButton(id: Int) {
        var proximo = findViewById<Button>(R.id.proximo)
        var anterior = findViewById<Button>(R.id.anterior)

        var db = LivroDBOpener(this)
        var countLivros = db.findAll().size

        if (id < 1) {
            anterior.visibility = View.INVISIBLE
            proximo.visibility = View.INVISIBLE
        } else if (id == 1 && db.findAll().size > 1) {
            anterior.visibility = View.INVISIBLE
            proximo.visibility = View.VISIBLE
        }else if (id == 1 && db.findAll().size == 1){
        } else if (id == countLivros) {
            anterior.visibility = View.VISIBLE
            proximo.visibility = View.INVISIBLE
        } else {
            anterior.visibility = View.VISIBLE
            proximo.visibility = View.VISIBLE
        }
    }
}