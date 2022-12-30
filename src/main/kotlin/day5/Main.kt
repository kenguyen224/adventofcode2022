package day5

import java.io.File
import java.util.*

/**
 * Created by KeNV on 20/12/2022.
 * https://adventofcode.com/2022/day/5
 */
fun main() {
    partOne()
    partTwo()
}

fun partOne() {
    val setStack = mapOf(
        1 to Stack<Char>().apply { addAll(0, listOf('T', 'P', 'Z','C','S','L','Q','N')) },
        2 to Stack<Char>().apply { addAll(0, listOf('L','P','T','V','H','C','G')) },
        3 to Stack<Char>().apply { addAll(0, listOf('D','C','Z','F')) },
        4 to Stack<Char>().apply { addAll(0, listOf('G','W','T','D','L','M','V','C')) },
        5 to Stack<Char>().apply { addAll(0, listOf('P','W','C')) },
        6 to Stack<Char>().apply { addAll(0, listOf('P','F','J','D','C','T','S','Z')) },
        7 to Stack<Char>().apply { addAll(0, listOf('V','W','G','B','D')) },
        8 to Stack<Char>().apply { addAll(0, listOf('N','J','S','Q','H','W')) },
        9 to Stack<Char>().apply { addAll(0, listOf('R','C','Q','F','S','L','V')) }
    )
    val input = File("src/main/kotlin/day5/input.txt")
    val firstLineIndex = 10
    input.useLines { it.toList() }.forEachIndexed { index, s ->
        if (index < firstLineIndex) {
            return@forEachIndexed
        }
        val delimiter1 = "move"
        val delimiter2 = "from"
        val delimiter3 = "to"
        val values = s.replace(" ", "").split(delimiter1, delimiter2, delimiter3, ignoreCase = true).toMutableList()
        values.removeFirst()
        val quantity = values[0].toInt()
        val fromStack = values[1].toInt()
        val destinationStack = values[2].toInt()
        for (i in 1..quantity) {
            val pop = try {
                setStack[fromStack]?.pop()
            } catch (ex: Throwable) {
                println("Error at row: $s")
                ex.printStackTrace()
                continue
            }
            setStack[destinationStack]?.push(pop)
        }
    }
    val result = setStack.map { it.value.peek() }
    println("Result part one: ${String(result.toCharArray())}")
}

fun partTwo() {
    val setStack = mapOf(
        1 to Stack<Char>().apply { addAll(0, listOf('T', 'P', 'Z','C','S','L','Q','N')) },
        2 to Stack<Char>().apply { addAll(0, listOf('L','P','T','V','H','C','G')) },
        3 to Stack<Char>().apply { addAll(0, listOf('D','C','Z','F')) },
        4 to Stack<Char>().apply { addAll(0, listOf('G','W','T','D','L','M','V','C')) },
        5 to Stack<Char>().apply { addAll(0, listOf('P','W','C')) },
        6 to Stack<Char>().apply { addAll(0, listOf('P','F','J','D','C','T','S','Z')) },
        7 to Stack<Char>().apply { addAll(0, listOf('V','W','G','B','D')) },
        8 to Stack<Char>().apply { addAll(0, listOf('N','J','S','Q','H','W')) },
        9 to Stack<Char>().apply { addAll(0, listOf('R','C','Q','F','S','L','V')) }
    )
    val input = File("src/main/kotlin/day5/input.txt")
    val firstLineIndex = 10

    input.useLines { it.toList() }.forEachIndexed { index, s ->
        if (index < firstLineIndex) {
            return@forEachIndexed
        }
        //TODO
    }
}
