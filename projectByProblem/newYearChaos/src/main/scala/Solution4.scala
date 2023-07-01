import scala.io.StdIn

object Result4 {

  /*
   * Complete the 'minimumBribes' function below.
   *
   * The function accepts INTEGER_ARRAY q as parameter.
   */

  /*
  creo que la solucion puede ser verlo de atras hacia adelante tene
  */

  def minimumBribes(q: Array[Int]): Any = {
    // Write your code here
    val qReverse: Array[Int] = q.reverse

    def go(qReverse: Array[Int], id: Int = q.size, bribes: Int = 0): Any = {
      if(qReverse.isEmpty){
        bribes
      }
      else {
        val count: Int = countBribesByPersonID(qReverse, id)

        if(count == -1){
          "Too chaotic"
        }
        else {
          go(qReverse.filter(_ != id), id-1, bribes+count)
        }
      }
    }

    go(qReverse)
  }

  /*
  Y si aqui voy guardando los elementos que voy descartando y despues que encuentre el id
  ademas del numero retorno lista nueva sin el id tomando lo que queda de qReverse y agregandole los descartados
  con + operator en ambas listar (creo que tengo que hacer un reverse en la segunda lista).
  */

  def countBribesByPersonID(qReverse: Array[Int], id: Int, count: Int = 0): Int = {
    if (count > 2) {
      -1
    }
    else if(qReverse.head >= id) {
      count
    }
    else {
      countBribesByPersonID(qReverse.tail, id, count + 1)
    }
  }

}

object Solution4 {
  def main(args: Array[String]) {
    val t = StdIn.readLine.trim.toInt

    for (tItr <- 1 to t) {
      val n = StdIn.readLine.trim.toInt

      val q = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
      println(Result4.minimumBribes(q))
    }
  }
}
