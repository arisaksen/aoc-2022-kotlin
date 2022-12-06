import org.assertj.core.api.Assertions.assertThat

// https://adventofcode.com/2022/day/6
fun main() {
    val moreThanTwoEqual = """^.*(.).*\1.*${'$'}""".toRegex()
    val part1WindowSize = 4
    val part2WindowSize = 14

    fun part1(input: String): Int =
        input.asSequence()
            .windowed(part1WindowSize)
            .withIndex()
            .filterNot { it.value.joinToString("").contains(moreThanTwoEqual) }  // replaced with indexOfFirst in part2
            .map { it.index }
            .first() + part1WindowSize


    fun part2(input: String): Int =
        input
            .windowed(part2WindowSize) { it.toSet().count() == it.length }
            .indexOf(true) + part2WindowSize
    // Returns the index of the first occurrence of the specified element in the list, or -1 if the specified element is not contained in the list.
    //.windowedSequence(part2WindowSize) { it.toSet().count() == it.length }  // for large data sets. Benchmark: https://github.com/Kotlin/kotlinx-benchmark

// test if implementation meets criteria from the description, like:
    val testInput = readText("Day06_test")
    assertThat(part1(testInput)).isEqualTo(10)
    assertThat(part2(testInput)).isEqualTo(29)

// print the puzzle answer
    val input = readText("Day06")
    println(part1(input))
    println(part2(input))
}
