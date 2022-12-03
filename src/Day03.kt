import org.assertj.core.api.Assertions.assertThat

val items: List<Char> = ('a'..'z') + ('A'..'Z')
val priority: List<Int> = (1..26) + (27..52)
val itemPriority: Map<Char, Int> = items.zip(priority).toMap()

typealias Compartment1 = String
typealias Compartment2 = String
typealias Rucksack = Pair<Compartment1, Compartment2>
typealias Rucksacks = List<Rucksack>

// part2


fun main() {

    fun part1(items: List<String>): Int {
        val rucksacks: List<Rucksack> =
            items.putItemsToRucksackCompartments()

        val commonItemsForBothCompartments = rucksacks.map { it.filterCommonCharsInStringPair() }
        val commonItemsSummed = commonItemsForBothCompartments.flatten()
            .map { itemPriority[it] as Int }
            .sumOf { it }

        return commonItemsSummed
    }

    fun part2(items: List<String>): Int {

        return 1
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    assertThat(part1(testInput)).isEqualTo(157)
//    assertThat(part2(testInput)).isEqualTo(70)

    val input = readInput("Day03")
    println(part1(input))
//    println("totalscore: " + part1(input))
//    println("totalscore: " + part2(input))
}

fun String.cmp1(): String = this.substring(0, this.length / 2)
fun String.cmp2(): String = this.substring(this.length / 2)
fun List<String>.putItemsToRucksackCompartments(): List<Pair<Compartment1, Compartment2>> =
    this.map { it.cmp1() to it.cmp2() }

fun Pair<String, String>.filterCommonCharsInStringPair(): List<Char> {
    val list = mutableSetOf<Char>()
    this.first.forEach {
        if (this.second.contains(it)) {
            list.add(it)
        }
    }
    return list.toList()
}
//    this.joinToString { it.first + it.second }
