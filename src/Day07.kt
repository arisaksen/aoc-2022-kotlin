import org.assertj.core.api.Assertions.assertThat

// https://adventofcode.com/2022/day/7
fun main() {

    open class File(var size: Long)
    class Dir : File(size = 0L) {
        val files = mutableListOf<File>()
        var parent: Dir? = null
        operator fun plusAssign(otherFile: File) {
            size += otherFile.size.also { if (otherFile is Dir) otherFile.parent = this }
            files += otherFile

            var parentDir: Dir? = parent
            while (parentDir != null) {
                parentDir.size += otherFile.size
                parentDir = parentDir.parent
            }
        }
    }

    data class Commands(
        private val input: List<String>,
        private val rootDir: Dir = Dir()
    ) {
        fun readCommands() {
            var current = rootDir
            input.forEachIndexed { index, line ->
                when {
                    line.startsWith("$ cd") -> current =
                        when (line.split(" ").last()) {
                            ".." -> current.parent as Dir
                            else -> {
                                val dir = Dir()
                                if (dir != current) current.plusAssign(dir)
                                dir
                            }
                        }

                    line.startsWith("$ ls") -> Unit
                    line.startsWith("dir") -> current.plusAssign(Dir())
                    line.first().isDigit() -> current.plusAssign(File(size = line.split(" ").first().toLong()))
                    else -> error("Error. Check your input $index: $line")
                }
            }
        }

        private fun getAllFiles(): List<File> =
            buildList {
                val tmpList = rootDir.files.toMutableList()
                while (tmpList.isNotEmpty()) {
                    val file = tmpList.removeFirst()
                    add(file)
                    if (file is Dir) file.files.forEach { tmpList.plusAssign(it) }
                }
            }

        fun getDirectoriesBySize(maxSize: Long): Long =
            getAllFiles()
                .filterIsInstance<Dir>()
                .filter { it.size <= maxSize }
                .sumOf { it.size }

        fun getSmallestFileToRemove(maxSize: Long, neededSpace: Long): Long =
            getAllFiles()
                .filterIsInstance<Dir>()
                .filter { it.size >= neededSpace - maxSize + rootDir.size }
                .minOf { it.size }
    }

    fun part1(input: List<String>): Long =
        Commands(input)
            .apply { readCommands() }
            .run { getDirectoriesBySize(100_000) }

    fun part2(input: List<String>): Long =
        Commands(input)
            .apply { readCommands() }
            .run { getSmallestFileToRemove(maxSize = 70_000_000L, neededSpace = 30_000_000L) }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    assertThat(part1(testInput)).isEqualTo(95437)
    assertThat(part2(testInput)).isEqualTo(24933642)

// print the puzzle answer
    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}

