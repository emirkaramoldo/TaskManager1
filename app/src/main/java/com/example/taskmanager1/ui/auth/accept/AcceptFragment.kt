package com.example.taskmanager1.ui.auth.accept

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.taskmanager1.R
import com.example.taskmanager1.databinding.FragmentAcceptBinding
import com.example.taskmanager1.databinding.FragmentPhoneBinding
import com.example.taskmanager1.ui.auth.phone.PhoneFragment
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class AcceptFragment : Fragment() {

    private lateinit var binding: FragmentAcceptBinding
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAcceptBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val verId: String = arguments?.getString(PhoneFragment.VER_KEY).toString()
        binding.btnAccept.setOnClickListener{
            val credential = PhoneAuthProvider.getCredential(verId, binding.etCode.text.toString())
            signInWithAuthCredential(credential)
        }
    }
    private fun signInWithAuthCredential(credential: PhoneAuthCredential){
        auth.signInWithCredential(credential).addOnSuccessListener {

            findNavController().navigate(R.id.navigation_home)
        }.addOnFailureListener{
            Toast.makeText(requireContext(), "Error:${it.message}", Toast.LENGTH_SHORT).show()
        }
    }
}