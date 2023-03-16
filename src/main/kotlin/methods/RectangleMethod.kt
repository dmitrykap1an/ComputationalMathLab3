package methods


sealed class RectangleMethod{


    object methodOfTheRight: Method{

        override fun solve(f: (Double) -> Double, interval: Pair<Double, Double>, n : Int) {
            val (a, b) = interval
            val h = (b - a) / n
            var x = a + h
            var sum = 0.0

            repeat(n) {
                sum += f(x)
                x += h
            }
            val answer = sum * h
            println(answer)
        }
        
    }

    object methodOfTheLeft: Method{

        override fun solve(f: (Double) -> Double, interval: Pair<Double, Double>, n : Int){
            val (a, b) = interval
            var x = a
            var sum = 0.0
            val h = (b - a) / n

            repeat(n){
                sum += f(x)
                x+= h
            }

            val answer = sum * h
            println(answer)
        }

    }

    object methodOfAverages: Method{

        override fun solve(f: (Double) -> Double, interval: Pair<Double, Double>, n: Int){
            val (a, b) = interval
            var x = a
            val h = (b - a) / n
            var sum = 0.0

            repeat(n){
                sum += f(x + h/2)
                x+=h
            }

            val answer = sum * h
            println(answer)
        }

    }


}