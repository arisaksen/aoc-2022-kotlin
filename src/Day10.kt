import org.assertj.core.api.Assertions.assertThat

fun main() {


    fun part1(input: List<String>): Int {
        var x = 1
        var currentCycle = 1
        var signalStrength = 0

        input.forEach { instruction ->
            currentCycle++
            if (currentCycle % 40 == 20) signalStrength += (currentCycle * x)

            if (instruction != "noop") {
                currentCycle++
                x += instruction.substringAfter(" ").toInt()
                if (currentCycle % 40 == 20) signalStrength += (currentCycle * x)
            }
        }
        return signalStrength
    }


    fun draw(currentCycle: Int, x: Int, screen: List<MutableList<Char>>) {
        val horizontalPos = (currentCycle - 1) % 40
        if (horizontalPos in x - 1..x + 1) {
            screen[(currentCycle - 1) / 40][horizontalPos] = '#'
        }
    }

    fun part2(input: List<String>): List<List<Char>> {
        var x = 1
        var currentCycle = 1
        val screen: List<MutableList<Char>> = List(6) { MutableList(40) { '.' } }

        input.forEach { instruction ->
            draw(currentCycle, x, screen)
            currentCycle++

            if (instruction != "noop") {
                draw(currentCycle, x, screen)
                currentCycle++
                x += instruction.substringAfter(" ").toInt()
            }
        }
        return screen
    }


// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    assertThat(part1(testInput)).isEqualTo(13140)
//    assertThat(part2(testInput)).isEqualTo(1)

// print the puzzle answer
    val input = readInput("Day10")
    println(part1(input))
    part2(input).forEach {
        println(it)
    }
}