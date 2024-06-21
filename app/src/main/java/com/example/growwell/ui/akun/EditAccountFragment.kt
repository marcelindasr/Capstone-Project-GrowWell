package com.example.growwell.ui.akun

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.growwell.R

class EditAccountFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fullNameEditText = view.findViewById<EditText>(R.id.full_name)
        val emailEditText = view.findViewById<EditText>(R.id.email)
        val birthDateEditText = view.findViewById<EditText>(R.id.birth_date)
        val saveButton = view.findViewById<Button>(R.id.save_button)

        saveButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val birthDate = birthDateEditText.text.toString()

            sharedViewModel.setFullName(fullName)
            sharedViewModel.setEmail(email)
            sharedViewModel.setBirthDate(birthDate)

            findNavController().navigateUp()
        }
    }
}
