package com.example.minhaprova

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: VModelMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(VModelMain::class.java)

        var settings = getSharedPreferences("PREFS", Context.MODE_PRIVATE)

        //Configuração da mensagem de boas vindas
        var welmcome = settings.getBoolean("welcome", true)

        //verificação se é a primeira vez que o usuário está entrnado no aplicativo
        if (welmcome) {
            Toast.makeText(this, "BEM-VINDO!", Toast.LENGTH_LONG).show()
            var putSettings = settings.edit()
            putSettings.putBoolean("welcome", false)
            putSettings.apply()
        }

        var textView1 = findViewById<TextView>(R.id.textView1)
        var textView2 = findViewById<TextView>(R.id.textView2)
        var button1 = findViewById<Button>(R.id.button1)
        var button2 = findViewById<Button>(R.id.button2)
        var button3 = findViewById<Button>(R.id.button3)
        var button4 = findViewById<Button>(R.id.button4)

        textView1.setText(viewModel._textView1)
        textView2.setText(viewModel._textView2)

        button1.setOnClickListener {
            var intent = Intent(this, ActivityAcao1::class.java)

            startActivityForResult(intent, 1)
        }

        button2.setOnClickListener {
            var dialog = Cafe()
            dialog.show(supportFragmentManager, "Dialog")
        }

        button3.setOnClickListener {
            var intent = Intent(this, ActivityAcao2::class.java)

            startActivityForResult(intent, 2)
        }

        button4.setOnClickListener {
            var intent = Intent(this, ActivityAcao3::class.java)

            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var mainLayout = findViewById<View>(R.id.mainLayout)

        var textView1 = findViewById<TextView>(R.id.textView1)
        var textView2 = findViewById<TextView>(R.id.textView2)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                1 -> {
                    textView1.setText(data?.getStringExtra("RESULT").toString())
                    viewModel._textView1 = data?.getStringExtra("RESULT").toString()
                }

                2 -> {
                    textView2.setText(data?.getStringExtra("RESULT").toString())
                    viewModel._textView2 = data?.getStringExtra("RESULT").toString()
                }
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            when (requestCode) {
                1 -> {
                    Snackbar.make(mainLayout, "Cancelado", Snackbar.LENGTH_LONG)
                        .setAction("ok"){}.show()
                }
            }
        }
    }
}