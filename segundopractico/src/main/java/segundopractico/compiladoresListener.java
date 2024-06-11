// Generated from c:/Users/vazqu/Downloads/segundopractico/src/main/java/segundopractico/compiladores.g4 by ANTLR 4.13.1

package segundopractico;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link compiladoresParser}.
 */
public interface compiladoresListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(compiladoresParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(compiladoresParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion(compiladoresParser.InstruccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion(compiladoresParser.InstruccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#bloque}.
	 * @param ctx the parse tree
	 */
	void enterBloque(compiladoresParser.BloqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#bloque}.
	 * @param ctx the parse tree
	 */
	void exitBloque(compiladoresParser.BloqueContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#declaracionFuncion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracionFuncion(compiladoresParser.DeclaracionFuncionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#declaracionFuncion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracionFuncion(compiladoresParser.DeclaracionFuncionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#parametros}.
	 * @param ctx the parse tree
	 */
	void enterParametros(compiladoresParser.ParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#parametros}.
	 * @param ctx the parse tree
	 */
	void exitParametros(compiladoresParser.ParametrosContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParametro(compiladoresParser.ParametroContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParametro(compiladoresParser.ParametroContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(compiladoresParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(compiladoresParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion(compiladoresParser.DeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#declaracion}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion(compiladoresParser.DeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion(compiladoresParser.AsignacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion(compiladoresParser.AsignacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#estructuraControl}.
	 * @param ctx the parse tree
	 */
	void enterEstructuraControl(compiladoresParser.EstructuraControlContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#estructuraControl}.
	 * @param ctx the parse tree
	 */
	void exitEstructuraControl(compiladoresParser.EstructuraControlContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#ifElse}.
	 * @param ctx the parse tree
	 */
	void enterIfElse(compiladoresParser.IfElseContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#ifElse}.
	 * @param ctx the parse tree
	 */
	void exitIfElse(compiladoresParser.IfElseContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#elseIf}.
	 * @param ctx the parse tree
	 */
	void enterElseIf(compiladoresParser.ElseIfContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#elseIf}.
	 * @param ctx the parse tree
	 */
	void exitElseIf(compiladoresParser.ElseIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#whileLoop}.
	 * @param ctx the parse tree
	 */
	void enterWhileLoop(compiladoresParser.WhileLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#whileLoop}.
	 * @param ctx the parse tree
	 */
	void exitWhileLoop(compiladoresParser.WhileLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void enterForLoop(compiladoresParser.ForLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void exitForLoop(compiladoresParser.ForLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExpresion(compiladoresParser.ExpresionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExpresion(compiladoresParser.ExpresionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#op_aritmeticos}.
	 * @param ctx the parse tree
	 */
	void enterOp_aritmeticos(compiladoresParser.Op_aritmeticosContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#op_aritmeticos}.
	 * @param ctx the parse tree
	 */
	void exitOp_aritmeticos(compiladoresParser.Op_aritmeticosContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(compiladoresParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(compiladoresParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#op_logicas}.
	 * @param ctx the parse tree
	 */
	void enterOp_logicas(compiladoresParser.Op_logicasContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#op_logicas}.
	 * @param ctx the parse tree
	 */
	void exitOp_logicas(compiladoresParser.Op_logicasContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#llamadaFuncion}.
	 * @param ctx the parse tree
	 */
	void enterLlamadaFuncion(compiladoresParser.LlamadaFuncionContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#llamadaFuncion}.
	 * @param ctx the parse tree
	 */
	void exitLlamadaFuncion(compiladoresParser.LlamadaFuncionContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#llamadaPrints}.
	 * @param ctx the parse tree
	 */
	void enterLlamadaPrints(compiladoresParser.LlamadaPrintsContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#llamadaPrints}.
	 * @param ctx the parse tree
	 */
	void exitLlamadaPrints(compiladoresParser.LlamadaPrintsContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void enterArgumentos(compiladoresParser.ArgumentosContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#argumentos}.
	 * @param ctx the parse tree
	 */
	void exitArgumentos(compiladoresParser.ArgumentosContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#booleano}.
	 * @param ctx the parse tree
	 */
	void enterBooleano(compiladoresParser.BooleanoContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#booleano}.
	 * @param ctx the parse tree
	 */
	void exitBooleano(compiladoresParser.BooleanoContext ctx);
	/**
	 * Enter a parse tree produced by {@link compiladoresParser#incrementoDecremento}.
	 * @param ctx the parse tree
	 */
	void enterIncrementoDecremento(compiladoresParser.IncrementoDecrementoContext ctx);
	/**
	 * Exit a parse tree produced by {@link compiladoresParser#incrementoDecremento}.
	 * @param ctx the parse tree
	 */
	void exitIncrementoDecremento(compiladoresParser.IncrementoDecrementoContext ctx);
}