import org.assertj.core.api.Assertions.assertThat
import java.util.LinkedList

fun main() {
    val moreThanTwoEqual = """^.*(.).*\1.*${'$'}""".toRegex()

    fun part1(input: String): Int {
        val limitedQue = LinkedList<Char>()
        val queSize = 4

        input.forEachIndexed { index, char ->
            limitedQue.addNew(char, queSize)
            if (limitedQue.size == queSize && limitedQue.joinToString("").contains(moreThanTwoEqual).not()) {
                println("true after: ${limitedQue.joinToString("")}($index)")
                return index + 1
            }
        }

        return 0
    }

    fun part2(input: String): Int {
        val limitedQue = LinkedList<Char>()
        val queSize = 14

        input.forEachIndexed { index, char ->
            limitedQue.addNew(char, queSize)
            if (limitedQue.size == queSize && limitedQue.joinToString("").contains(moreThanTwoEqual).not()) {
                println("true after: ${limitedQue.joinToString("")}($index)")
                return index + 1
            }
        }

        return 0
    }


// test if implementation meets criteria from the description, like:
    val testInput = readText("Day06_test")
    assertThat(part1(testInput)).isEqualTo(10)
    assertThat(part2(testInput)).isEqualTo(29)

// print the puzzle answer
    val input = readText("Day06")
    println(part1(input))
    println(part2(input))
}

fun LinkedList<Char>.addNew(char: Char, max: Int) {
    this.add(char)
    if (this.size > max) {
        this.removeFirst()
    }
}