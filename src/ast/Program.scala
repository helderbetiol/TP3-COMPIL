package ast

import eval.State

case class Program(listFunDef: List[FunDef], body: Body) extends AST{
  def eval(si:State[Int], sf:State[FunDef]): Int = {
    for (fd <- listFunDef) {
      sf.bind((fd.head.id.value,fd))
    }
//    println(listFunDef)
    body.eval(si, sf)
  }

  override def gen(): String = {
    "(module (func (export \"main\") (result i32)\n    "+body.exp.gen()+"\n    return)\n    )"
  }
}


