package com.budiwira.myapplication66

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var newPasswordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var changePasswordButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_change_password)

        newPasswordEditText = findViewById(R.id.new_password_edit_text)
        confirmPasswordEditText = findViewById(R.id.confirm_password_edit_text)
        changePasswordButton = findViewById(R.id.change_password_button)

        val backButton = findViewById<ImageButton>(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressed()
        }

        newPasswordEditText.addTextChangedListener(passwordTextWatcher)
        confirmPasswordEditText.addTextChangedListener(passwordTextWatcher)

        changePasswordButton.setOnClickListener {
            val newPassword = newPasswordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (newPassword == confirmPassword) {
                // Handle password change logic here
                Toast.makeText(this, "Password changed successfully!", Toast.LENGTH_SHORT).show()
                // Optionally, you can finish the activity or navigate to the login screen
                finish()
            } else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private val passwordTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // No action needed before text changes
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val newPassword = newPasswordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()
            changePasswordButton.isEnabled = newPassword.isNotEmpty() && confirmPassword.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {
            // No action needed after text changes
        }
    }
}
