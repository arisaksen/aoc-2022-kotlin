import org.assertj.core.api.Assertions.assertThat

fun main() {
//    val startPos = 400
    val startPos = 20

    class Head(
        val signature: Char = 'H',
        var positionX: Int = startPos,
        var positionY: Int = startPos,
        var prevPositionX: Int = startPos,
        var prevPositionY: Int = startPos
    )

    class Tail(
        val signature: Char,
        var positionX: Int = startPos,
        var positionY: Int = startPos,
        var prevPositionX: Int = startPos,
        var prevPositionY: Int = startPos
    )

    data class Grid(
        private val input: List<String>,
    ) {
        val head = Head()
        val tail1 = Tail('1')
        val tail2 = Tail('2')
        val tail3 = Tail('3')
        val tail4 = Tail('4')
        val tail5 = Tail('5')
        val tail6 = Tail('6')
        val tail7 = Tail('7')
        val tail8 = Tail('8')
        val tail9 = Tail('9')
        val grid = mutableListOf<MutableList<Char>>()
        val tailTrail9 = mutableSetOf<String>()
        var cachePrevDirection: String = "start"

        init {
            repeat(startPos * 2) { grid.add(".".repeat(startPos * 2).toMutableList()) }
        }

        fun moveHead() {
            input.forEach { line ->
                val direction = line.split(" ").first()
                val times = line.split(" ").last().toInt()
                repeat(times) { time ->
                    head.prevPositionX = head.positionX
                    head.prevPositionY = head.positionY
                    when (direction) {
                        "R" -> head.positionX++
                        "L" -> head.positionX--
                        "U" -> head.positionY++
                        "D" -> head.positionY--
                        else -> error("Check inputs: $line")
                    }
                    moveTails()
                    cachePrevDirection = direction
                    updateGrid()
                    countTailMoves(line)
                }
                println("== $line ==")
                drawGrid()
            }
        }

        fun tailMoves() = tailTrail9.size

        private fun countTailMoves(line: String) {
            if (tail9.positionX != tail9.prevPositionX) {
//                println("${tail9.positionX}-${tail9.positionY}")
//                drawGrid()
                grid.forEach {

                    if (it.find { char -> char == '9' } != null) {
                        tailTrail9.add("${tail9.positionX}-${tail9.positionY}")
                    }
                }
            }
        }

        private fun moveTails() {
            when {
                head.positionX > tail1.positionX + 1 || head.positionY > tail1.positionY + 1 -> {
                    tail9.prevPositionX = tail9.positionX
                    tail9.prevPositionY = tail9.positionY
                    tail9.positionX = tail8.prevPositionX
                    tail9.positionY = tail8.prevPositionY
                    tail8.prevPositionX = tail8.positionX
                    tail8.prevPositionY = tail8.positionY
                    tail8.positionX = tail7.prevPositionX
                    tail8.positionY = tail7.prevPositionY
                    tail7.prevPositionX = tail7.positionX
                    tail7.prevPositionY = tail7.positionY
                    tail7.positionX = tail6.prevPositionX
                    tail7.positionY = tail6.prevPositionY
                    tail6.prevPositionX = tail6.positionX
                    tail6.prevPositionY = tail6.positionY
                    tail6.positionX = tail5.prevPositionX
                    tail6.positionY = tail5.prevPositionY
                    tail5.prevPositionX = tail5.positionX
                    tail5.prevPositionY = tail5.positionY
                    tail5.positionX = tail4.prevPositionX
                    tail5.positionY = tail4.prevPositionY
                    tail4.prevPositionX = tail4.positionX
                    tail4.prevPositionY = tail4.positionY
                    tail4.positionX = tail3.prevPositionX
                    tail4.positionY = tail3.prevPositionY
                    tail3.prevPositionX = tail3.positionX
                    tail3.prevPositionY = tail3.positionY
                    tail3.positionX = tail2.prevPositionX
                    tail3.positionY = tail2.prevPositionY
                    tail2.prevPositionX = tail2.positionX
                    tail2.prevPositionY = tail2.positionY
                    tail2.positionX = tail1.prevPositionX
                    tail2.positionY = tail1.prevPositionY
                    tail1.prevPositionX = tail1.positionX
                    tail1.prevPositionY = tail1.positionY
                    tail1.positionX = head.prevPositionX
                    tail1.positionY = head.prevPositionY
//                    tail1Trail.add("${tail1.positionX}-${tail1.positionY}")
                }

                head.positionX < tail1.positionX - 1 || head.positionY < tail1.positionY - 1 -> {
                    tail9.prevPositionX = tail9.positionX
                    tail9.prevPositionY = tail9.positionY
                    tail9.positionX = tail8.prevPositionX
                    tail9.positionY = tail8.prevPositionY
                    tail8.prevPositionX = tail8.positionX
                    tail8.prevPositionY = tail8.positionY
                    tail8.positionX = tail7.prevPositionX
                    tail8.positionY = tail7.prevPositionY
                    tail7.prevPositionX = tail7.positionX
                    tail7.prevPositionY = tail7.positionY
                    tail7.positionX = tail6.prevPositionX
                    tail7.positionY = tail6.prevPositionY
                    tail6.prevPositionX = tail6.positionX
                    tail6.prevPositionY = tail6.positionY
                    tail6.positionX = tail5.prevPositionX
                    tail6.positionY = tail5.prevPositionY
                    tail5.prevPositionX = tail5.positionX
                    tail5.prevPositionY = tail5.positionY
                    tail5.positionX = tail4.prevPositionX
                    tail5.positionY = tail4.prevPositionY
                    tail4.prevPositionX = tail4.positionX
                    tail4.prevPositionY = tail4.positionY
                    tail4.positionX = tail3.prevPositionX
                    tail4.positionY = tail3.prevPositionY
                    tail3.prevPositionX = tail3.positionX
                    tail3.prevPositionY = tail3.positionY
                    tail3.positionX = tail2.prevPositionX
                    tail3.positionY = tail2.prevPositionY
                    tail2.prevPositionX = tail2.positionX
                    tail2.prevPositionY = tail2.positionY
                    tail2.positionX = tail1.prevPositionX
                    tail2.positionY = tail1.prevPositionY
                    tail1.prevPositionX = tail1.positionX
                    tail1.prevPositionY = tail1.positionY
                    tail1.positionX = head.prevPositionX
                    tail1.positionY = head.prevPositionY
//                    tail1Trail.add("${tail1.positionX}-${tail1.positionY}")
                }

                else -> return
            }
            tail2.positionX = tail1.prevPositionX
            tail2.positionY = tail1.prevPositionY
            tail3.positionX = tail2.prevPositionX
            tail3.positionY = tail2.prevPositionY
            tail4.positionX = tail3.prevPositionX
            tail4.positionY = tail3.prevPositionY
            tail5.positionX = tail4.prevPositionX
            tail5.positionY = tail4.prevPositionY
            tail6.positionX = tail5.prevPositionX
            tail6.positionY = tail5.prevPositionY
            tail7.positionX = tail6.prevPositionX
            tail7.positionY = tail6.prevPositionY
            tail8.positionX = tail7.prevPositionX
            tail8.positionY = tail7.prevPositionY
            tail9.positionX = tail8.prevPositionX
            tail9.positionY = tail8.prevPositionY
        }

        private fun updateGrid() {
            if (grid[tail1.prevPositionY][tail1.prevPositionX] == tail1.signature) {
                grid[tail1.prevPositionY][tail1.prevPositionX] = '.'
            }
            if (grid[head.prevPositionY][head.prevPositionX] != tail1.signature) {
                grid[head.prevPositionY][head.prevPositionX] = '.'
            }
            if (grid[tail2.prevPositionY][tail2.prevPositionX] == tail2.signature) {
                grid[tail2.prevPositionY][tail2.prevPositionX] = '.'
            }
            if (grid[tail3.prevPositionY][tail3.prevPositionX] == tail3.signature) {
                grid[tail3.prevPositionY][tail3.prevPositionX] = '.'
            }
            if (grid[tail4.prevPositionY][tail4.prevPositionX] == tail4.signature) {
                grid[tail4.prevPositionY][tail4.prevPositionX] = '.'
            }
            if (grid[tail5.prevPositionY][tail5.prevPositionX] == tail5.signature) {
                grid[tail5.prevPositionY][tail5.prevPositionX] = '.'
            }
            if (grid[tail6.prevPositionY][tail6.prevPositionX] == tail6.signature) {
                grid[tail6.prevPositionY][tail6.prevPositionX] = '.'
            }
            if (grid[tail7.prevPositionY][tail7.prevPositionX] == tail7.signature) {
                grid[tail7.prevPositionY][tail7.prevPositionX] = '.'
            }
            if (grid[tail8.prevPositionY][tail8.prevPositionX] == tail8.signature) {
                grid[tail8.prevPositionY][tail8.prevPositionX] = '.'
            }
            if (grid[tail9.prevPositionY][tail9.prevPositionX] == tail9.signature) {
                grid[tail9.prevPositionY][tail9.prevPositionX] = '.'
            }
            if (grid[tail1.prevPositionY][tail1.prevPositionX] != tail2.signature) {
                grid[tail1.prevPositionY][tail1.prevPositionX] = '.'
            }
            if (grid.size <= head.positionY) grid.add(mutableListOf('.'))
            while (grid[head.positionY].size <= head.positionX) grid[head.positionY].add('.')
            grid[tail9.positionY][tail9.positionX] = tail9.signature
            grid[tail8.positionY][tail8.positionX] = tail8.signature
            grid[tail7.positionY][tail7.positionX] = tail7.signature
            grid[tail6.positionY][tail6.positionX] = tail6.signature
            grid[tail5.positionY][tail5.positionX] = tail5.signature
            grid[tail4.positionY][tail4.positionX] = tail4.signature
            grid[tail3.positionY][tail3.positionX] = tail3.signature
            grid[tail2.positionY][tail2.positionX] = tail2.signature
            grid[tail1.positionY][tail1.positionX] = tail1.signature
            grid[head.positionY][head.positionX] = head.signature
            if (grid[startPos][startPos] == '.') grid[startPos][startPos] = 's'
        }

        private fun drawGrid() {
            grid.reversed().forEach {
                print(it.joinToString(""))
                println()
            }
            println()
        }
    }


    fun part2(input: List<String>): Int =
        Grid(input)
            .apply { moveHead() }
            .run { tailMoves() }


// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test2")
//    assertThat(part1(testInput)).isEqualTo(12)
    assertThat(part2(testInput)).isEqualTo(36)

// print the puzzle answer
    val input = readInput("Day09")
//    println(part1(input))
//    println(part2(input))
}