import utils.CLI
import kotlin.math.abs

fun main(){
  CLI.doMethod();
}


val f = fun(x: Double) = x*x*x + 2*x*x - 3*x - 12
val isolation= 1.0 to 2.0
const val n = 10
val h = (isolation.second - isolation.first) / n
const val realResult = -97.0/12


fun simpson(){
    var (a, b) = isolation
    var sum = f(a) + f(b)
    println("f(x0) = ${f(a)}")
    println("f(xn) = ${f(b)}")
    a+=h
    for(i in 1 until n - 1){
        if(i % 2 == 1){
            sum+=4 * f(a)
        } else sum += 2 * f(a)
        println("f(x$i) = ${f(a)}")

        a+=h
    }
    println("f(xn) = ${f(b)}")
    println(sum * h /3)
    println("Погрешность Метода Симпсона: ${abs(sum * h/3 - realResult)/realResult}")
}
fun trapezoid(){
    var (a, b) = isolation
    var sum = (f(a) + f(b)) / 2
    a+=h;
    for(i in 1 until n){
        println("f(x$i) = ${f(a)}")
        sum+=f(a)
        a+=h
    }
    println(sum * h)

}
fun averageRectangle(){
    var(a,b) = isolation
    var sum = 0.0
    while(a <= b){
        println("f(xi - 1/2) = ${f(a + h/2)}")
        sum+=f(a + h/2)
        a+=h
    }
    println(sum)
    println(sum * h)

}

fun newtonKotes(){
    var (a,b) = isolation
    val step = (b - a)/5
    var sum = 0.0
    var i = 0
    while (a <= b){
        println("f(xi) = ${f(a)}")
        println("kotes = ${getKotesC5(i)}")
        println("xi = $a")
        sum+= ( f(a) * getKotesC5(i))
        println(i)
        i++
        a+=step
        println("-------------------------------")

    }
    println(sum)
}

fun getKotesC5(i: Int): Double {

    return when {
        (i == 0 || i == 5) -> 19.0/ 288
        (i == 1 || i == 4) -> 75.0 / 288
        (i == 2 || i == 3)  -> 50.0 /288
        else -> throw Exception()
    }
}