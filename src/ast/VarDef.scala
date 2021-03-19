package ast

import eval.State

case class VarDef(id: VarId, e1: Exp) extends AST {
  def eval(s:State[Int], sf:State[FunDef]) : Unit = s.bind((id.value, e1.eval(s, sf)))

  override def gen(): String = ???
}


