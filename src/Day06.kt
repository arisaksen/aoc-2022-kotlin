import org.assertj.core.api.Assertions.assertThat

fun main() {

    fun part1(input: String): String {

        return "1"
    }

    fun part2(input: String): String {

        return "1"
    }


// test if implementation meets criteria from the description, like:
    val testInput = readText("Day06_test")
    assertThat(part1(testInput)).isEqualTo("CMZ")
    assertThat(part2(testInput)).isEqualTo("MCD")

// print the puzzle answer
    val input = readText("Day06")
    println(part1(input))
    println(part2(input))
}
