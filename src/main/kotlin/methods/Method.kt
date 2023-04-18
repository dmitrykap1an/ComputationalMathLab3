package methods

import utils.CLI
import utils.Expression

interface Method{

    fun solve(f: Expression, interval: Pair<Double, Double>, accuracy: Double)

    fun printResult(result: Double, n: Int, exp: Expression){
        val answer = "${exp.getExpString()} = $result\n" + "Число разбиений: $n\n"
        if(CLI.getVisible()){
          CLI.ask(answer)
        } else{
            val bw = CLI.createFileAndWriteResult()
            bw.write(answer)
            bw.flush()
            bw.close()
        }
    }

    fun printError(message: String){
        if(CLI.getVisible()){
            CLI.ask(message)
        } else{
            val bw = CLI.createFileAndWriteResult()
            bw.write(message)
            bw.flush()
            bw.close()
        }
    }
}
