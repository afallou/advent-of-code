fun main() {
    fun part1(input: List<String>): Pair<Int, Int> {
        val y = 0
        val xCommands = input.filter { it.split(" ")[0] == "forward" }
        val yCommands = input.filter { it.split(" ")[0] != "forward" }

        val finalX = xCommands.map {it.split(" ")[1].toInt()}.sum()

        val finalY = yCommands.map(fun(item: String): Int {
            val command = item.split(" ")[0]
            val value = item.split(" ")[1].toInt()
            when (command) {
                "down" -> return value
                "up" -> return -1 * value
            }
            return 0
        }).sum()
        return Pair(finalX, finalY)
    }

    fun part2(input: List<String>): Pair<Int, Int> {
        var x = 0
        var y = 0
        var aim = 0

        for (item in input) {
            val (command, valueString) = item.split(" ")
            val value = valueString.toInt()
            when (command) {
                "down" -> aim += value
                "up" -> aim -= value
                "forward" -> {
                    x += value
                    y += aim * value
                }
            }
        }

        return Pair(x, y)
    }

    val (finalX1, finalY1) = part1(readInput("Day02"))
    println(finalX1 * finalY1)

    val (finalX2, finalY2) = part2(readInput("Day02"))
    println(finalX2 * finalY2)
}