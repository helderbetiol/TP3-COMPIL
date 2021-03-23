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
    var funs = ""
    for ( f <- listFunDef){
      funs += f.gen() + "\n"
    }
    s"""
      |(module
      |  ${funs}
      |  (func (export "main") (result i32)
      |    ${body.gen()}
      |    return
      |  )
      |)
    |""".stripMargin
  }
}


