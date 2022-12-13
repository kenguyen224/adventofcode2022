package day3

import java.io.File

/**
 * Created by KeNV on 13/12/2022.
 * https://adventofcode.com/2022/day/3
 */

fun main() {
    partOne()
}

val itemPriority = mutableMapOf<String, Int>().also {
    var count = 1
    for (item in 'a'..'z') {
        it[item.toString()] = count
        count++
    }
    for (item in 'A'..'Z') {
        it[item.toString()] = count
        count++
    }
}

fun partOne() {
    val file = File("src/main/kotlin/day3/input.txt")
    var sumOfPriorities = 0
    file.useLines { it.toList() }.forEach { line ->
        val length = line.length
        val firstCompartment = line.substring(0, length / 2)
        val secondCompartment = line.substring(length / 2, length).toSet()

        firstCompartment.forEach label@ { item ->
            if (secondCompartment.contains(item)) {
                sumOfPriorities += (itemPriority[item.toString()] ?: 0)
                return@forEach
            }
        }
    }
    println("Result part one is: $sumOfPriorities")
}
