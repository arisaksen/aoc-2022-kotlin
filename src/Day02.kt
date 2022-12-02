import Outcome.*
import org.assertj.core.api.Assertions.assertThat

enum class Opponent(val char: Char) { ROCK('A'), PAPER('B'), SCISSORS('C') }
enum class Response(val char: Char) { ROCK('X'), PAPER('Y'), SCISSORS('Z') }
enum class Outcome { LOSE, DRAW, WIN }

val responseScore: Map<Response, Int> = mapOf(Response.ROCK to 1, Response.PAPER to 2, Response.SCISSORS to 3)
val outcomeScore: Map<Outcome, Int> = mapOf(LOSE to 0, DRAW to 3, WIN to 6)

/** val (a, _, c) = "X Z"  Allow you to deconstruct string to char
 * operator fun String.component1() = this[0]
 * operator fun String.component2() = this[1]
 * operator fun String.component3() = this[1]
 * val (opponent, _, choice) = "X Z"
 * */

fun main() {

    fun part1(rounds: List<String>): Int {
        var totalScore: Int = 0
        rounds.forEach {
            totalScore += responseScore[response(it)] ?: 0
            val outcome = opponentChoice(it).outcome(response(it))
            totalScore += outcomeScore[outcome] ?: 0
            println("$it - ${opponentChoice(it)} ${response(it)} - ${responseScore[response(it)]} ${outcomeScore[outcome]} $outcome totalscore: $totalScore")
        }
        return totalScore
    }

    fun part2(rounds: List<String>): Int {
        var totalScore: Int = 0
        rounds.forEach {
            val outcome = response(it).fixedOutcome()
            val fixedResponse = fixResponse(opponentChoice(it), response(it).fixedOutcome())
            print("${it.last()} -> ${response(it).fixedOutcome()} - ${response(it)} -> $fixedResponse")
            println(" ====> $it - ${opponentChoice(it)} $fixedResponse - ${responseScore[fixedResponse]} ${outcomeScore[outcome]} $outcome totalscore: $totalScore")
            totalScore += responseScore[fixedResponse] ?: 0
            totalScore += outcomeScore[outcome] ?: 0
        }
        return totalScore
    }


// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    assertThat(part1(testInput)).isEqualTo(15)
    assertThat(part2(testInput)).isEqualTo(12)

    val input = readInput("Day02")
    println("totalscore: " + part1(input))
    println("totalscore: " + part2(input))
}

private fun Opponent.outcome(response: Response): Outcome =
    when (this) {
        Opponent.ROCK -> {
            when (response) {
                Response.ROCK -> DRAW
                Response.PAPER -> WIN
                Response.SCISSORS -> LOSE
            }
        }

        Opponent.PAPER -> {
            when (response) {
                Response.ROCK -> LOSE
                Response.PAPER -> DRAW
                Response.SCISSORS -> WIN
            }
        }

        Opponent.SCISSORS -> {
            when (response) {
                Response.ROCK -> WIN
                Response.PAPER -> LOSE
                Response.SCISSORS -> DRAW
            }
        }
    }

fun opponentChoice(input: String): Opponent =
    when (input.first()) {
        Opponent.ROCK.char -> Opponent.ROCK
        Opponent.PAPER.char -> Opponent.PAPER
        else -> Opponent.SCISSORS
    }

fun response(input: String): Response =
    when (input.last()) {
        Response.ROCK.char -> Response.ROCK
        Response.PAPER.char -> Response.PAPER
        else -> Response.SCISSORS
    }

// part2
fun Response.fixedOutcome(): Outcome =
    when (this) {
        Response.ROCK -> LOSE
        Response.PAPER -> DRAW
        Response.SCISSORS -> WIN
    }

fun fixResponse(opponent: Opponent, desiredOutcome: Outcome): Response =
    when (opponent) {
        Opponent.ROCK -> {
            when (desiredOutcome) {
                WIN -> Response.PAPER
                DRAW -> Response.ROCK
                LOSE -> Response.SCISSORS
            }
        }

        Opponent.PAPER -> {
            when (desiredOutcome) {
                WIN -> Response.SCISSORS
                DRAW -> Response.PAPER
                LOSE -> Response.ROCK
            }
        }

        Opponent.SCISSORS -> {
            when (desiredOutcome) {
                WIN -> Response.ROCK
                DRAW -> Response.SCISSORS
                LOSE -> Response.PAPER
            }
        }
    }
