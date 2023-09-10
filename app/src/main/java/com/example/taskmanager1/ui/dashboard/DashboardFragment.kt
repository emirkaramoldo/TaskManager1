package com.example.taskmanager1.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager1.App
import com.example.taskmanager1.R

import com.example.taskmanager1.databinding.FragmentDashboardBinding
import com.example.taskmanager1.databinding.FragmentHomeBinding
import com.example.taskmanager1.databinding.FragmentProfileBinding
import com.example.taskmanager1.model.Cinema
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            val data  = Cinema(
                binding.etName.text.toString(),
                binding.etDirector.text.toString()
            )
            db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
                .add(data)
                .addOnSuccessListener {
                    binding.etName.text?.clear()
                    binding.etDirector.text?.clear()
                    Toast.makeText(requireContext(), "Успешно сохранено", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener{
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
        }
    }
}