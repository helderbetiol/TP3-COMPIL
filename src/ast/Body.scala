package ast

import eval.State

case class Body(listVarDef: List[VarDef], exp: Exp) extends AST{
  def eval(s:State[Int], sf:State[FunDef]): Int = {
    // evaluate all vardefs first
    for (v <- listVarDef) {
      v.eval(s, sf)
    }
    // evaluate expression
    exp.eval(s, sf)
  }

  override def gen(): String = ???
}

