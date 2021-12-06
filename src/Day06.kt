import kotlin.math.max

val FISH_CYCLE_DAYS = 7
val YOUNGSTER_START_COUNT = 8

fun main() {

    fun part1(input: List<String>, iterationCount: Int): Int {
        var fishDays = input[0].split(",").map { it.toInt() }

        for (i in 1..iterationCount) {
            val resetCount = fishDays.count { it == 0 }
            val nextFishDays = fishDays.map(fun (element: Int): Int {
                val nextValue = element - 1
                if (nextValue < 0) {
                    return FISH_CYCLE_DAYS - 1
                }
                return nextValue
            })
            fishDays = nextFishDays + MutableList(resetCount) { _ -> YOUNGSTER_START_COUNT}
        }
        return fishDays.size
    }

    /*
     Naturally, they're asking us to be smarter this time around :)
     */
    fun part2(input: List<String>, iterationCount: Int): Long {
        var fishDays = input[0].split(",").map { it.toInt() }
        val fishDaysMap = fishDays
            .groupingBy { it }
            .eachCount()
            .map { it.key to it.value.toLong() }
            .toMap()
            .toMutableMap()

        for (i in 1..iterationCount) {
            val zeroCount = fishDaysMap.getOrDefault(0, 0)
            for (day in 0.rangeTo(YOUNGSTER_START_COUNT - 1)) {
                fishDaysMap[day] = fishDaysMap.getOrDefault(day + 1, 0)
            }
            val maxDayCount = fishDaysMap.getOrDefault(FISH_CYCLE_DAYS - 1, 0)
            fishDaysMap[FISH_CYCLE_DAYS - 1] = maxDayCount + zeroCount
            fishDaysMap[YOUNGSTER_START_COUNT] = zeroCount
        }
        return fishDaysMap.map { it.value }.sum()
    }

    val input = readInput("Day06")
    println(part1(input, 80))
    println(part2(input, 256))
}
