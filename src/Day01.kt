fun main() {
    fun part1(input: List<String>): Int {
        var lastItem = input[0].toInt()
        var counter = 0
        for (item in input.drop(1)) {
            if (item.toInt() - lastItem > 0){
                counter += 1
            }
            lastItem = item.toInt()
        }
        return counter
    }

    fun part2(input: List<String>): Int {
//        Let's go more functional for this one
        val inputInts = input.map { it.toInt() };

//        We could make this more flexible to allow different sliding window sizes.
        val sliding1 = inputInts.dropLast(2)
        val sliding2 = inputInts.drop(1).dropLast(1)
        val sliding3 = inputInts.drop(2)

        val sumOneTwo = sliding1.zip(sliding2) { val1, val2 -> val1 + val2}
        val sumOneTwoThree = sumOneTwo.zip(sliding3) { val12, val3 -> val12 + val3}

//        Casting back to string is dirty, but quicker here
        return part1(sumOneTwoThree.map { it.toString() })
    }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
