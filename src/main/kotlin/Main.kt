import methods.RectangleMethod
import methods.SimpsonsMethod
import methods.TrapezoidMethod

fun main(){
    val f = {x: Double -> x * x}
    val interval = 1.0 to 2.0
    SimpsonsMethod.solve(f, interval, 4)
}