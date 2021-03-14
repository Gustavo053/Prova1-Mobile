package com.example.minhaprova

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ActivityAcao1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acao1)

        var intent = Intent(this, MainActivity::class.java)

        var textViewAcao1 = findViewById<EditText>(R.id.editTextAction1)
        var buttonOk = findViewById<Button>(R.id.buttonOk)
        var buttonCancelar = findViewById<Button>(R.id.buttonCancelar)

        buttonOk.setOnClickListener {
            intent.putExtra("RESULT", textViewAcao1.text.toString())

            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        buttonCancelar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}