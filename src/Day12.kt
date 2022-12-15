import org.assertj.core.api.Assertions.assertThat
//typealias Coordinates = Pair<Int, Int>

// https://adventofcode.com/2022/day/12
fun main() {


    fun part1(input: List<String>): Int = 1
    // Solved with graqphics at:
    // https://github.com/arisaksen/pathfinding-libgdx

    fun part2(input: List<String>): Int = 1
    // https://github.com/arisaksen/pathfinding-libgdx

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day12_test")
    assertThat(part1(testInput)).isEqualTo(31)
    assertThat(part2(testInput)).isEqualTo(29)

// print the puzzle answer
    val input = readInput("Day12")
    println(part1(input))
    println(part2(input))
}


