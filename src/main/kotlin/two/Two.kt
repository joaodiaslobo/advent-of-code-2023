package two

import java.io.File

fun main() {
    println("== DAY TWO 📦 ==")
    val partOneResult = partOne(File("input/day2.txt"))
    val partTwoResult = partTwo(File("input/day2.txt"))
    println("PART ONE: $partOneResult")
    println("PART TWO: $partTwoResult")
}

fun partOne(lines: File): Int {
    var sum = 0;
    var currentID = 1;
    lines.forEachLine { line ->
        val strings = line.split(":").toTypedArray()
        val cubeSubsets = strings[1].split(";")
        var isValid = true
        for (cubeSubset in cubeSubsets){
            var red = 0
            var green = 0
            var blue = 0
            val colorCubes = cubeSubset.split(",")
            for (color in colorCubes){
                if(color.contains("red")){
                    red += color.split(" ")[1].toInt()
                } else if(color.contains("green")){
                    green += color.split(" ")[1].toInt()
                } else {
                    blue += color.split(" ")[1].toInt()
                }
            }

            if(!(red <= 12 && green <= 13 && blue <= 14)){
                isValid = false
            }
        }
        if(isValid){
            sum += currentID
        }
        currentID++;
    }
    return sum
}

fun partTwo(lines: File): Int {
    var sum = 0;
    lines.forEachLine { line ->
        val strings = line.split(":").toTypedArray()
        val cubeSubsets = strings[1].split(";")
        var reds = intArrayOf()
        var greens = intArrayOf()
        var blues = intArrayOf()
        for (cubeSubset in cubeSubsets){
            var red = 0
            var green = 0
            var blue = 0
            val colorCubes = cubeSubset.split(",")
            for (color in colorCubes){
                if(color.contains("red")){
                    red += color.split(" ")[1].toInt()
                } else if(color.contains("green")){
                    green += color.split(" ")[1].toInt()
                } else {
                    blue += color.split(" ")[1].toInt()
                }
            }
            reds += red
            greens += green
            blues += blue
        }
        val power = reds.max() * greens.max() * blues.max()
        sum += power
    }
    return sum
}