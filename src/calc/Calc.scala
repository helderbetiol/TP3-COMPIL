package calc

import java.io.{FileInputStream, FileWriter, InputStream}

import org.antlr.v4.runtime.{ANTLRInputStream, CommonTokenStream}
import parser.{ASTVisitor, CalcParser, Error, ErrorListener, ReportingCalcLexer}
import ast.{FunDef, Program, SyntaxError}
import eval.State

object Calc extends App {

  val atConsole = args.length == 0 || args(0).startsWith("-")
  val (is, filename) = if (atConsole) (System.in, None) else (new FileInputStream(args(0)), Some(args(0)))
  val verbose = args.length == 0 || args.contains("-v")
  if (args.contains("-i")) println(s"==> ${interpret(is)}")
  else compile(is, filename)

  def analyze(is: InputStream): Program = {
    val input = new ANTLRInputStream(is)
    val lexer = new ReportingCalcLexer(input)
    val tokens = new CommonTokenStream(lexer)
    val parser = new CalcParser(tokens)
    parser.removeErrorListeners()
    parser.addErrorListener(new ErrorListener)
    // produce the parse tree with the analyzer produced by ANTLR
    val tree = parser.program()
    if (verbose) println(tree.toStringTree(parser))
    if (Error.flag) throw new SyntaxError(Error.msg)
    // turn the parse tree into an AST
    val visitor = new ASTVisitor
    val program = visitor.visit(tree).asInstanceOf[Program]
    if (verbose) println(program)
    program
  }

  def interpret(is: InputStream): Int = {
    val program = analyze(is: InputStream)
    program.eval(new State[Int], new State[FunDef])
//    program.eval()
  }

  def compile(is: InputStream, inputFile: Option[String]) : Unit = {
    val program = analyze(is: InputStream)
    val code = program.gen()
    if (inputFile.isDefined) write(code, inputFile.get)
    else println(code)
  }

  // write code to .wat file associated to .calc file passed as argument,
  // returning .wat file relative filename
  def write(code: String, filename: String) = {
    val CFilename = filename.replaceFirst("\\.calc\\z", ".wat")
    if (verbose) System.out.println("writing .wat code to " + CFilename)
    val out = new FileWriter(CFilename)
    out.write(code)
    out.flush()
    out.close()
    CFilename
  }
}