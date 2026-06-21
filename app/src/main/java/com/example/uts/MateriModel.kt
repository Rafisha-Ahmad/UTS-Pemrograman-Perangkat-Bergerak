package com.example.uts

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MateriModel(
    val name: String,
    val shortDesc: String,
    val detail: String,
    val level: String, // Beginner, Intermediate, Advanced
    val imageIcon: Int, // Resource ID untuk icon di list
    val imageDetail: Int, // Resource ID untuk header di detail
    val fungsi: String,
    val contohSintaks: String,
    val penjelasanKode: String,
    val contohOutput: String,
    val faktaMenarik: String,
    val quiz: List<QuizModel>
) : Parcelable

@Parcelize
data class QuizModel(
    val pertanyaan: String,
    val jawaban: String
) : Parcelable
