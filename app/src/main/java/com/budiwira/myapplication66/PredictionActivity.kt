package com.budiwira.myapplication66

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.budiwira.myapplication66.databinding.ActivityPredictionBinding

class PredictionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPredictionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPredictionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelanjutnya.setOnClickListener {
            Log.d("PredictionActivity", "btnSelanjutnya clicked")
            val intent = Intent(this, PredictionStuntingActivity::class.java)
            startActivity(intent)
            Log.d("PredictionActivity", "Intent started")
        }
    }
}
