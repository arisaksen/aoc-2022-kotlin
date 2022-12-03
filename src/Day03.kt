import org.assertj.core.api.Assertions.assertThat

val items: List<Char> = ('a'..'z') + ('A'..'Z')
val priority: List<Int> = (1..26) + (27..52)
val itemPriority: Map<Char, Int> = items.zip(priority).toMap()

typealias Compartment1 = String
typealias Compartment2 = String
typealias Rucksack = Pair<Compartment1, Compartment2>

fun main() {

    fun part1(items: List<String>): Int {
        val rucksacks: List<Rucksack> =
            items.putItemsToRucksackCompartments()

        val commonItemsForBothCompartments = rucksacks.map { it.first.filterCharsInCommonWith(it.second) }
        val commonItemsSummed = commonItemsForBothCompartments
            .flatten()
            .sumOf { itemPriority[it] as Int }

        return commonItemsSummed
    }

    fun part2(items: List<String>): Int {
        val rucksacks: List<Rucksack> =
            items.putItemsToRucksackCompartments()

        val rucksacksGrouped = rucksacks
            .chunked(3)
            /** .also{ log.debug("") }    nice for logging  */
            .map { rucksack -> rucksack.map { it.first + it.second } }
            .map { it.filterCommonCharsInListItems() }

        val commonItemsSummed = rucksacksGrouped
            .flatten()
            .sumOf { itemPriority[it] as Int }

        return commonItemsSummed
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    assertThat(part1(testInput)).isEqualTo(157)
    assertThat(part2(testInput)).isEqualTo(70)
    /** "abccddef".toSet() intersect "cadeff".toSet())
     * output: [a, c, d, e, f]                                    */

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

fun String.cmp1(): String = this.substring(0, this.length / 2)
fun String.cmp2(): String = this.substring(this.length / 2)
fun List<String>.putItemsToRucksackCompartments(): List<Pair<Compartment1, Compartment2>> =
    this.map { it.cmp1() to it.cmp2() }
