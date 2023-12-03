package three

import java.io.File

fun main() {
    println("== DAY THREE ⚙️ ==")
    val partOneResult = partOne(File("input/day3.txt"))
    val partTwoResult = partTwo(File("input/day3.txt"))
    println("PART ONE: $partOneResult")
    println("PART TWO: $partTwoResult")
}

fun partOne(file: File): Int {
    val lines = ArrayList(file.readLines())
    var sum = 0
    for (i in 0 until lines.size){
        val line = lines[i]
        var hasFoundSymbol = false
        var number = ""
        for(j in 0 until line.length){
            val char = line[j]
            if(char.isDigit()){
                number += char
                if(!hasFoundSymbol){
                    hasFoundSymbol = checkCorners(lines, j, i)
                }
                if(j + 1 < line.length && !(line[j + 1].isDigit()) || j == line.length - 1){
                    if(hasFoundSymbol){
                        sum += number.toInt()
                    }
                    number = ""
                    hasFoundSymbol = false
                }
            }
        }
    }
    return sum
}

fun partTwo(file: File): Int {
    val lines = ArrayList(file.readLines())
    var sum = 0
    for (i in 0 until lines.size){
        val line = lines[i]
        for(j in 0 until line.length){
            val char = line[j]
            if(char == '*'){
                var num1 = ""
                var num2 = ""
                for(k in -1..1){
                    val lineIndex = i + k
                    if(lineIndex >= 0 && lineIndex < lines.size){
                        var indexes = Pair(-1, -1)
                        for(l in -1..1){
                            val charIndex = j + l
                            if(!(indexes.first <= charIndex && charIndex <= indexes.second)) {
                                indexes = getNumberAtIndex(lines[lineIndex], charIndex)
                                if(indexes.first != -1){
                                    val ctry = lines[lineIndex].substring(indexes.first, indexes.second)
                                    if (ctry != "") {
                                        if (num1 == "") {
                                            num1 = ctry
                                        } else {
                                            num2 = ctry
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if(num1 != "" && num2 != ""){
                    val ratio = num1.toInt()* num2.toInt()
                    sum += ratio
                }
            }
        }
    }
    return sum
}

fun checkCorners(lines: ArrayList<String>, x: Int, y: Int): Boolean {
    val width = lines[y].length
    val height = lines.size
    if (y > 0) {
        if (x > 0 && isSymbol(lines[y - 1][x - 1])) return true
        if (isSymbol(lines[y - 1][x])) return true
        if (x < width - 1 && isSymbol(lines[y - 1][x + 1])) return true
    }
    if (x > 0 && isSymbol(lines[y][x - 1])) return true
    if (x < width - 1 && isSymbol(lines[y][x + 1])) return true
    if (y < height - 1) {
        if (x > 0 && isSymbol(lines[y + 1][x - 1])) return true
        if (isSymbol(lines[y + 1][x])) return true
        if (x < width - 1 && isSymbol(lines[y + 1][x + 1])) return true
    }
    return false
}

fun isSymbol(char: Char): Boolean {
    return !char.isDigit() && char != '.'
}

fun getNumberAtIndex(input: String, index: Int): Pair<Int, Int> {
    val maxLength = input.length
    if (index < 0 || index >= maxLength || !input[index].isDigit()) {
        return Pair(-1, -1)
    }
    var startIndex = index
    var endIndex = index
    while (startIndex >= 0 && input[startIndex].isDigit()) {
        startIndex--
    }
    while (endIndex < maxLength && input[endIndex].isDigit()) {
        endIndex++
    }
    return Pair(startIndex + 1, endIndex)
}