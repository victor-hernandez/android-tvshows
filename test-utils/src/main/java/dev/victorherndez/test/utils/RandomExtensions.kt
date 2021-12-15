package dev.victorherndez.test.utils

import kotlin.random.Random

private val CHAR_POOL = ('a'..'z') + ('A'..'Z') + ('0'..'9')

fun Random.nextString(length: Int = 16): String =
    (1..length)
        .map { Random.nextInt(0, CHAR_POOL.size) }
        .map(CHAR_POOL::get)
        .joinToString("")
