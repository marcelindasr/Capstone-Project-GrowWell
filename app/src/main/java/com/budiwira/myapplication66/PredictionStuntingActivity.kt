package com.budiwira.myapplication66

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.budiwira.myapplication66.databinding.ActivityPredictionStuntingBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PredictionStuntingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPredictionStuntingBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPredictionStuntingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance().reference.child("predictions")

        binding.btnSelanjutnya.setOnClickListener {
            val height = binding.etPrediction.text.toString().trim()
            val weight = binding.editText2.text.toString().trim()
            val age = binding.editText3.text.toString().trim()

            if (height.isEmpty() || weight.isEmpty() || age.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                makePrediction(height, weight, age)
            }
        }
    }

    private fun makePrediction(height: String, weight: String, age: String) {
        val predictionId = database.push().key ?: return

        val prediction = Prediction(predictionId, height, weight, age)

        database.child(predictionId).setValue(prediction)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Prediction submitted successfully", Toast.LENGTH_SHORT).show()
                    // Optionally, navigate to another activity or perform other actions
                } else {
                    Toast.makeText(this, "Failed to submit prediction", Toast.LENGTH_SHORT).show()
                    // Log the error or handle it as needed
                }
            }
    }

    data class Prediction(
        val id: String,
        val height: String,
        val weight: String,
        val age: String
    )
}
