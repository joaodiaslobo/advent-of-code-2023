import java.util.Scanner

fun main(args: Array<String>) {
    println("== 🎄 ADVENT OF CODE 2023 ❄️ ==")
    while(true){
        val reader = Scanner(System.`in`)
        println("> Pick a day to run: ")
        var day = reader.nextInt()
        runDay(day)
    }
}

fun runDay(day: Int) {
    when(day) {
        1 -> one.main()
        2 -> two.main()
        3 -> three.main()
        4 -> four.main()
        else -> println("Not implemented yet!")
    }
}