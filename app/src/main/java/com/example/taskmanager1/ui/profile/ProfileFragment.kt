package com.example.taskmanager1.ui.profile

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.taskmanager1.R

import com.example.taskmanager1.data.local.Pref
import com.example.taskmanager1.databinding.FragmentProfileBinding
import com.example.taskmanager1.utils.loadImage
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: FragmentProfileBinding

    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK && it.data != null){
                val uri: Uri? = it.data?.data
                pref.saveAvatar(uri.toString())
                binding.profileImage.loadImage(uri.toString())
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveName()
        saveImage()
        mAuth = FirebaseAuth.getInstance()
        initClickers()
    }

    private fun saveImage() {
        pref.getAvatar()?.let{ binding.profileImage.loadImage(it) }
        binding.profileImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            launcher.launch(intent)
        }

    }
    private fun saveName() {
        binding.etName.setText(pref.getName())
        binding.etName.addTextChangedListener {
            pref.saveName(binding.etName.text.toString())
        }
    }

    private fun initClickers() {
        with(binding) {
            btnLogout.setOnClickListener {
                val alert = AlertDialog.Builder(requireContext())
                    .setTitle("Вы действительно хотите выйти?")
                    .setPositiveButton("Да"){_, _ ->
                        mAuth.signOut()
                        findNavController().navigate(R.id.authFragment)
                        Toast.makeText(requireContext(), "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("Нет"){dialog, _ -> dialog.dismiss()
                    }
                alert.create().show()
            }
            }
        }
    }