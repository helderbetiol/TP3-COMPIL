package ast

case class Head(id: FunId, listVarId: List[VarId]) extends AST {
  override def gen(): String = ???
}

