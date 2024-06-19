// DetailActivity.kt
package com.example.growwell.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.growwell.R
import com.example.growwell.databinding.FragmentDetailActivityBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: FragmentDetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cek data yang diterima dari Intent
        val title = intent.getStringExtra("title") ?: "No Title"
        val description = intent.getStringExtra("description") ?: "No Description"
        val imageResId = intent.getIntExtra("image", 0)

        // Set data ke views
        binding.detailTitle.text = title
        binding.detailDescription.text = description
        if (imageResId != 0) {
            binding.detailImage.setImageResource(imageResId)
        } else {
            // Handle jika imageResId tidak valid
            binding.detailImage.setImageResource(R.drawable.logo) // ganti dengan gambar default
        }
    }
}
