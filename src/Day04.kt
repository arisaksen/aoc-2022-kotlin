import org.assertj.core.api.Assertions.assertThat


// https://adventofcode.com/2022/day/4
fun main() {

    fun part1(items: List<String>): Int =
        items
            .map { it.split(",") }
            .map { it.map { range -> (range.substringBefore("-").toInt()..range.substringAfter("-").toInt()) } }
            .also { it.map { println(it) } }
            .filter { it.first() intRangeContains it.last() }   // Util.kt
            .size
//            .count { it.first() intRangeContains it.last() }  // tip: count replaces both .filter and size

    fun part2(items: List<String>): Int =
        items
            .map { it.split(",") }
            .map { it.map { range -> (range.substringBefore("-").toInt()..range.substringAfter("-").toInt()) } }
            .also { it.map { println(it) } }
            .map { it.first() intersect it.last() }
            .filter { it.isNotEmpty() }
            .size
//            .count { it.isNotEmpty() }  // tip: count replaces both .filter and size


// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    assertThat(part1(testInput)).isEqualTo(2)
    assertThat(part2(testInput)).isEqualTo(4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
