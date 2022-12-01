import org.assertj.core.api.Assertions.assertThat


fun main() {
    /** bag -> grouped data. Example see testInput */
    val bagDelimiter = "\n\n" // \n\n -> 'empty line'

    fun part1(input: String) =
        input
            .bagIntsFromString(bagDelimiter)
            .sumTopBagElements(1)

    fun part2(input: String) =
        input
            .bagIntsFromString(bagDelimiter)
            .sumTopBagElements(3)

// test if implementation meets criteria from the description, like:
    val testInput = readText("Day01_test")
    assertThat(part1(testInput)).isEqualTo(24000)
    assertThat(part2(testInput)).isEqualTo(45000)

// print the puzzle answer
    val puzzleInput = readText("Day01")
    println(part1(puzzleInput))
    println(part2(puzzleInput))
}
