package com.example.taskmanager1.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager1.App
import com.example.taskmanager1.R
import com.example.taskmanager1.databinding.FragmentHomeBinding
import com.example.taskmanager1.model.Task
import com.example.taskmanager1.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val adapter = TaskAdapter(this::onLongClick, this::onClick, this::onSuccess)
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

    private fun onClick(task: Task){
        findNavController().navigate(R.id.taskFragment, bundleOf(TASK_FOR_EDIT to task))
    }

    private fun onLongClick(task: Task) {
        val alert = AlertDialog.Builder(requireContext())
            .setTitle("Вы хотите удалить задачу?")
            .setPositiveButton("Да"){_, _ ->
                App.db.taskDao().delete(task)
                setData()
            }
            .setNegativeButton("Нет"){dialog, _ -> dialog.dismiss()
            }
        alert.create().show()
    }

    private fun onSuccess(task: Task){
        App.db.taskDao().update(task)
        setData()
    }

    private fun setData(){
        val data = App.db.taskDao().getAll()
        adapter.addTasks(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val TASK_FOR_EDIT = "task.edit"
    }
}