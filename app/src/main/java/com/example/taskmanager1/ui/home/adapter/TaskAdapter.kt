package com.example.taskmanager1.ui.home.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager1.R

import com.example.taskmanager1.databinding.ItemTaskBinding
import com.example.taskmanager1.model.Task

class TaskAdapter(
    private val onLongClick: (Task) -> Unit,
    private val onClick: (Task) -> Unit,
    private val onSuccess: (Task) -> Unit,
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val list = arrayListOf<Task>()

    fun addTasks(tasks: List<Task>) {
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = list[position]
        holder.bind(task, position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task, position: Int) = with(binding) {
            if (position % 2 == 0) {
                itemView.setBackgroundResource(R.color.white)
                tvTitle.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
                tvDesc.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
            } else {
                itemView.setBackgroundResource(R.color.black)
                tvTitle.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                tvDesc.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            }
            binding.checkbox.isChecked = task.isSuccess
            if (task.isSuccess){
                tvTitle.paintFlags = tvTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                tvDesc.paintFlags = tvDesc.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
            else{
                tvTitle.paintFlags = 0
                tvDesc.paintFlags = 0
            }
            tvTitle.text = task.title
            tvDesc.text = task.description
            checkbox.setOnCheckedChangeListener{ _, isSuccess ->
                onSuccess(task.copy(isSuccess = isSuccess))
            }
            itemView.setOnLongClickListener {
                onLongClick(task)
                false
            }
            itemView.setOnClickListener{
                onClick(task)
            }
        }
    }
}