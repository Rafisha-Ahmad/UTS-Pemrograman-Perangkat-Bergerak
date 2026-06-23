package com.example.uts

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Profile : AppCompatActivity() {

    private lateinit var ivProfile: ImageView
    private lateinit var etNama: EditText
    private lateinit var etNim: EditText
    private lateinit var etProdi: EditText
    private lateinit var etJurusan: EditText
    
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        // Bind Views
        ivProfile = findViewById(R.id.ivProfile)
        etNama = findViewById(R.id.etNama)
        etNim = findViewById(R.id.etNim)
        etProdi = findViewById(R.id.etProdi)
        etJurusan = findViewById(R.id.etJurusan)
        val fabEditPhoto = findViewById<FloatingActionButton>(R.id.fabEditPhoto)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Load Saved Data
        loadProfileData()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fabEditPhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            }
            startActivityForResult(intent, 100)
        }

        btnSave.setOnClickListener {
            saveProfileData()
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun saveProfileData() {
        val sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("nama", etNama.text.toString())
            putString("nim", etNim.text.toString())
            putString("prodi", etProdi.text.toString())
            putString("jurusan", etJurusan.text.toString())
            putString("imageUri", imageUri?.toString())
            apply()
        }
        Toast.makeText(this, "Profil Berhasil Disimpan!", Toast.LENGTH_SHORT).show()
    }

    private fun loadProfileData() {
        val sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        etNama.setText(sharedPref.getString("nama", ""))
        etNim.setText(sharedPref.getString("nim", ""))
        etProdi.setText(sharedPref.getString("prodi", ""))
        etJurusan.setText(sharedPref.getString("jurusan", ""))
        
        val savedUri = sharedPref.getString("imageUri", null)
        if (savedUri != null) {
            imageUri = Uri.parse(savedUri)
            // Grant persistable permission for the URI if needed, 
            // but for simple display, we try to set it
            try {
                contentResolver.takePersistableUriPermission(imageUri!!, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                ivProfile.setImageURI(imageUri)
            } catch (e: Exception) {
                ivProfile.setImageURI(imageUri)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                imageUri = uri
                // Take persistable permission to ensure we can load it again after app restart
                contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                ivProfile.setImageURI(uri)
            }
        }
    }
}