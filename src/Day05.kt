import org.assertj.core.api.Assertions.assertThat

fun main() {

    fun part1(input: String): String {
        val (stackData, moves) = input.split("\n\n")
        val stacks: List<ArrayDeque<String>> = stackData.toStacks()

        moves.lines().forEach {
            val number = it.split(" ")[1].toInt()
            val moveFrom = it.split(" ")[3].toInt() - 1
            val moveTo = it.split(" ").last().toInt() - 1

            for (i in 1..number) {
                val poped = stacks[moveFrom].pop()
                stacks[moveTo].push(poped)
            }
        }

        stacks.forEach {
            println("stack ${it.peek()}")
        }

        return stacks.map { it.peek()[1] }.joinToString("")

    }

    fun part2(input: String): String {
        val (stackData, moves) = input.split("\n\n")
        val stacks: List<ArrayDeque<String>> = stackData.toStacks()

        moves.lines().forEach {
            var numbers = """\d+\w""".toRegex()                  // hint: d1aagfgf     alt+enter matches '1a'
            var number = it.split(" ")[1].toInt()        // use raw string """ """ for regex
            val moveFrom = it.split(" ")[3].toInt() - 1
            val moveTo = it.split(" ").last().toInt() - 1

            val tmpStack = ArrayDeque<String>()
            println("move $number from ${moveFrom + 1} to ${moveTo + 1}")
            if (number == 1 && moveFrom == 6) {
                println()
            }
            println("stack ${moveFrom + 1}: ${stacks[moveFrom]}")
            println("stack ${moveTo + 1}: ${stacks[moveTo]}")

            for (i in number downTo 1) {
                tmpStack.push(stacks[moveFrom].pop())
                if (tmpStack.size == number) {
                    println()
                    while (tmpStack.size > 0) {
                        stacks[moveTo].push(tmpStack.pop())
                        number--
                    }
                    println("stack ${moveFrom + 1}: ${stacks[moveFrom]}")
                    println("stack ${moveTo + 1}: ${stacks[moveTo]}")
                }
            }
            repeat(20) { print(".") }
            println()
        }

        return stacks.map { it.peek()[1] }.joinToString("")
    }


// test if implementation meets criteria from the description, like:
    val testInput = readText("Day05_test")
    assertThat(part1(testInput)).isEqualTo("CMZ")
    assertThat(part2(testInput)).isEqualTo("MCD")

// print the puzzle answer
    val input = readText("Day05")
    println(part1(input))
    println(part2(input))
}

fun String.toStacks(): List<ArrayDeque<String>> {
    val cargo =
        this
            .lines()
            .dropLast(1)
            .reversed()
            .map { it.chunked(4) }

    val stacks = List(cargo.first().size) { ArrayDeque<String>() }

    cargo.map {
        it.forEachIndexed { index, element ->
            if (element.isNotBlank())
                stacks[index].add(element)
        }
    }

    return stacks
}

fun ArrayDeque<String>.push(element: String) = this.addLast(element)
fun ArrayDeque<String>.pop() = this.removeLast()
fun ArrayDeque<String>.peek() = this.last()
