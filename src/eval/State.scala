package eval

class State[T] {
  var map = Map[String, T]()

  def apply(id: String):T = map.get(id) match {
    case Some(value) => value
    case None => throw new EvaluationError(s"undefined id $id")
  }
  def bind(pair:(String, T)): Unit = map.get(pair._1) match {
    case Some(value) => throw new EvaluationError(s"redefining id ${pair._1}")
    case None => map += pair
  }

  def bind(names: List[String], values: List[T]): Unit = {
    if (names.size == values.size) 
      for (pair <- names.zip(values)) bind(pair)
    else     
     throw new EvaluationError(s"bindAll incompatible argument lengths: $names and $values")
  }

  def print(): Unit ={
    println(map)
  }
}
