package utils

import methods.RectangleMethod
import methods.SimpsonsMethod
import methods.TrapezoidMethod
import java.io.*
import java.time.LocalDateTime
import kotlin.math.*

object CLI {

    private val mapOfMethods = mapOf(
        1 to "Метод прямоугольников",
        2 to "Метод трапеций",
        3 to "Метод Симпсона"
    )
    private val kindsOfRectangleMethods = mapOf(
        1 to "Метод левых",
        2 to "Метод средних",
        3 to "Метод правых"
    )
    private val expressions = mapOf(
        1 to Expression("x^2 dx") { x: Double -> x * x },
        2 to Expression("x^3 + 2x^2 - 3x - 12"){x: Double -> x*x*x + 2*x*x - 3*x - 12},
        3 to Expression("2x^3 - 3x^2 - 5x + 27"){x: Double -> 2*x*x*x - 3*x*x - 5*x + 27}
    )

    lateinit var input: () -> String
    private val br = BufferedReader(FileReader("src/files/task1/task1.txt"))
    private var visible = true

    fun doMethod(){
        askInputOption()
        val index = askMethod()
        val exp = askExpression()
        val interval = askInterval()
        val accuracy = askAccuracy()
        when(index.first){
            1 ->{
                when(index.second){
                    1 -> RectangleMethod.methodOfTheLeft.solve(exp, interval, accuracy)
                    2 -> RectangleMethod.methodOfAverages.solve(exp, interval, accuracy)
                    3 -> RectangleMethod.methodOfTheRight.solve(exp, interval, accuracy)
                }
            }

            2 -> TrapezoidMethod.solve(exp, interval, accuracy)

            3 -> SimpsonsMethod.solve(exp, interval, accuracy)
        }

    }

    private fun askInputOption() {
        print("Прочитать данные из файла? Д/н ")
        val str = readln()
        when (str.lowercase()) {
            "д", "\n", "l" -> {
                visible = false
                input ={ br.readLine() }
            }

            else -> {
                visible = true
                input = { readln() }
            }
        }
    }

    private fun askMethod(): Pair<Int, Int?>{
        mapOfMethods.forEach{
            ask("${it.key}) ${it.value}\n")
        }
        while (true){
            ask("Введите подходящий метод для нахождения интеграла: ")
            val index = input().toIntOrNull()
            if(index != null && index > 0 && index <= mapOfMethods.size) {
                return if(index == 1){
                    index to askKindOfRectangleMethod()
                } else index to null

            }
            ask("Номер метода должен быть представлен числом и быть в пределах [1; ${mapOfMethods.size}]")
        }

    }

    private fun askKindOfRectangleMethod(): Int{
        kindsOfRectangleMethods.forEach{
            ask("${it.key}) ${it.value}\n")
        }
        while (true){
            ask("Введите номер вида метода прямоугольника: ")
            val index = input().toIntOrNull()
            if(index != null && index > 0 && index <= kindsOfRectangleMethods.size) {
                return index
            }
            ask("Номер метода должен быть представлен числом и быть в пределах [1; ${kindsOfRectangleMethods.size}]")
        }
    }

    private fun askExpression(): Expression{
        expressions.forEach{
            ask("${it.key}) ${it.value.getExpString()}\n")
        }
        while (true){
            ask("Введите номер интеграла, который надо найти: ")
            val index = input().toIntOrNull()
            if(index != null && index > 0 && index <= expressions.size) {
                return expressions[index]!!
            }
            ask("Номер метода должен быть представлен числом и быть в пределах [1; ${expressions.size}]\n")
        }
    }

    private fun askInterval(): Pair<Double, Double>{
        while (true) {
            try {
                ask("Введите пределы интегрирования через пробел: ")
                val (a, b) = input().split(" ").map { it.toDoubleOrNull() }
                if (a is Double && b is Double) return Pair(min(a, b), max(a, b))
            } catch (e: IndexOutOfBoundsException) {
                ask("Введите через пробел два числа!\n")
            }

        }
    }


    private fun askAccuracy(): Double {
        ask("Введите точность: ")
        return input().toDouble()
    }

    fun ask(text: String) {
        if (visible) print(text)
    }

    fun createFileAndWriteResult(): BufferedWriter {
        val date = LocalDateTime.now()
        val file = File(
            "/home/newton/IdeaProjects/Math/comp_math/lab3/src/files/results/" +
                    "${date.dayOfMonth}_${date.month}_${date.hour}:${date.minute}.${date.second}"
        )

        return BufferedWriter(FileWriter(file))
    }

    fun getVisible() = visible


}