package com.example.taskmanager1.ui.notifications

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
import com.example.taskmanager1.databinding.FragmentNotificationsBinding
import com.example.taskmanager1.databinding.FragmentProfileBinding
import com.example.taskmanager1.model.Cinema
import com.example.taskmanager1.ui.notifications.adapter.CinemaAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NotificationsFragment : Fragment() {
    private lateinit var binding: FragmentNotificationsBinding
    private val db = Firebase.firestore
    private val adapter = CinemaAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .get()
            .addOnSuccessListener {
                val list = it.toObjects(Cinema::class.java)
                adapter.addCinema(list)
            }.addOnFailureListener{
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
    }
}