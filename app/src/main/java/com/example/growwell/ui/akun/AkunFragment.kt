package com.example.growwell.ui.akun

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.growwell.R
import com.example.growwell.databinding.FragmentAkunBinding

class AkunFragment : Fragment() {

    private var _binding: FragmentAkunBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAkunBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.fullName.observe(viewLifecycleOwner) { fullName ->
            binding.profileName.text = fullName
        }

        sharedViewModel.email.observe(viewLifecycleOwner) { email ->
            binding.profileEmail.text = email
        }

        sharedViewModel.birthDate.observe(viewLifecycleOwner) { birthDate ->
            binding.profileBirthDate.text = birthDate
        }

        binding.btnEditProfile.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_akun_to_editAccountFragment)
        }

        binding.btnSyaratKetentuan.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_akun_to_syaratKetentuanFragment)
        }

        binding.btnTentangKami.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_akun_to_aboutFragment)
        }

        binding.btnKeluar.setOnClickListener {
            // Implementasikan logika untuk tombol keluar jika diperlukan
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
