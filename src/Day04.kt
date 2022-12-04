import org.assertj.core.api.Assertions.assertThat


fun main() {

    fun part1(items: List<String>): Int =
        items
            .map { it.split(",") }
            .map { it.map { range -> (range.substringBefore("-").toInt()..range.substringAfter("-").toInt()) } }
            .also { it.map { println(it) } }
            .filter { it.first().checkIfIntRangeContains(it.last()) }
            .size

    fun part2(items: List<String>): Int {

        return 1
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    assertThat(part1(testInput)).isEqualTo(2)
//    assertThat(part2(testInput)).isEqualTo(0)

    val input = readInput("Day04")
    println(part1(input))
//    println(part2(input))
}

fun IntRange.checkIfIntRangeContains(range: IntRange): Boolean =
    when ((this intersect range).size) {
        range.count() -> true
        this.count() -> true
        else -> false
    }