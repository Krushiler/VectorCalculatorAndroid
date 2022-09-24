package com.example.vectors.domain.vector

import android.os.Parcelable
import com.example.vectors.domain.util.IncorrectDimensionException
import kotlinx.parcelize.Parcelize
import kotlin.math.sqrt

@Parcelize
data class Vector(
    val coordinates: List<Double>
) : Parcelable {

    companion object {
        fun createNormalized(cords1: List<Double>, cords2: List<Double>): Vector {
            if (cords1 != cords2) throw IncorrectDimensionException("Coordinates must have the save length")
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
            append("[")
            for (i in coordinates.indices) {
                append(coordinates[i])
                if (i < coordinates.size - 1) append(", ")
            }
            append("]")
        }
    }

    fun createOpposite(): Vector {
        return Vector(coordinates.map {
            -it
        })
    }
}
