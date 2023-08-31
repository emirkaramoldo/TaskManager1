package com.example.taskmanager1.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager1.App
import com.example.taskmanager1.R
import com.example.taskmanager1.databinding.FragmentHomeBinding
import com.example.taskmanager1.model.Task
import com.example.taskmanager1.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val adapter = TaskAdapter(this::onLongClickItem)
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter

        val data = App.db.taskDao().getAll()
        adapter.addTasks(data)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onLongClickItem(task: Task){
        showAlertDialog(task)
    }

    private fun showAlertDialog(task: Task) {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle(task.title.toString())
            .setMessage("Вы хотите удалить задачу?")
            .setCancelable(true)
            .setPositiveButton("Да"){_,_ ->
                App.db.taskDao().delete(task)
                val data = App.db.taskDao().getAll()
                adapter.addTasks(data)
            }
            .setNegativeButton("Нет"){_,_ ->

            }
            .show()
    }
}