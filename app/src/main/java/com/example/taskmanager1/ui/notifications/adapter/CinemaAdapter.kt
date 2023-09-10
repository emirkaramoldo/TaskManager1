package com.example.taskmanager1.ui.notifications.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager1.R

import com.example.taskmanager1.databinding.ItemTaskBinding
import com.example.taskmanager1.model.Cinema
import com.example.taskmanager1.model.Task

class CinemaAdapter(
) : RecyclerView.Adapter<CinemaAdapter.CinemaViewHolder>() {

    private val list = arrayListOf<Cinema>()

    fun addCinema(cimemas: List<Cinema>) {
        list.clear()
        list.addAll(cimemas)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewHolder {
        return CinemaViewHolder(
            ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
        val cinema = list[position]
        holder.bind(cinema, position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class CinemaViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cinema: Cinema, position: Int) = with(binding) {
            tvTitle.text = cinema.name
            tvDesc.text = cinema.director
        }
    }
}