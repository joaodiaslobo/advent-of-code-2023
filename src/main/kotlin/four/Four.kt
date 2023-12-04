package four

import java.io.File

fun main() {
    println("== DAY FOUR 🪙 ==")
    val partOneResult = partOne(File("input/day4.txt"))
    val partTwoResult = partTwo(File("input/day4.txt"))
    println("PART ONE: $partOneResult")
    println("PART TWO: $partTwoResult")
}

fun partOne(lines: File): Int {
    var sum = 0
    lines.forEachLine { line ->
        var cardData = line.split(":").toTypedArray()[1]
        val winningNumbers = cardData.split(" | ").toTypedArray()[0].trim().replace("  ", " ").split(" ").map { it.toInt() }
        val numbers = cardData.split(" | ").toTypedArray()[1].trim().replace("  ", " ").split(" ").map { it.toInt() }
        var points = 0
        numbers.forEach { num ->
            if(num in winningNumbers){
                if(points == 0){
                    points = 1
                } else {
                    points *= 2
                }
            }
        }
        sum += points
    }

    return sum
}

fun partTwo(lines: File): Int {
    val instances = (List(198) { 1 }).toTypedArray()
    var currentID = 0
    lines.forEachLine { line ->
        val cardData = line.split(":").toTypedArray()[1]
        val winningNumbers = cardData.split(" | ").toTypedArray()[0].trim().replace("  ", " ").split(" ").map { it.toInt() }
        val numbers = cardData.split(" | ").toTypedArray()[1].trim().replace("  ", " ").split(" ").map { it.toInt() }
        var addedInstances = 1
        numbers.forEach { num ->
            if(num in winningNumbers){
                for(i in 1..instances[currentID]){
                    instances[currentID + addedInstances] += 1
                }
                addedInstances++
            }
        }
        currentID++
    }
    return instances.sum()
}