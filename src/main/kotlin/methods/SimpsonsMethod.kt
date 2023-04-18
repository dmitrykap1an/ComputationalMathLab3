package methods

import utils.Expression
import utils.exceptions.ImpossibleToFindAnswerException
import kotlin.math.abs


object SimpsonsMethod: Method {
    override fun solve(exp: Expression, interval: Pair<Double, Double>, accuracy: Double) {
        try{
            val f = exp.getExpFunction();
            val n = 4;
            val (result, newN) = simpson(f, interval, accuracy, n)
            printResult(result,  newN, exp)
        }
        catch (e: ImpossibleToFindAnswerException){
            printError(e.message!!)
        }


    }

    private fun getIntegral(f: (Double) -> Double, interval: Pair<Double, Double>, n: Int ): Double {
        val (a, b) = interval
        var result = (f(a) + f(b))
        val h = getH(a, b, n)
        var x = a
        repeat(n - 1){
            var value =  2 * f(x + h)
            if(it % 2 == 0){
                value *= 2
            }

            result += value
            x += h

        }
        return result * h/3
    }

    private fun getH(a: Double, b: Double, n: Int) = (b - a) / n

    private fun simpson(f: (Double) -> Double,  interval: Pair<Double, Double> , accuracy: Double, n: Int): Pair<Double, Int> {
        val I = getIntegral(f,  interval, n * 2)
        val I2 = getIntegral(f, interval, n)
        if(n > 102400) throw ImpossibleToFindAnswerException("Невозможно найти ответ с заданой точностью точностью")
        if(abs(I - I2)/15 <= accuracy) return I to n
        return simpson(f,  interval, accuracy, n * 2)
    }
}