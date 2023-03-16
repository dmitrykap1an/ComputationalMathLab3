import methods.RectangleMethod

fun main(){
    val f = {x: Double -> x * x}
    val interval = 1.0 to 2.0
    RectangleMethod.methodOfTheLeft.solve(f, interval, 5)
}