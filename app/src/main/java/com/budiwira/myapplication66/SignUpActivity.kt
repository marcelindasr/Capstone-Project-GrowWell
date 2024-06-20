package com.budiwira.myapplication66

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.budiwira.myapplication66.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var etFullName: EditText
    private lateinit var etBirthDate: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var tvLogin: TextView

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
            // Handle sign-up logic here
        }

        tvLogin.setOnClickListener {
            // Handle navigation to login page here
        }
    }
}
