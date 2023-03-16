package methods

import utils.exceptions.OddNumberException

object SimpsonsMethod: Method {
    override fun solve(f: (Double) -> Double, interval: Pair<Double, Double>, n: Int) {
        if(n % 2 == 1) throw OddNumberException("Для нахождения интеграла методом Симпсона число n должно быть четное!")
        val (a, b) = interval
        val h = (b - a) / n
        var result = (f(a) + f(b))
        var x = a
        repeat(n - 1){
            var value =  2 * f(x + h)
            if(it % 2 == 0){
                value *= 2
            }

            result += value
            x += h

        }
        println(result * h / 3)

    }
}