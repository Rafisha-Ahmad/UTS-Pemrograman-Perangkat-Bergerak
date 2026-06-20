package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Dashboard : AppCompatActivity() {

    private var username: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        setContentView(R.layout.activity_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        username = intent.getStringExtra("username") ?: ""

        val tvHeaderDashboard = findViewById<TextView>(R.id.tvHeaderDashboard)
        val tvSHDashboard = findViewById<TextView>(R.id.tvSHDashboard)
        val btnHPDashboard = findViewById<Button>(R.id.btnHPDashboard)
        val btnAboutDashboard = findViewById<Button>(R.id.btnAboutDashboard)

        tvHeaderDashboard.text = "CodeLearn, $username"

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerDashboard,
                    HomepageFragment()).commit()
        }

        btnHPDashboard.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerDashboard,
                    HomepageFragment()).commit()
        }

        btnAboutDashboard.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerDashboard,
                    AboutFragment()).commit()
        }
    }
    fun bukaMateri() {
        val intentDashboard = Intent(this, Materi::class.java)
        startActivity(intentDashboard)
        }
    }