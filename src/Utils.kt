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

fun String.filterCommonCharsInStrings(second: String): List<Char> {
    val list = mutableSetOf<Char>()
    this.forEach {
        if (second.contains(it))
            list.add(it)
    }
    return list.toList()
}

fun List<String>.filterCommonCharsInList(): List<Char> =
    this.fold(this.first()) { acc, s ->
        acc.filterCommonCharsInStrings(s).toString()
    }.replace("[", "").replace("]", "").toList()
