import org.assertj.core.api.Assertions.assertThat

// https://adventofcode.com/2022/day/11
fun main() {

    data class Test(
        var divisible: Long,
        val ifTestTrue: Int,
        val ifTestFalse: Int
    )

    data class Monkey(
        val startingItems: MutableList<Long>,
        val operation: List<String>,
        val test: Test,
    )

    data class Actions(
        private val input: String,
    ) {
        val monkeys = mutableMapOf<Int, Monkey>()
        var rounds: Int = 0
        var monkeyItemInspect = mutableMapOf<Int, Long>()

        fun monkeyStealItems() {
            input
                .split("\n\n")
                .map { it.split("\n") }
                .forEach {
                    val monkeyNumber = it[0][7].digitToInt()
                    val startingItems: MutableList<Long> =
                        it[1].substringAfterLast(":").split(", ")
                            .map { it.replace(" ", "").toLong() }.toMutableList()
                    monkeys.put(
                        monkeyNumber, Monkey(
                            operation = it[2].substringAfterLast("old ").split(" ").toMutableList(),
                            startingItems = startingItems,
                            test = Test(
                                divisible = it[3].split(" ").last().toLong(),
                                ifTestTrue = it[4].split(" ").last().toInt(),
                                ifTestFalse = it[5].split(" ").last().toInt()
                            )
                        )
                    )
                    monkeyItemInspect[monkeyNumber] = 0L
                }
        }

        fun divisibility(): Long = monkeys.values.map { it.test.divisible }.reduce(Long::times)
//       equivalent fun divisibility(): Long = monkeys.values.map { it.test.divisible }.reduce { acc: Long, elem: Long -> acc * elem }

        fun round(relief: (Long) -> Long) {
            rounds++
            monkeys.forEach { monkey ->
                monkey.value.startingItems.forEach { item ->
                    monkeyItemInspect[monkey.key] = monkeyItemInspect[monkey.key]!! + 1
                    var itemToThrow: Long = item
                    when (monkey.value.operation.first()) {
                        "*" -> {
                            if (monkey.value.operation.last() == "old") {
                                itemToThrow = relief(item * item)
                            } else {
                                itemToThrow = relief(item * monkey.value.operation.last().toLong())
                            }
                        }

                        "+" -> itemToThrow = relief(item + monkey.value.operation.last().toLong())
                        else -> error("check input for monkey ${monkey.key}, ${monkey.value.operation}")
                    }
//                    print("Round $rounds: Monkey ${monkey.key} throw $itemToThrow($item ${monkey.value.operation} / ${relief}) ")

                    if (itemToThrow % monkey.value.test.divisible == 0L) {
                        monkeys[monkey.value.test.ifTestTrue]?.startingItems?.add(itemToThrow)
//                        print("(is divisable by ${monkey.value.test.divisible})")
//                        print(" to Monkey${monkey.value.test.ifTestTrue}")
                    } else {
                        monkeys[monkey.value.test.ifTestFalse]?.startingItems?.add(itemToThrow)
//                        print("(not divisable by ${monkey.value.test.divisible})")
//                        print(" to Monkey${monkey.value.test.ifTestFalse}")
                    }
//                    println()
                }
                monkey.value.startingItems.clear()
            }
        }

        fun monkeyBussiness(): Long =
            monkeyItemInspect
                .map { it.value }
                .sortedDescending()
                .take(2)
                .fold(1L) { acc, item -> acc * item }

        fun printMonkeyBussiness(): Unit {
            if (rounds == 20 || rounds % 1000 == 0) {
                println("== After round ${rounds} ==")
                monkeyItemInspect.forEach {
                    println("Monkey ${it.key} ${it.value} times")
                }
            }
        }

    }

    fun part1(input: String): Long =
        Actions(input)
            .apply { monkeyStealItems() }
            .apply { repeat(20) { round() { Long -> Long / 3L } } }
            .run { monkeyBussiness() }

    fun part2(input: String): Long =
        Actions(input)
            .apply { monkeyStealItems() }
            .apply {
                repeat(10_000) {
                    round() { Long -> Long % divisibility() }
                    printMonkeyBussiness()
                }
            }
            .run { monkeyBussiness() }

// test if implementation meets criteria from the description, like:
    val testInput = readText("Day11_test")
    assertThat(part1(testInput)).isEqualTo(10605)
    assertThat(part2(testInput)).isEqualTo(2713310158)

// print the puzzle answer
    val input = readText("Day11")
    println(part1(input))
    println(part2(input))
}


