package parser

import org.antlr.v4.runtime.{CharStream, LexerNoViableAltException}

object Error {
  var flag = false
  var msg = "Error"
}
class ReportingCalcLexer(input: CharStream) extends CalcLexer(input) {
  override def recover(e: LexerNoViableAltException) {
    Error.flag = true // report error
    super.recover(e)
  }
}