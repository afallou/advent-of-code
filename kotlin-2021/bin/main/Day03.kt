fun main() {
    /*
     Turn lines of bytes into columns
     */
    fun getByteColumns(input: List<String>, byteCount: Int): ArrayList<List<Char>> {
        var byteColumns = arrayListOf<List<Char>>()
//        Would there be a way to zip n lists instead?
        for (i in 0..byteCount - 1) {
            val byteColumn = input.fold(
                mutableListOf<Char>(),
                fun (agg: MutableList<Char>, element: String): MutableList<Char> {
                    agg.add(element[i])
                    return agg
                }
            )

            byteColumns.add(byteColumn.toList())
        }
        return byteColumns
    }

    fun part1(input: List<String>): Pair<Int, Int> {
        val byteCount = input[0].length

        val byteColumns = getByteColumns(input, byteCount)
        var maxByte = arrayListOf<Char>()
        var minByte = arrayListOf<Char>()

        for (byteColumn in byteColumns) {
            val countByByte = byteColumn.groupingBy { it }.eachCount()
            maxByte.add(countByByte.maxByOrNull { it.value }!!.key)
            minByte.add(countByByte.minByOrNull { it.value }!!.key)
        }


        return Pair(
            maxByte.joinToString("").toInt(2),
            minByte.joinToString("").toInt(2),
        )
    }

    val input = readInput("Day03")
    val (gamma, epsilon) = part1(input)
    println(gamma)
    println(epsilon)
    println(gamma * epsilon)
}