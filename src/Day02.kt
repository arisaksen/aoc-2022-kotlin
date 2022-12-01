import org.assertj.core.api.Assertions.assertThat

fun main() {
    val aocDay = "Day02"

    fun part1() {}
    fun part2() {}

// test if implementation meets criteria from the description, like:
    val testInput = readText("${aocDay}_test")
    assertThat(part1()).isEqualTo("")
    assertThat(part2()).isEqualTo(2)

// print the puzzle answer
    val puzzleInput = readText(aocDay)
    println(part1())
    println(part2())
}
