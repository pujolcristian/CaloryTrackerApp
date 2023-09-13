package com.cpujoldev.calorytrackerapp.navigation

import androidx.navigation.NavController
import com.cpujoldev.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}
