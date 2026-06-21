package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uts.databinding.FragmentHomepageBinding
import java.util.Locale

class HomepageFragment : Fragment() {

    private var _binding: FragmentHomepageBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MateriAdapter
    private var listMateri = ArrayList<MateriModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomepageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSearchView()
        
        binding.btnMulai.setOnClickListener {
            binding.rvMateri.smoothScrollToPosition(0)
        }
    }

    private fun setupRecyclerView() {
        listMateri = ArrayList(getMateriData())
        adapter = MateriAdapter(listMateri) { materi ->
            val intent = Intent(requireContext(), DetailMateriActivity::class.java)
            intent.putExtra("EXTRA_MATERI", materi)
            startActivity(intent)
        }

        binding.rvMateri.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMateri.adapter = adapter
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return true
            }
        })
    }

    private fun filter(text: String?) {
        val filteredList = ArrayList<MateriModel>()
        val query = text?.lowercase(Locale.ROOT) ?: ""
        
        for (item in listMateri) {
            if (item.name.lowercase(Locale.ROOT).contains(query)) {
                filteredList.add(item)
            }
        }
        adapter.filterList(filteredList)
    }

    private fun getMateriData(): List<MateriModel> {
        return listOf(
            MateriModel(
                "HTML",
                "Bahasa markup standar untuk pembuatan halaman web.",
                "HTML (HyperText Markup Language) adalah tulang punggung dari setiap halaman web. Ia mendefinisikan struktur dan konten sebuah situs web.",
                "Beginner",
                R.drawable.foto_html,
                R.drawable.html,
                "Menentukan struktur halaman web menggunakan elemen-elemen seperti header, paragraf, dan link.",
                "<!DOCTYPE html>\n<html>\n<body>\n  <h1>Halo Dunia</h1>\n</body>\n</html>",
                "<!DOCTYPE html> mendeklarasikan tipe dokumen. <html> adalah akar elemen. <h1> adalah judul.",
                "Halo Dunia (sebagai teks besar)",
                "HTML pertama kali diciptakan oleh Tim Berners-Lee pada tahun 1991.",
                listOf(
                    QuizModel("Apa kepanjangan HTML?", "HyperText Markup Language"),
                    QuizModel("Tag apa yang digunakan untuk judul terbesar?", "h1"),
                    QuizModel("Tag apa untuk membuat baris baru?", "br")
                )
            ),
            MateriModel(
                "CSS",
                "Bahasa untuk mengatur tampilan elemen HTML.",
                "CSS (Cascading Style Sheets) digunakan untuk mengatur gaya visual halaman web, mulai dari warna hingga tata letak.",
                "Beginner",
                R.drawable.foto_css,
                R.drawable.css,
                "Memisahkan konten (HTML) dari desain (CSS) agar situs lebih mudah dikelola.",
                "h1 {\n  color: navy;\n  text-align: center;\n}",
                "Kode ini mengubah warna teks h1 menjadi navy dan mengatur posisinya ke tengah.",
                "Teks h1 berwarna navy di tengah halaman",
                "CSS diperkenalkan pertama kali pada tahun 1996 oleh W3C.",
                listOf(
                    QuizModel("Apa kepanjangan CSS?", "Cascading Style Sheets"),
                    QuizModel("Properti apa untuk mengubah warna latar?", "background-color"),
                    QuizModel("Simbol apa untuk ID selector?", "# (Hash)")
                )
            ),
            MateriModel(
                "JavaScript",
                "Bahasa pemrograman untuk interaktivitas web.",
                "JavaScript memungkinkan Anda membuat konten yang bergerak, mengontrol multimedia, dan melakukan hal-hal kompleks di web.",
                "Intermediate",
                R.drawable.foto_js,
                R.drawable.js,
                "Membuat halaman web menjadi dinamis dan interaktif (validasi form, animasi, dll).",
                "console.log('Hello World');",
                "console.log digunakan untuk mencetak pesan ke konsol browser.",
                "Hello World (di konsol)",
                "Brendan Eich membuat JavaScript hanya dalam waktu 10 hari!",
                listOf(
                    QuizModel("Tipe data apa untuk teks?", "String"),
                    QuizModel("Kata kunci apa untuk variabel tetap?", "const"),
                    QuizModel("Fungsi apa untuk menampilkan alert?", "alert()")
                )
            ),
            MateriModel(
                "Python",
                "Bahasa tingkat tinggi yang sangat populer.",
                "Python adalah bahasa serbaguna yang mudah dibaca, sering digunakan untuk AI, data science, dan web backend.",
                "Beginner",
                R.drawable.foto_python,
                R.drawable.python,
                "Analisis data, kecerdasan buatan, otomasi, dan pengembangan web.",
                "print('Halo Python')",
                "Fungsi print() menampilkan teks ke layar secara sederhana.",
                "Halo Python",
                "Nama Python diambil dari grup komedi 'Monty Python', bukan ular.",
                listOf(
                    QuizModel("Apa ekstensi file Python?", ".py"),
                    QuizModel("Fungsi untuk input data?", "input()"),
                    QuizModel("Siapa pencipta Python?", "Guido van Rossum")
                )
            ),
            MateriModel(
                "PHP",
                "Bahasa scripting server-side untuk web.",
                "PHP banyak digunakan untuk mengelola konten dinamis, database, dan session pada sebuah website.",
                "Intermediate",
                R.drawable.foto_php,
                R.drawable.php,
                "Berinteraksi dengan database (seperti MySQL) untuk membuat web aplikasi dinamis.",
                "<?php echo 'Halo PHP'; ?>",
                "Tag <?php ?> menandai awal dan akhir blok kode PHP.",
                "Halo PHP",
                "PHP awalnya adalah singkatan dari 'Personal Home Page'.",
                listOf(
                    QuizModel("Apa singkatan PHP sekarang?", "PHP: Hypertext Preprocessor"),
                    QuizModel("Simbol variabel di PHP?", "$ (Dollar)"),
                    QuizModel("Fungsi untuk cetak teks?", "echo atau print")
                )
            ),
            MateriModel(
                "Java",
                "Bahasa pemrograman berorientasi objek.",
                "Java memiliki moto 'Write Once, Run Anywhere'. Sangat populer untuk aplikasi enterprise dan Android.",
                "Advanced",
                R.drawable.foto_java,
                R.drawable.java,
                "Membangun aplikasi Android, sistem perbankan, dan aplikasi backend besar.",
                "System.out.println(\"Halo Java\");",
                "System.out.println digunakan untuk mencetak baris teks baru ke konsol.",
                "Halo Java",
                "Nama Java terinspirasi dari kopi asal Jawa (Java Coffee).",
                listOf(
                    QuizModel("Java dikembangkan oleh?", "Sun Microsystems"),
                    QuizModel("Apa itu JVM?", "Java Virtual Machine"),
                    QuizModel("Tipe data angka bulat?", "int")
                )
            ),
            MateriModel(
                "C",
                "Bahasa pemrograman tingkat menengah yang efisien.",
                "C adalah bahasa fundamental yang memberikan kontrol penuh atas hardware dan memori.",
                "Advanced",
                R.drawable.foto_c,
                R.drawable.c,
                "Pembuatan sistem operasi (OS), driver perangkat, dan aplikasi performa tinggi.",
                "#include <stdio.h>\nint main() {\n  printf(\"Halo C\");\n  return 0;\n}",
                "#include mengimpor library, main() adalah titik masuk program.",
                "Halo C",
                "Bahasa C adalah dasar bagi banyak bahasa modern seperti C++, Java, dan C#.",
                listOf(
                    QuizModel("Library standar untuk input/output?", "stdio.h"),
                    QuizModel("Simbol untuk pointer?", "*"),
                    QuizModel("Siapa pencipta bahasa C?", "Dennis Ritchie")
                )
            ),
            MateriModel(
                "C++",
                "Pengembangan dari bahasa C dengan dukungan OOP.",
                "C++ menambahkan fitur Object-Oriented Programming (OOP) pada bahasa C, sangat cepat dan kuat.",
                "Advanced",
                R.drawable.foto_cpp,
                R.drawable.cpp,
                "Pembuatan game modern (Game Engine), browser, dan software kompleks.",
                "#include <iostream>\nint main() {\n  std::cout << \"Halo C++\";\n  return 0;\n}",
                "std::cout digunakan untuk menampilkan output teks ke layar.",
                "Halo C++",
                "C++ awalnya disebut 'C with Classes'.",
                listOf(
                    QuizModel("Library standar untuk input/output?", "iostream"),
                    QuizModel("Operator untuk output?", "<<"),
                    QuizModel("Pencipta C++?", "Bjarne Stroustrup")
                )
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
