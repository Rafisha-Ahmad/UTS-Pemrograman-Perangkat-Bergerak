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
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog

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

        val tvHeaderDashboard = findViewById<TextView>(R.id.tvHeaderDashboard)
        val tvSHDashboard = findViewById<TextView>(R.id.tvSHDashboard)
        val btnHPDashboard = findViewById<Button>(R.id.btnHPDashboard)
        val btnAboutDashboard = findViewById<Button>(R.id.btnAboutDashboard)
        val btnProfile = findViewById<ImageButton>(R.id.btnProfile)

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

        btnProfile.setOnClickListener { view -> val popup = PopupMenu(this, view)
            popup.menu.add("Profile")
            popup.menu.add("Logout")
            popup.setOnMenuItemClickListener {
                when (it.title) {
                    "Profile" -> {
                        val intent = Intent(this, Profile::class.java)
                        intent.putExtra("username", username)
                        startActivity(intent)
                        true }
                    "Logout" -> {

                        AlertDialog.Builder(this)
                            .setTitle("Logout")
                            .setMessage("Yakin ingin logout?")
                            .setPositiveButton("Ya") { _, _ ->

                                val intent = Intent(this, Login::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or
                                            Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                                finish()
                            }.setNegativeButton("Tidak", null).show()
                        true
                    } else -> false
                }
            }
            popup.show()
        }
    }
    fun bukaMateri() {
        val intentDashboard = Intent(this, Materi::class.java)
        startActivity(intentDashboard)
        }
    }