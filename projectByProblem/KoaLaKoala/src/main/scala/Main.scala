

/*
1. Compruebo que la condicin de a + b <= n se cumpla, si no se cumple retorno "No",
 si se cumple, imprimo "yes" y sigo adelante.
2. Primero le doy prioridad a los gatos y de forma recursiva y utilizando artimetica
    modular para repetir los indices de los perros, le voy asignando el perro
    correspondiente.
3. Tengo que utilizar un estructura de datos que me ayude a representar eso, por ejemplo
  un diccionario.
4. Despues tengo que generar el dicionario de los perros de igual formar pero
    tomando en cuenta los de los gatos.
*/
object Main extends App {

  case class Animals(cats: Map[Int, List[Int]], dogs: Map[Int, List[Int]])

  def solution(n: Int, a: Int, b: Int): Option[Animals] = {
    if(!condition(n, a, b)) None
    else {

    }
  }

  def condition(n: Int, a: Int, b: Int): Boolean = a + b <= n

  def generateCatsObservation(n: Int, a: Int): Map[Int, List[Int]] = {

    def go(a: Int, catIndex: Int = 0, dogIndex: Int = 0, cats: Map[Int, List[Int]] = Map()): Map[Int, List[Int]] = {
      if(a == 0) cats
      else if(dogIndex >= n) go(a-1, 0, 0, cats)
      else if(catIndex <= n) go(a-1, 0, 0, cats)
      else ???
    }

  }


}
