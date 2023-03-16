package methods

interface Method{

    fun solve(f: (Double) -> Double, interval: Pair<Double, Double>, n: Int)
}
