package com.example.taskmanager1.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.taskmanager1.R

import com.example.taskmanager1.databinding.ItemOnBoardingBinding
import com.example.taskmanager1.model.OnBoarding

class OnBoardingAdapter(
    private val onClick: () -> Unit,
) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {


    private val list = arrayListOf(
        OnBoarding(
            "Plan a Trip",
            "Be ready to depart anytime, anywhere",
            R.raw.animation_llw3tstr
        ),
        OnBoarding(
            "Book the Flight",
            "Pay in seconds",
            R.raw.animation_llw469tw
        ),
        OnBoarding(
            "Enjoy your Trip",
            "Bringing you joy ",
            R.raw.animation_llw46kay
        )
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnBoardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnBoardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoarding: OnBoarding) = with(binding) {
            tvTitle.text = onBoarding.title
            tvDesc.text = onBoarding.description
            onBoarding.anim?.let{
                binding.animBoard.setAnimation(onBoarding.anim)
                binding.animBoard.playAnimation()
            }
            binding.btnStart.isVisible = adapterPosition == list.lastIndex
            binding.btnSkip.isVisible = adapterPosition != list.lastIndex

            binding.btnStart.setOnClickListener {
                onClick()
            }
            binding.btnSkip.setOnClickListener {
                onClick()
            }
        }
    }
}