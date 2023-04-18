package utils

const val dx = 0.000001
fun derive(f: (Double) -> Double): (Double) -> Double{
    return { x: Double -> (f(x + dx) - f(x)) / dx}
}