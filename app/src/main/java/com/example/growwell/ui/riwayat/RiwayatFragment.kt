package com.example.growwell.ui.riwayat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.growwell.R

class RiwayatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_riwayat, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Dummy data
        val riwayatList = listOf(
            RiwayatItem("20 Mei 2024", "Ananda Putri Cahya Ningsi", "2 Tahun 3 Bulan", "Status Kesehatan: Sehat"),
            RiwayatItem("12 Oktober 2024", "Ananda Putra Surya Kencana", "2 Tahun 8 Bulan", "Status Kesehatan: Stunting"),
            RiwayatItem("25 Februari 2025", "Ananda Steven Bernard", "3 Tahun", "Status Kesehatan: Sehat")
        )

        val adapter = RiwayatAdapter(riwayatList)
        recyclerView.adapter = adapter

        return view
    }
}
