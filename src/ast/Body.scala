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

  override def gen(): String = {
    var res = "";
    var used:Map[VarId,Boolean] = Map()

    for (v <- listVarDef) {
      res += " (local $"+v.id.value+" i32) \n"
      if(used.contains(v.id)){
        throw new NoSuchElementException(v.id+" is defined after");
      }
      if(v.e1.isInstanceOf[VarId]) {
        used += (v.e1.asInstanceOf[VarId] -> true)
      }
    }

    for (v <- listVarDef) {
      res += v.e1.gen() + "\n"
      res += "local.set $"+v.id.value +"\n"
    }
    res += exp.gen()
    res
  }
}

