package ast
import eval.State
import scala.collection.mutable.ListBuffer

case class FunDef(head: Head, body: Body) extends AST {
  def eval(args: List[Int], stFun: State[FunDef]): Int = {
    val stateInt = new State[Int]
    val names = new ListBuffer[String]
    for (varId <- head.listVarId) {
      names += varId.value
    }
    stateInt.bind(names.toList, args)
    body.eval(stateInt, stFun)
  }

  override def gen(): String = {
    var res = "("
    res += head.gen()
    res += body.gen()
    res += ")"
    res
  }
}
