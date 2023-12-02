package one

import java.io.File

fun main() {
    println("== DAY ONE 🎯 ==")
    val partOneResult = partOne(File("input/day1.txt"))
    val partTwoResult = partTwo(File("input/day1.txt"))
    println("PART ONE: $partOneResult")
    println("PART TWO: $partTwoResult")
}

fun partOne(lines: File): Int {
    var sum = 0
    lines.forEachLine { line ->
        var first = -1
        var second = -1
        line.forEach {char ->
            if(char.isDigit()){
                val digit = char.digitToInt()
                if(first == -1){
                    first = digit
                }
                second = digit
            }
        }
        sum += first * 10 + second
    }

    return sum
}

// 🤮
fun partTwo(lines: File): Int {
    var sum = 0
    lines.forEachLine { line ->
        var first: Int
        var firstIndexWord = 100000
        var firstIndexDigit = 100000
        val firstWordNums = listOf(line.indexOf("one"), line.indexOf("two"), line.indexOf("three"), line.indexOf("four"), line.indexOf("five"), line.indexOf("six"), line.indexOf("seven"), line.indexOf("eight"), line.indexOf("nine"))
        val firstDigitNums = listOf(line.indexOf("1"), line.indexOf("2"), line.indexOf("3"), line.indexOf("4"), line.indexOf("5"), line.indexOf("6"), line.indexOf("7"), line.indexOf("8"), line.indexOf("9"))
        firstWordNums.forEach{num ->
            if(num != -1 && firstIndexWord > num){
                firstIndexWord = num;
            }
        }
        firstDigitNums.forEach{num ->
            if(num != -1 && firstIndexDigit > num){
                firstIndexDigit = num;
            }
        }
        if(firstIndexDigit < firstIndexWord){
            first = firstDigitNums.indexOf(firstIndexDigit) + 1
        } else {
            first = firstWordNums.indexOf(firstIndexWord) + 1
        }
        var second: Int
        var lastIndexWord = -1
        var lastIndexDigit = -1
        var lastWordNums = listOf(line.lastIndexOf("one"), line.lastIndexOf("two"), line.lastIndexOf("three"), line.lastIndexOf("four"), line.lastIndexOf("five"), line.lastIndexOf("six"), line.lastIndexOf("seven"), line.lastIndexOf("eight"), line.lastIndexOf("nine"))
        var lastDigitNums = listOf(line.lastIndexOf("1"), line.lastIndexOf("2"), line.lastIndexOf("3"), line.lastIndexOf("4"), line.lastIndexOf("5"), line.lastIndexOf("6"), line.lastIndexOf("7"), line.lastIndexOf("8"), line.lastIndexOf("9"))
        lastWordNums.forEach{num ->
            if(num != -1 && lastIndexWord < num){
                lastIndexWord = num;
            }
        }
        lastDigitNums.forEach{num ->
            if(num != -1 && lastIndexDigit < num){
                lastIndexDigit = num;
            }
        }
        if(lastIndexDigit > lastIndexWord){
            second = lastDigitNums.indexOf(lastIndexDigit) + 1
        } else {
            second = lastWordNums.indexOf(lastIndexWord) + 1
        }

        sum += first * 10 + second
    }
    return sum
}