package ast

case class Head(id: FunId, listVarId: List[VarId]) extends AST {
  override def gen(): String = {
    var res = "func $"+id.value;
    for( x <- listVarId){
      res += " (param $"+x.value + " i32) "
    }
    res += " (result i32) "
    res
  }
}

