package com.example.uts

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.textfield.TextInputEditText


class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etUsername = findViewById<TextInputEditText>(R.id.etUsername)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {

            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username == "admin" && password == "admin123") {
                Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT)
                    .show()

                val intent = Intent(this, Dashboard::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Login gagal", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}