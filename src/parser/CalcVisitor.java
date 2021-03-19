// Generated from /Users/helderbetiol/Downloads/CalcANTLR-ofi/src/parser/Calc.g4 by ANTLR 4.9.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CalcParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CalcVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CalcParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CalcParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcParser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(CalcParser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcParser#head}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHead(CalcParser.HeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(CalcParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(CalcParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntLit}
	 * labeled alternative in {@link CalcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntLit(CalcParser.IntLitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarId}
	 * labeled alternative in {@link CalcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarId(CalcParser.VarIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnExp}
	 * labeled alternative in {@link CalcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnExp(CalcParser.UnExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinExp}
	 * labeled alternative in {@link CalcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinExp(CalcParser.BinExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CondExp}
	 * labeled alternative in {@link CalcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondExp(CalcParser.CondExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunCall}
	 * labeled alternative in {@link CalcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunCall(CalcParser.FunCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnExp2}
	 * labeled alternative in {@link CalcParser#tail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnExp2(CalcParser.UnExp2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code BinExp2}
	 * labeled alternative in {@link CalcParser#tail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinExp2(CalcParser.BinExp2Context ctx);
	/**
	 * Visit a parse tree produced by {@link CalcParser#variableId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableId(CalcParser.VariableIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcParser#functionId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionId(CalcParser.FunctionIdContext ctx);
}