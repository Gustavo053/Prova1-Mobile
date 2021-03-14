package com.example.minhaprova

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast

class ActivityAcao2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acao2)

        var titulo = findViewById<EditText>(R.id.titulo)
        var autor = findViewById<EditText>(R.id.autor)
        var ano = findViewById<EditText>(R.id.ano)
        var nota = findViewById<RatingBar>(R.id.nota)

        var salvar = findViewById<Button>(R.id.salvar)
        var cancelar = findViewById<Button>(R.id.cancelar)

        salvar.setOnClickListener {
            if (!(titulo.toString().isEmpty() || autor.toString().isEmpty() || ano.toString().isEmpty() || nota.toString().isEmpty())) {
                var livro = Livro(0, titulo.text.toString(), autor.text.toString(), ano.text.toString().toInt(), nota.rating)

                var db = LivroDBOpener(this)
                db.insert(livro)

                var intent = Intent(this, MainActivity::class.java)
                intent.putExtra("RESULT", "Cadastrado")

                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        cancelar.setOnClickListener {
            finish()
        }
    }
}