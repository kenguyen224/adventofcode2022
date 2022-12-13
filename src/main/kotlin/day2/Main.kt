package day2

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

/**
 * Created by KeNV on 13/12/2022.
 * https://adventofcode.com/2022/day/2
 */

enum class Score(val value: Int) {
    WON(6),
    DRAW(3),
    LOST(0)
}

enum class GameStrategy(val point: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3)
}

fun GameStrategy.isWinner(other: GameStrategy) =
    when (this) {
        GameStrategy.ROCK -> other == GameStrategy.SCISSORS
        GameStrategy.SCISSORS -> other == GameStrategy.PAPER
        GameStrategy.PAPER -> other == GameStrategy.ROCK
    }

fun GameStrategy.isLoser(other: GameStrategy) =
    when (this) {
        GameStrategy.ROCK -> other == GameStrategy.PAPER
        GameStrategy.SCISSORS -> other == GameStrategy.ROCK
        GameStrategy.PAPER -> other == GameStrategy.SCISSORS
    }

fun GameStrategy.getScore(other: GameStrategy): Score =
    when {
        isWinner(other) -> Score.WON
        isLoser(other) -> Score.LOST
        else -> Score.DRAW
    }

fun GameStrategy.getLoser() =
    when (this) {
        GameStrategy.ROCK -> GameStrategy.SCISSORS
        GameStrategy.SCISSORS -> GameStrategy.PAPER
        GameStrategy.PAPER -> GameStrategy.ROCK
    }

fun GameStrategy.getWinner() =
    when (this) {
        GameStrategy.ROCK -> GameStrategy.PAPER
        GameStrategy.SCISSORS -> GameStrategy.ROCK
        GameStrategy.PAPER -> GameStrategy.SCISSORS
    }

fun Score.getExpectStrategy(other: GameStrategy): GameStrategy =
    when(this) {
        Score.DRAW -> other
        Score.LOST -> other.getLoser()
        Score.WON -> other.getWinner()
    }

val opponentGuideMap = mapOf("A" to GameStrategy.ROCK, "B" to GameStrategy.PAPER, "C" to GameStrategy.SCISSORS)

fun main() {
    partOne()
    partTwo()
}

fun partOne() {
    val mineGuideMap = mapOf("X" to GameStrategy.ROCK, "Y" to GameStrategy.PAPER, "Z" to GameStrategy.SCISSORS)
    var totalScore = 0
    val file = File("src/main/kotlin/day2/input.txt")
    try {
        BufferedReader(FileReader(file)).use { br ->
            do {
                val line: String? = br.readLine()
                if (line.isNullOrBlank()) {
                    continue
                }
                val splitLine = line.split(" ")
                val opponentChoose = opponentGuideMap[splitLine.first()] ?: continue
                val mineChoose = mineGuideMap[splitLine.last()] ?: continue
                totalScore += mineChoose.point + mineChoose.getScore(opponentChoose).value
            } while (line != null)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    println("Result part one is: $totalScore")
}

fun partTwo() {
    val mineExpectResult = mapOf("X" to Score.LOST, "Y" to Score.DRAW, "Z" to Score.WON)
    var totalScore = 0
    val file = File("src/main/kotlin/day2/input.txt")
    try {
        BufferedReader(FileReader(file)).use { br ->
            do {
                val line: String? = br.readLine()
                if (line.isNullOrBlank()) {
                    continue
                }
                val splitLine = line.split(" ")
                val opponentChoose = opponentGuideMap[splitLine.first()] ?: continue
                val mineScore = mineExpectResult[splitLine.last()] ?: continue
                totalScore += mineScore.value + mineScore.getExpectStrategy(opponentChoose).point
            } while (line != null)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    println("Result part two is: $totalScore")
}
