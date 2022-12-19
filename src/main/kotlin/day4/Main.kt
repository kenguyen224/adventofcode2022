package day4

import java.io.File


/**
 * Created by KeNV on 16/12/2022.
 * https://adventofcode.com/2022/day/4
 */
fun main() {
    partOne()
    partTwo()
}

fun partOne() {
    val input = "src/main/kotlin/day4/input.txt"
    var count = 0
    File(input).useLines { it.toList() }.forEach { line ->
        val pairs = line.split(",")
        val pairOne = convertSessionToIntRange(pairs.first()) ?: return@forEach
        val pairTwo = convertSessionToIntRange(pairs.last()) ?: return@forEach
        if (pairOne.containFully(pairTwo) || pairTwo.containFully(pairOne)) {
            count++
        }
    }
    println("Result part one: $count")
}

fun partTwo() {
    val input = "src/main/kotlin/day4/input.txt"
    var count = 0
    File(input).useLines { it.toList() }.forEach { line ->
        val pairs = line.split(",")
        val pairOne = convertSessionToIntRange(pairs.first()) ?: return@forEach
        val pairTwo = convertSessionToIntRange(pairs.last()) ?: return@forEach
        if (pairOne.isOverlap(pairTwo) || pairTwo.isOverlap(pairOne)) {
            count++
        }
    }
    println("Result part two: $count")
}

fun IntRange.containFully(other: IntRange): Boolean = other.first >= first && other.last <= last

fun IntRange.isOverlap(other: IntRange): Boolean = (containFully(other) || (other.first <= last && other.last >= last))

/**
 * 2-4 -> IntRange(2,4)
 * If error when converting return null
 */
fun convertSessionToIntRange(session: String): IntRange? {
    return try {
        val splits = session.split("-")
        val start = splits.first().toInt()
        val end = splits.last().toInt()
        if (start > end) {
            println("Start must be smaller or equal to end [$session]")
            return null
        }
        IntRange(start, end)
    } catch (ex: Throwable) {
        ex.printStackTrace()
        null
    }
}
