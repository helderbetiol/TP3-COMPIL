package parser

import ast._
import parser.CalcParser.{BodyContext, IntLitContext, VarIdContext}

import scala.collection.convert.ImplicitConversionsToScala.`list asScalaBuffer`

class ASTVisitor extends  CalcBaseVisitor[AST] {
  override def visitIntLit(ctx: IntLitContext) = IntLit(Integer.parseInt(ctx.getText))
  override def visitVarId(ctx: VarIdContext) = {VarId(ctx.variableId().IDENTIFIER().getText)}
  override def visitFunctionId(ctx: CalcParser.FunctionIdContext): AST = FunId(ctx.IDENTIFIER().getText)

  override def visitUnExp(ctx: CalcParser.UnExpContext) = {
    if (ctx.tail.getText != ")") {
      // not - unary, it is - binary
      val exp = visit(ctx.tail).asInstanceOf[Exp]
      BinExp(OP.parseOp("-"), visit(ctx.expression).asInstanceOf[Exp], exp)
    } else {
      UnExp(visit(ctx.expression).asInstanceOf[Exp])
    }
  }
  override def visitBinExp(ctx: CalcParser.BinExpContext): AST = {
    val exp0 = visit(ctx.expression(0)).asInstanceOf[Exp]
    val exp1 = visit(ctx.expression(1)).asInstanceOf[Exp]
    BinExp(OP.parseOp(ctx.OP().getText), exp0, exp1)
  }
  // second expression for - binary
  override def visitBinExp2(ctx: CalcParser.BinExp2Context): AST = {
    visit(ctx.expression).asInstanceOf[Exp]
  }
  override def visitCondExp(ctx: CalcParser.CondExpContext): AST = {
    val exp0 = visit(ctx.expression(0)).asInstanceOf[Exp]
    val exp1 = visit(ctx.expression(1)).asInstanceOf[Exp]
    val exp2 = visit(ctx.expression(2)).asInstanceOf[Exp]
    CondExp(exp0, exp1, exp2)
  }

  override def visitVarDef(ctx: CalcParser.VarDefContext): AST = {
    VarDef(VarId(ctx.variableId().IDENTIFIER().getText), visit(ctx.expression).asInstanceOf[Exp])
  }
  override def visitFunCall(ctx: CalcParser.FunCallContext): AST = {
    val exps = for (exp <- ctx.expression()) yield visit(exp).asInstanceOf[Exp]
    FunCall(visit(ctx.functionId()).asInstanceOf[FunId], exps.toList)
  }
  override def visitFuncDef(ctx: CalcParser.FuncDefContext): AST = {
    FunDef(visit(ctx.head).asInstanceOf[Head], visit(ctx.body).asInstanceOf[Body])
  }

  override def visitHead(ctx: CalcParser.HeadContext): AST = {
    val varIds = for (varId <- ctx.variableId) yield VarId(varId.IDENTIFIER().getText)
    Head(visit(ctx.functionId).asInstanceOf[FunId], varIds.toList)
  }
  override def visitBody(ctx: BodyContext) = {
    // retrieve ASTs for definitions
    val defs = for (varDef <- ctx.varDef) yield visit(varDef).asInstanceOf[VarDef]
    // retrieve AST for expression
    val exp = visit(ctx.expression).asInstanceOf[Exp]
    // return AST for body
    Body(defs.toList, exp)
  }

  override def visitProgram(ctx: CalcParser.ProgramContext): AST = {
    val listFunDef = for (funDef <- ctx.funcDef) yield visit(funDef).asInstanceOf[FunDef]
    val body = visit(ctx.body()).asInstanceOf[Body]
    Program(listFunDef.toList, body)
  }
}

