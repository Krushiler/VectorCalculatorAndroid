package com.example.vectorcalculations_android.vectorcalculations.util

import kotlin.math.round

fun Double.roundTo(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}