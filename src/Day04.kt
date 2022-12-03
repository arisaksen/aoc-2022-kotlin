import org.assertj.core.api.Assertions.assertThat

fun main() {

    fun part1(items: List<String>): Int {

        return 1
    }

    fun part2(items: List<String>): Int {

        return 1
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    assertThat(part1(testInput)).isEqualTo(0)
    assertThat(part2(testInput)).isEqualTo(0)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
