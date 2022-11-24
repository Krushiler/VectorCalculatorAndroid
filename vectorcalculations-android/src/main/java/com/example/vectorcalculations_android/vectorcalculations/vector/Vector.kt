package com.example.vectorcalculations_android.vectorcalculations.vector

import android.os.Parcelable
import com.example.vectorcalculations_android.vectorcalculations.util.IncorrectDimensionException
import kotlinx.parcelize.Parcelize
import kotlin.math.sqrt

@Parcelize
data class Vector(
    val coordinates: List<Double>
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is Vector) return false
        if (coordinates.size != other.coordinates.size) return false
        for (i in coordinates.indices) {
            if (coordinates[i] != other.coordinates[i]) return false
        }
        return true
    }

    operator fun get(index: Int) = coordinates[index]

    companion object {
        fun createNormalized(cords1: List<Double>, cords2: List<Double>): Vector {
            if (cords1.size != cords2.size) throw IncorrectDimensionException("Coordinates must have the save length")
            val normalizedCords: List<Double> = cords2.mapIndexed { index, value ->
                value - cords1[index]
            }
            return Vector(normalizedCords)
        }
    }

    val dimensions: Int
        get() = coordinates.size

    val length: Double
        get() {
            var sum = 0.0
            coordinates.forEach {
                sum += it * it
            }
            return sqrt(sum)
        }

    override fun toString(): String {
        return buildString {
            append('(')
            for (i in coordinates.indices) {
                append(coordinates[i])
                if (i < coordinates.size - 1) append(", ")
            }
            append(')')
        }
    }

    fun createOpposite(): Vector {
        return Vector(coordinates.map {
            -it
        })
    }
}
