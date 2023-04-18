package methods

import utils.Expression
import utils.exceptions.ImpossibleToFindAnswerException
import kotlin.math.abs

object TrapezoidMethod:  Method{
    override fun solve(exp: Expression, interval: Pair<Double, Double>, accuracy: Double) {
        try{
            val f = exp.getExpFunction()
            val n = 4;
            val (result, newN) = trapezoid(f, interval, accuracy, n)
            printResult(result, newN, exp)
        }
        catch (e: ImpossibleToFindAnswerException){
            printError(e.message!!)
        }


    }

    private fun getIntegral(f: (Double) -> Double, interval: Pair<Double, Double>, n: Int): Double {
        val (a, b) = interval
        var result = (f(a) + f(b)) / 2
        var x = a
        val h = getH(a, b, n)
        repeat(n - 1){
            result+= f(x + h)
            x+=h
        }

        return result * h
    }
    private fun getH(a: Double, b: Double, n: Int) = (b - a) / n
    private fun trapezoid(f: (Double) -> Double,  interval: Pair<Double, Double> , accuracy: Double, n: Int): Pair<Double, Int> {
        val I = getIntegral(f, interval, n * 2)
        val I2 = getIntegral(f, interval, n)
        if(n > 102400) throw ImpossibleToFindAnswerException("Невозможно найти ответ с заданой точностью точностью")
        if(abs(I - I2)/3 <= accuracy) return I to n
        return trapezoid(f,  interval, accuracy, n * 2)
    }

}