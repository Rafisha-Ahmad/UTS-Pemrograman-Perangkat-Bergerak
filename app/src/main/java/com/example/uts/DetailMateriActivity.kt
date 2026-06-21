package com.example.uts

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.uts.databinding.ActivityDetailMateriBinding

class DetailMateriActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMateriBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMateriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { 
            onBackPressedDispatcher.onBackPressed()
        }

        // Cara ambil Parcelable yang lebih aman untuk berbagai versi Android
        val materi = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("EXTRA_MATERI", MateriModel::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("EXTRA_MATERI")
        }
        
        materi?.let { setupUI(it) }
    }

    private fun setupUI(materi: MateriModel) {
        binding.collapsingToolbar.title = materi.name
        binding.imgDetailMateri.setImageResource(materi.imageDetail)
        binding.tvDetailDesc.text = materi.detail
        binding.tvDetailFungsi.text = materi.fungsi
        binding.tvFaktaMenarik.text = materi.faktaMenarik
        binding.tvContohKode.text = materi.contohSintaks
        binding.tvPenjelasanKode.text = materi.penjelasanKode
        binding.tvContohOutput.text = materi.contohOutput

        binding.containerQuiz.removeAllViews()
        materi.quiz.forEachIndexed { index, quiz ->
            val quizView = layoutInflater.inflate(R.layout.item_quiz, binding.containerQuiz, false)
            
            val tvPertanyaan = quizView.findViewById<TextView>(R.id.tvPertanyaan)
            val etJawabanUser = quizView.findViewById<EditText>(R.id.etJawabanUser)
            val btnCekJawaban = quizView.findViewById<Button>(R.id.btnCekJawaban)
            val tvHasilCek = quizView.findViewById<TextView>(R.id.tvHasilCek)
            val tvJawabanBenar = quizView.findViewById<TextView>(R.id.tvJawabanBenar)
            
            tvPertanyaan.text = "${index + 1}. ${quiz.pertanyaan}"
            tvJawabanBenar.text = "Jawaban benar: ${quiz.jawaban}"
            
            btnCekJawaban.setOnClickListener {
                val jawabanUser = etJawabanUser.text.toString().trim()
                if (jawabanUser.isEmpty()) {
                    etJawabanUser.error = "Isi jawaban dulu ya"
                    return@setOnClickListener
                }

                if (jawabanUser.equals(quiz.jawaban.trim(), ignoreCase = true)) {
                    tvHasilCek.text = "Benar! ✅"
                    tvHasilCek.setTextColor(getColor(android.R.color.holo_green_dark))
                } else {
                    tvHasilCek.text = "Salah! ❌"
                    tvHasilCek.setTextColor(getColor(android.R.color.holo_red_dark))
                }
                
                tvHasilCek.visibility = View.VISIBLE
                tvJawabanBenar.visibility = View.VISIBLE
            }
            
            binding.containerQuiz.addView(quizView)
        }
    }
}
