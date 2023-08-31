package com.example.taskmanager1.model

import java.io.Serializable

data class OnBoarding(
    val title: String? = null,
    val description: String? = null,
    val anim: Int? = null,
) : Serializable