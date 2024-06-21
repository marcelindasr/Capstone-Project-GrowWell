package com.budiwira.myapplication66

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var etFullName: EditText
    private lateinit var etBirthDate: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var tvLogin: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etFullName = findViewById(R.id.etFullName)
        etBirthDate = findViewById(R.id.etBirthDate)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnSignUp = findViewById(R.id.btnSignUp)
        tvLogin = findViewById(R.id.tvLogin)

        auth = FirebaseAuth.getInstance()

        val signUpTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val fullNameInput = etFullName.text.toString().trim()
                val birthDateInput = etBirthDate.text.toString().trim()
                val emailInput = etEmail.text.toString().trim()
                val passwordInput = etPassword.text.toString().trim()
                val confirmPasswordInput = etConfirmPassword.text.toString().trim()

                btnSignUp.isEnabled = fullNameInput.isNotEmpty() && birthDateInput.isNotEmpty() && emailInput.isNotEmpty() &&
                        passwordInput.isNotEmpty() && confirmPasswordInput.isNotEmpty() && passwordInput == confirmPasswordInput

                btnSignUp.setBackgroundColor(if (btnSignUp.isEnabled) 0xFF009688.toInt() else 0xFFCCCCCC.toInt())
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        etFullName.addTextChangedListener(signUpTextWatcher)
        etBirthDate.addTextChangedListener(signUpTextWatcher)
        etEmail.addTextChangedListener(signUpTextWatcher)
        etPassword.addTextChangedListener(signUpTextWatcher)
        etConfirmPassword.addTextChangedListener(signUpTextWatcher)

        btnSignUp.setOnClickListener {
            signUp()
        }

        tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUp() {
        val fullName = etFullName.text.toString().trim()
        val birthDate = etBirthDate.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        if (fullName.isNotEmpty() && birthDate.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            if (email.isValidEmail()) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign up success, update UI with the signed-in user's information
                            Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show()
                            // Navigate to LoginActivity
                            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish() // Finish SignUpActivity so it's removed from the back stack
                        } else {
                            // If sign up fails, display a message to the user.
                            Toast.makeText(this, "Signup failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }

    // Extension function to validate email
    fun String.isValidEmail() = android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
