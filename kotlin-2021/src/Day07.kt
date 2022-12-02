import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.roundToInt

fun main() {

    fun getIntegerMedian(items: List<Int>): Int {
        println(items.size)
        println(items)
        val sortedItems = items.sorted()
        if (items.size % 2 == 1) {
//            Odd-sized list, media is middle element
            return sortedItems[items.size / 2]
        }
//      Even-size list – median is average of middle two elements
        val median = 0.5 * (sortedItems[items.size / 2 - 1] + sortedItems[items.size / 2])
//        We need the closest integer position
        return median.roundToInt()
    }
    /*
     Moving to position x requires |currentPos - x| fuel
     We want to minimize sum{i}(|currentPos,i - x|) over x (L1 distance min)
     The mathematical solution is the median – however the median may not be an integer value.
     */
    fun part1(input: List<String>): Int {
        val crabPositions = input[0].split(",").map { it.toInt() }

        val optimalPosition = getIntegerMedian(crabPositions)

        println("Optimal position: $optimalPosition")
        return crabPositions.fold(0) { sum, position -> sum + abs(position - optimalPosition) }
    }

    /*
     Moving to position x requires sum(i=1..|currentPos - x|) i fuel
     sum(i=1..n) i  = (n * (n+1)) / 2
     Overall we want to minimize sum(nJ = currentPosJ) 1/2 * (|nJ - x| + 1) * |nJ - x|) = 1/2 * ((nJ - x)^2 + |nJ - x|))
     d/dx ->  sum(J) 1/2 * (2 * x - 2nJ + sign(nJ - x)) = sum(J) (x - nJ + sign(nJ - x)/2)
     */
    fun part2(input: List<String>): Int {
        val crabPositions = input[0].split(",").map { it.toInt() }

        val optimalPosition = getIntegerMedian(crabPositions)

        println("Optimal position: $optimalPosition")
        return crabPositions.fold(0) { sum, position -> sum + abs(position - optimalPosition) }
    }

    val input = readInput("Day07")
    println(part1(input))
}