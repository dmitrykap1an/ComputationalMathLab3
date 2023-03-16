package methods

object TrapezoidMethod:  Method{
    override fun solve(f: (Double) -> Double, interval: Pair<Double, Double>, n: Int) {
        val (a, b) = interval
        val h = (b - a) / n
        var result = (f(a) + f(b)) / 2
        var x = a

        repeat(n - 1){
            result+= f(x + h)
            x+=h
        }

        println(result * h)
    }
}