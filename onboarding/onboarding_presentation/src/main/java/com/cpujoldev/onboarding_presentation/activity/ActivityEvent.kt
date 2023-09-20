package com.cpujoldev.onboarding_presentation.activity

import com.cpujoldev.core.domain.model.ActivityLevel

sealed class ActivityEvent {
    data class OnActivityClick(val activityLevel: ActivityLevel) : ActivityEvent()
    object OnNextClick : ActivityEvent()
}
