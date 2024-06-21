package com.example.growwell.ui.riwayat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.growwell.R

data class RiwayatItem(val tanggal: String, val nama: String, val usia: String, val status: String)

class RiwayatAdapter(private val riwayatList: List<RiwayatItem>) :
    RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder>() {

    class RiwayatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tanggal: TextView = itemView.findViewById(R.id.tanggal)
        val nama: TextView = itemView.findViewById(R.id.nama)
        val usia: TextView = itemView.findViewById(R.id.usia)
        val status: TextView = itemView.findViewById(R.id.status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_riwayat, parent, false)
        return RiwayatViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RiwayatViewHolder, position: Int) {
        val currentItem = riwayatList[position]
        holder.tanggal.text = currentItem.tanggal
        holder.nama.text = currentItem.nama
        holder.usia.text = currentItem.usia
        holder.status.text = currentItem.status
    }

    override fun getItemCount() = riwayatList.size
}
