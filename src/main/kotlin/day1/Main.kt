package day1

import java.io.File
import java.io.FileReader
import java.io.IOException

/**
 * https://adventofcode.com/2022/day/1
 */
fun main() {
    partOne()
    partTwo()
}

fun partOne() {
    val file = File("src/main/kotlin/day1/input.txt")
    var maxSumCalories: Long = 0
    try {
        val fileReader = FileReader(file)
        val lines = fileReader.useLines { it.toMutableList() }.apply { add("/") }
        var sumCalories: Long = 0
        lines.forEach { line ->
            if (line.isBlank() || line == "/") {
                if (sumCalories > maxSumCalories) {
                    maxSumCalories = sumCalories
                }
                sumCalories = 0

            } else {
                sumCalories += (line.toLong())
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    println("Result part one: $maxSumCalories")
}

fun partTwo() {
    val file = File("src/main/kotlin/day1/input.txt")
    val topNum = 3
    val topMaxSumCalories = mutableListOf<Long>()
    try {
        val fileReader = FileReader(file)
        val lines = fileReader.useLines { it.toMutableList() }.apply { add("/") }
        var sumCalories: Long = 0
        lines.forEach { line ->
            if (line.isBlank() || line == "/") {
                if (topMaxSumCalories.size < topNum) {
                    topMaxSumCalories.add(sumCalories)
                } else {
                    if (sumCalories > (topMaxSumCalories.minOrNull() ?: 0)) {
                        topMaxSumCalories.add(sumCalories)
                        topMaxSumCalories.sortDescending()
                        topMaxSumCalories.removeLast()
                    }
                }
                sumCalories = 0
            } else {
                sumCalories += (line.toLong())
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    println("Result part two: ${topMaxSumCalories.sum()}")
}
