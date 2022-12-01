import org.assertj.core.api.Assertions.assertThat

/** bag -> grouped data. Example see testInput */
const val BAG_DELIMITER = "\n\n" // \n\n -> 'empty line'

fun main() {

    fun part1(input: String) =
        input
            .bagIntsFromString(BAG_DELIMITER)
            .sumTopBagElements(1)

    fun part2(input: String) =
        input
            .bagIntsFromString(BAG_DELIMITER)
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
