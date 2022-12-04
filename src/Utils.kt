import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/main/resources", "$name.txt")
    .readLines()

fun readText(name: String) = File("src/main/resources", "$name.txt")
    .readText()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun String.bagIntsFromString(delimiter: String): List<List<Int>> =
    this
        .split(delimiter)
        .map { it.lines() }
        .map { it.map { numberInGroup -> numberInGroup.toInt() } }

fun List<List<Int>>.sumTopBagElements(n: Int) =
    this
        .map { it.sum() }
        .sortedDescending()
        .take(n)
        .sum()

/** "abccddef".toSet() intersect "cadeff".toSet())
 * output: [a, c, d, e, f]                                    */
fun String.filterCharsInCommonWith(second: String): List<Char> =
    this.fold("") { accChars, char -> if (char in second) accChars.plus(char) else accChars }
        .toSet()
        .toList()

fun List<String>.filterCommonCharsInListItems(): List<Char> =
    this.fold(this.first()) { accChars, stringFromList ->
        accChars.filterCharsInCommonWith(stringFromList).toString()
    }.replace("[", "").replace("]", "").toList()

infix fun IntRange.intRangeContains(secondIntRange: IntRange): Boolean =
    when ((this intersect secondIntRange).size) {
        secondIntRange.count() -> true
        this.count() -> true
        else -> false
    }
