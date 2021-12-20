package dev.victorhernandez.tvshows.presentation.ui.ktx

import androidx.compose.ui.test.TouchInjectionScope
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight

fun TouchInjectionScope.swipeToNext() =
    swipeLeft(right, left/2, 1000)

fun TouchInjectionScope.swipeToPrevious() =
    swipeRight(left/2, right, 1000)