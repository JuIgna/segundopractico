package segundopractico;

import org.antlr.v4.runtime.tree.ParseTreeWalker;
import java.io.PrintWriter;

class AnalizadorSemantico extends segundopractico.compiladoresBaseListener {
    private TablaSimbolos tablaSimbolos;
    private PrintWriter escritorErrores;

    AnalizadorSemantico(PrintWriter escritorErrores) {
        this.tablaSimbolos = new TablaSimbolos(escritorErrores);
        this.escritorErrores = escritorErrores;
    }

    @Override
    public void enterDeclaracion(segundopractico.compiladoresParser.DeclaracionContext ctx) {
        String tipo = ctx.tipo().getText();
        String nombre = ctx.ID().getText();
        boolean estaInicializado = ctx.expresion() != null;
        tablaSimbolos.agregarSimbolo(nombre, tipo, estaInicializado);

        if (estaInicializado) {
            String tipoExpresion = getTipoExpresion(ctx.expresion());
            if (tipoExpresion == null) {
                tablaSimbolos.reportarError(
                        "Error semántico: Tipos de datos incompatibles en declaración de '" + nombre + "'.");
            } else if (!esTipoCompatible(tipo, tipoExpresion)) {
                tablaSimbolos.reportarError(
                        "Error semántico: Tipos de datos incompatibles en declaración de '" + nombre + "'.");
            }
        }
    }

    @Override
    public void enterParametros(segundopractico.compiladoresParser.ParametrosContext ctx) {
        for (segundopractico.compiladoresParser.ParametroContext param : ctx.parametro()) {
            String tipo = param.tipo().getText();
            String nombre = param.ID().getText();
            if (!tablaSimbolos.contieneSimbolo(nombre)) {
                tablaSimbolos.agregarSimbolo(nombre, tipo, true);
            }
        }
    }

    @Override
    public void enterAsignacion(segundopractico.compiladoresParser.AsignacionContext ctx) {
        String nombre = ctx.ID().getText();
        tablaSimbolos.verificarSimbolo(nombre);

        if (ctx.expresion() != null) {
            String tipoExpresion = getTipoExpresion(ctx.expresion());
            String tipoVar = tablaSimbolos.getTipoSimbolo(nombre);
            if (tipoExpresion == null) {
                tablaSimbolos.reportarError("Error semántico: Tipos de datos incompatibles en declaración de '" + nombre
                        + "' de tipo " + tipoVar);
            } else if (tipoVar != null && !esTipoCompatible(tipoVar, tipoExpresion)) {
                tablaSimbolos.reportarError("Error semántico: Tipos de datos incompatibles en asignación de '" + nombre
                        + "' de tipo " + tipoVar + " con expresión de tipo " + tipoExpresion);
            }
        }

        tablaSimbolos.inicializarSimbolo(nombre);
    }

    @Override
    public void enterExpresion(segundopractico.compiladoresParser.ExpresionContext ctx) {
        if (ctx.ID() != null) {
            String nombre = ctx.ID().getText();
            tablaSimbolos.verificarSimbolo(nombre);
        }
    }

    @Override
    public void exitPrograma(segundopractico.compiladoresParser.ProgramaContext ctx) {
        tablaSimbolos.imprimirSimbolos(escritorErrores);
    }

    private String getTipoExpresion(segundopractico.compiladoresParser.ExpresionContext ctx) {
        if (ctx.literal() != null) {
            if (ctx.literal().NUMERO() != null) {
                return "int";
            } else if (ctx.literal().DOUBLE_LITERAL() != null) {
                return "double";
            }
        } else if (ctx.ID() != null) {
            return tablaSimbolos.getTipoSimbolo(ctx.ID().getText());
        } else if (ctx.expresion().size() == 2) {
            String tipoIzquierda = getTipoExpresion(ctx.expresion(0));
            String tipoDerecha = getTipoExpresion(ctx.expresion(1));
            if (tipoIzquierda == null || tipoDerecha == null) {
                return null;
            }
            if (tipoIzquierda.equals("double") || tipoDerecha.equals("double")) {
                return "double";
            } else {
                return "int";
            }
        }
        return null;
    }

    private boolean esTipoCompatible(String tipoVar, String tipoExpresion) {
        if (tipoVar.equals(tipoExpresion)) {
            return true;
        }
        if (tipoVar.equals("double") && tipoExpresion != null
                && (tipoExpresion.equals("int") || tipoExpresion.equals("double"))) {
            return true;
        }
        return false;
    }

    public TablaSimbolos getTablaSimbolos() {
        return tablaSimbolos;
    }
}
