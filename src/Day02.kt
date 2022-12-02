import Outcome.*
import org.assertj.core.api.Assertions.assertThat

enum class Apponent(val char: Char) { ROCK('A'), PAPER('B'), SCISSORS('C') }
enum class Response(val char: Char) { ROCK('X'), PAPER('Y'), SCISSORS('Z') }
enum class Outcome { LOSE, DRAW, WIN }

fun Apponent.outcome(response: Response): Outcome =
    if (this == Apponent.ROCK) {
        when (response) {
            Response.ROCK -> DRAW
            Response.PAPER -> WIN
            Response.SCISSORS -> LOSE
        }
    } else if (this == Apponent.PAPER) {
        when (response) {
            Response.ROCK -> LOSE
            Response.PAPER -> DRAW
            Response.SCISSORS -> WIN
        }
    } else {
        when (response) { // Apponent.SCISSORS
            Response.ROCK -> WIN
            Response.PAPER -> LOSE
            Response.SCISSORS -> DRAW
        }
    }


val responseScore: Map<Response, Int> = mapOf(Response.ROCK to 1, Response.PAPER to 2, Response.SCISSORS to 3)
val outcomeScore: Map<Outcome, Int> = mapOf(LOSE to 0, DRAW to 3, WIN to 6)
// total score is the sum of all rounds. (Apponent + Outcome)

fun choice(input: String): Apponent =
    when (input.first()) {
        Apponent.ROCK.char -> Apponent.ROCK
        Apponent.PAPER.char -> Apponent.PAPER
        else -> Apponent.SCISSORS
    }

fun response(input: String): Response =
    when (input.last()) {
        Response.ROCK.char -> Response.ROCK
        Response.PAPER.char -> Response.PAPER
        else -> Response.SCISSORS
    }

fun main() {

    fun part1(rounds: List<String>): Int {
        var totalScore: Int = 0
        rounds.forEach {
            totalScore += responseScore[response(it)] ?: 0
            val outcome = choice(it).outcome(response(it))
            totalScore += outcomeScore[outcome] ?: 0
            println("$it - ${choice(it)} ${response(it)} - ${responseScore[response(it)]} ${outcomeScore[outcome]} $outcome totalscore: $totalScore")
        }
        return totalScore
    }


// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    assertThat(part1(testInput)).isEqualTo(15)

    val input = readInput("Day02")
    println("totalscore:" + part1(input))

}
