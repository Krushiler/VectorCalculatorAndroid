package com.example.vectorcalculations_android.vectorcalculations.matrix

import android.os.Parcelable
import com.example.vectorcalculations_android.vectorcalculations.vector.Vector
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Matrix(
    private val _rows: List<Vector>
) : Parcelable {

    operator fun get(row: Int, col: Int): Double = rows[row][col]
    operator fun get(row: Int): Vector = rows[row]

    fun setRow(index: Int, row: Vector): Matrix =
        Matrix(rows.toMutableList().apply {
            set(index, row)
        })

    companion object {
        fun fromColumns(columns: List<Vector>): Matrix =
            Matrix(columns).transposed()

        fun fromDoubles(doubles: List<List<Double>>): Matrix =
            Matrix(doubles.map {
                Vector(it)
            })
    }

    @IgnoredOnParcel
    val rows: List<Vector> = _rows
    val columns: List<Vector>
        get() {
            val result = mutableListOf<Vector>()
            for (col in _rows.first().coordinates.indices) {
                val column = mutableListOf<Double>()
                for (value in _rows) {
                    column.add(value.coordinates[col])
                }
                result.add(Vector(column))
            }
            return result.toList()
        }

    override fun toString(): String {
        return buildString {
            for (row in rows) {
                append(row)
                append('\n')
            }
        }
    }

    fun transposed() = Matrix(columns)

    fun swapRows(row1: Int, row2: Int): Matrix {
        val newRows = rows.toMutableList()
        newRows[row1] = newRows[row2].also { newRows[row2] = newRows[row1] }
        return Matrix(newRows.toList())
    }

    fun swapColumns(row1: Int, row2: Int): Matrix {
        val newColumns = columns.toMutableList()
        newColumns[row1] = newColumns[row2].also { newColumns[row2] = newColumns[row1] }
        return Matrix(newColumns.toList()).transposed()
    }
}