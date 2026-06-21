package com.example.uts

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.uts.databinding.ItemMateriBinding

class MateriAdapter(
    private var listMateri: List<MateriModel>,
    private val onItemClick: (MateriModel) -> Unit
) : RecyclerView.Adapter<MateriAdapter.ListViewHolder>() {

    class ListViewHolder(val binding: ItemMateriBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(newList: List<MateriModel>) {
        listMateri = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemMateriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val materi = listMateri[position]
        
        with(holder.binding) {
            imgMateri.setImageResource(materi.imageIcon)
            tvNamaMateri.text = materi.name
            tvDeskripsiSingkat.text = materi.shortDesc
            tvLevel.text = materi.level.uppercase()
            
            val context = root.context
            when (materi.level.lowercase()) {
                "beginner", "pemula" -> tvLevel.setTextColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
                "intermediate", "menengah" -> tvLevel.setTextColor(ContextCompat.getColor(context, android.R.color.holo_orange_dark))
                "advanced", "sulit" -> tvLevel.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
            }

            root.setOnClickListener { onItemClick(materi) }
        }
    }

    override fun getItemCount(): Int = listMateri.size
}
