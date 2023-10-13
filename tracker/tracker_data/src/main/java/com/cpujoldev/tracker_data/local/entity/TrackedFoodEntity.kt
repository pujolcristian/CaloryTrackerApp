package com.cpujoldev.tracker_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrackedFoodEntity(
    val name: String,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val imageUrl: String?,
    val type: String,
    val amount: Int,
    val dayOfMonth: String,
    val month: String,
    val year: String,
    val calories: Int,
    @PrimaryKey val id: Int? = null
)
