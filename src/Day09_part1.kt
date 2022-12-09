import org.assertj.core.api.Assertions.assertThat

fun main() {
    val startPos = 400
//    val startPos = 1

    open class Head(
        val signature: Char = 'H',
        var positionX: Int = startPos,
        var positionY: Int = startPos,
        var prevPositionX: Int = startPos,
        var prevPositionY: Int = startPos
    )

    class Tail(signature: Char, positionX: Int, positionY: Int, prevPositionX: Int, prevPositionY: Int) :
        Head(signature, positionX, positionY, prevPositionX, prevPositionY)

    data class Grid(
        private val input: List<String>,
    ) {
        val head = Head()
        val tail = Tail('T', head.positionX, head.positionY, head.prevPositionX, head.prevPositionY)
        val grid = mutableListOf<MutableList<Char>>()
        val tailTrail = mutableSetOf<String>()

        init {
            repeat(startPos * 2) { grid.add(".".repeat(startPos * 2).toMutableList()) }
        }

        fun moveHead() {
            input.forEach { line ->
                println("== $line ==")
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
                    moveTail()
                    updateGrid()
                }
            }
        }

        fun tailMoves() = tailTrail.size

        private fun moveTail() {
            when {
                head.positionX > tail.positionX + 1 || head.positionY > tail.positionY + 1 -> {
                    tail.prevPositionX = tail.positionX
                    tail.prevPositionY = tail.positionY
                    tail.positionX = head.prevPositionX
                    tail.positionY = head.prevPositionY
                    tailTrail.add("${tail.positionX}-${tail.positionY}")
                }

                head.positionX < tail.positionX - 1 || head.positionY < tail.positionY - 1 -> {
                    tail.prevPositionX = tail.positionX
                    tail.prevPositionY = tail.positionY
                    tail.positionX = head.prevPositionX
                    tail.positionY = head.prevPositionY
                    tailTrail.add("${tail.positionX}-${tail.positionY}")
                }

                else -> return
            }
        }

        private fun updateGrid() {
            if (grid[tail.prevPositionY][tail.prevPositionX] == tail.signature) {
                grid[tail.prevPositionY][tail.prevPositionX] = '.'
            }
            if (grid[head.prevPositionY][head.prevPositionX] != tail.signature) {
                grid[head.prevPositionY][head.prevPositionX] = '.'
            }
            if (grid.size <= head.positionY) grid.add(mutableListOf('.'))
            while (grid[head.positionY].size <= head.positionX) grid[head.positionY].add('.')
            grid[tail.positionY][tail.positionX] = tail.signature
            grid[head.positionY][head.positionX] = head.signature
            if (grid[startPos][startPos] == '.') grid[startPos][startPos] = 's'
//            drawGrid()
        }

        private fun drawGrid() {
            grid.reversed().forEach {
                print(it.joinToString(""))
                println()
            }
            println()
        }

    }

    fun part1(input: List<String>): Int =
        Grid(input)
            .apply { moveHead() }
            .run { tailMoves() }

    fun part2(input: List<String>): Int =
        input
            .map { it }.size


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    assertThat(part1(testInput)).isEqualTo(12)
//    assertThat(part2(testInput)).isEqualTo(24933642)

    // print the puzzle answer
    val input = readInput("Day09")
    println(part1(input))
//    println(part2(input))
}