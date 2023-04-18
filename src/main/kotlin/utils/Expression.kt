package utils

data class Expression(
    private val expString: String,
    private val expFunction: (Double) -> Double
){
    fun getExpString() = expString

    fun getExpFunction() = expFunction
}