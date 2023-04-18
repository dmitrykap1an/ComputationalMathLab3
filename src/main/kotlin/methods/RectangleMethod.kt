package methods

import utils.Expression
import utils.exceptions.ImpossibleToFindAnswerException
import kotlin.math.abs

sealed class RectangleMethod{



    object methodOfTheRight: Method{

        override fun solve(exp: Expression, interval: Pair<Double, Double>, accuracy: Double) {
            try{
                val f = exp.getExpFunction()
                val n = 4
                val (result, newN) = right(f,interval, accuracy, n)
                printResult(result,  newN, exp)
            }
            catch (e: ImpossibleToFindAnswerException){
                printError(e.message!!)
            }

        }


        private fun getIntegral(f: (Double) -> Double, interval: Pair<Double, Double>, n: Int): Double {
            var sum = 0.0
            val (a, b) = interval
            val h = getH(a, b, n)
            var x = a + h
            repeat(n) {
                sum += f(x)
                x += h
            }
            return sum * h
        }

        private fun getH(a: Double, b: Double, n: Int) = (b - a) / n

        private fun right(f: (Double) -> Double, interval: Pair<Double, Double>, accuracy: Double, n: Int): Pair<Double, Int> {
            val I = getIntegral(f, interval, n * 2)
            val I2 = getIntegral(f, interval, n)
            if(n > 102400) throw ImpossibleToFindAnswerException("Невозможно найти ответ с заданой точностью точностью")
            if(abs(I - I2) /3 <= accuracy) return I to n
            return right(f, interval, accuracy, n * 2)
        }

    }

    object methodOfTheLeft: Method{

        override fun solve(exp: Expression, interval: Pair<Double, Double>, accuracy: Double) {
            try{
                val f = exp.getExpFunction()
                val n = 4;
                val (result, newN) = left(f, interval, accuracy, n)
                printResult(result, newN, exp)
            }
            catch (e: ImpossibleToFindAnswerException){
                printError(e.message!!)
            }

        }


        private fun getIntegral(f: (Double) -> Double, interval: Pair<Double, Double>, n: Int): Double {
            var sum = 0.0
            val (a, b) = interval
            var x = a;
            val h = getH(a, b, n)
            repeat(n) {
                sum += f(x)
                x += h
            }
            return sum * h
        }

        private fun getH(a: Double, b: Double, n: Int) = (b - a) / n

        private fun left(f: (Double) -> Double, interval: Pair<Double, Double>, accuracy: Double, n: Int): Pair<Double, Int> {
            val I = getIntegral(f, interval, n * 2)
            val I2 = getIntegral(f, interval, n)
            if(n > 102400) throw ImpossibleToFindAnswerException("Невозможно найти ответ с заданой точностью точностью")
            if(abs(I - I2) /3 <= accuracy) return I to n
            return left(f, interval, accuracy, n * 2)
        }


    }

    object methodOfAverages: Method{

        override fun solve(exp: Expression, interval: Pair<Double, Double>, accuracy: Double) {
            try{
                val f = exp.getExpFunction()
                val n = 4;
                val (result, newN) = average(f, interval, accuracy, n)
                printResult(result, newN, exp)
            }
            catch (e: ImpossibleToFindAnswerException){
                printError(e.message!!)
            }

        }

        private fun getIntegral(f: (Double) -> Double,interval: Pair<Double, Double>, n: Int): Double {
            val (a, b) = interval
            var x = a;
            val h = getH(a, b, n)
            var sum = 0.0
            repeat(n){
                sum += f(x + h/2)
                x+=h
            }
            return sum * h
        }

        private fun getH(a: Double, b: Double, n: Int) = (b - a) / n

        private fun average(f: (Double) -> Double, interval: Pair<Double, Double>, accuracy: Double, n: Int): Pair<Double, Int> {
            val I = getIntegral(f, interval, n * 2)
            val I2 = getIntegral(f, interval, n)
            if(n > 102400) throw ImpossibleToFindAnswerException("Невозможно найти ответ с заданой точностью точностью")
            if(abs(I - I2) /3 <= accuracy) return I to n
            return average(f, interval, accuracy, n * 2)
        }


    }

}