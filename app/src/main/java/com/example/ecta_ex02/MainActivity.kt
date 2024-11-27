package com.example.ecta_ex02

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnUsuarios = findViewById<Button>(R.id.btnUsuarios)
        val btnCreditos = findViewById<Button>(R.id.btnCreditos)
        val btnSalir = findViewById<Button>(R.id.btnSalir)

        btnUsuarios.setOnClickListener{
            startActivity(intentUsuarios())
        }

        btnSalir.setOnClickListener{
            finishAffinity()
        }

        btnCreditos.setOnClickListener {
            startActivity(intentCreditos())
        }
    }

    private fun intentCreditos(): Intent {
        val intentCreditos = Intent(this, CreditosActivity::class.java)
        return intentCreditos
    }

    private fun intentUsuarios(): Intent {
        val intentUsuarios = Intent(this, UsuariosActivity::class.java)
        return intentUsuarios
    }
}