package ast

import eval.State
import scala.collection.mutable.ListBuffer

// OP
sealed trait Token
sealed trait OP extends Token
case class PLUS() extends OP
case class MINUS() extends OP
case class TIMES() extends OP
case class DIVIDE() extends OP
case class EQUAL() extends OP
case class LESS() extends OP
object OP {
  def parseOp(str: String): OP = {
    str match {
      case "==" =>  { EQUAL() }
      case "-"  => { MINUS() }
      case "+"  => { PLUS() }
      case "*"  => { TIMES() }
      case "/"  => { DIVIDE() }
      case "<"  => { LESS() }
    }
  }
}

// Exp
sealed trait Exp extends AST {
  def eval(s: State[Int], sf:State[FunDef]): Int
  def gen(): String
}
case class IntLit(value: Int) extends Exp {
  override def eval(s: State[Int], sf:State[FunDef]): Int = value

  override def gen(): String = "i32.const "+value
}
case class VarId(value: String) extends Exp {
  override def eval(s: State[Int], sf:State[FunDef]): Int = s(value)

  override def gen(): String = ???
}
case class FunId(value: String) extends Exp {
  override def eval(s: State[Int], sf:State[FunDef]): Int = {
    println("FUN ID EVAL ???")
    1
  }

  override def gen(): String = ???
}
case class UnExp(e1: Exp) extends Exp {
  override def eval(s: State[Int], sf:State[FunDef]): Int = {
    val val1 = e1.eval(s, sf)
    -val1
  }

  override def gen(): String = "i32.const 0 "+e1.gen()+" i32.sub"
}
case class BinExp(op: OP, e1: Exp, e2: Exp) extends Exp {
  override def eval(s: State[Int], sf:State[FunDef]): Int = {
    val val1 = e1.eval(s, sf)
    val val2 = e2.eval(s, sf)
    op match {
      case op: PLUS => val1 + val2
      case op: MINUS => val1 - val2
      case op: TIMES => val1 * val2
      case op: DIVIDE => val1 / val2
      case op: EQUAL => if (val1 == val2) 1 else 0
    }
  }

  override def gen(): String = {
    val val1 = e1.gen()
    val val2 = e2.gen()
    op match {
      case op: PLUS => val1+" "+val2+" i32.add"
      case op: MINUS => val1+" "+val2+" i32.sub"
      case op: TIMES => val1+" "+val2+" i32.mul"
      case op: DIVIDE => val1+" "+val2+" i32.div_u"
      case op: EQUAL => val1+" "+val2+" i32.eq"
    }
  }
}
case class FunCall(funId: FunId, exps: List[Exp]) extends Exp {
  override def eval(s: State[Int], sf:State[FunDef]): Int = {
    var args: ListBuffer[Int] = ListBuffer[Int]()
    for (exp <- exps) {
      args += exp.eval(s, sf)
    }
    sf(funId.value).eval(args.toList, sf)
  }

  override def gen(): String = ???
}
case class CondExp(e1: Exp, e2: Exp, e3: Exp) extends Exp {
  override def eval(s: State[Int], sf:State[FunDef]): Int = {
    val val1 = e1.eval(s, sf)
    if (val1 != 0)
      e2.eval(s, sf)
    else
      e3.eval(s, sf)
  }

  override def gen(): String = {
    // test text first
    val test = e1.gen()
    test+" if (result i32) "+e2.gen()+" else "+e3.gen()+" end"
  }
}