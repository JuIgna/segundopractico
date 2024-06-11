package segundopractico;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

class SymbolTable {
    private Map<String, Symbol> symbols = new HashMap<>();
    private PrintWriter errorWriter;

    SymbolTable(PrintWriter errorWriter) {
        this.errorWriter = errorWriter;
    }

    void addSymbol(String name, String type, boolean isInitialized) {
        if (symbols.containsKey(name)) {
            reportError("Error semántico: Identificador '" + name + "' ya ha sido declarado.");
        } else {
            symbols.put(name, new Symbol(name, type, isInitialized));
        }
    }

    boolean containsSymbol(String name) {
        return symbols.containsKey(name);
    }

    void checkSymbol(String name) {
        if (!symbols.containsKey(name)) {
            reportError("Error semántico: Identificador '" + name + "' no ha sido declarado.");
        } else if (!symbols.get(name).isInitialized()) {
            reportError("Error semántico: Identificador '" + name + "' no ha sido inicializado.");
        } else {
            symbols.get(name).setUsed(true);
        }
    }

    void initializeSymbol(String name) {
        if (symbols.containsKey(name)) {
            symbols.get(name).setInitialized(true);
        }
    }

    String getSymbolType(String name) {
        if (symbols.containsKey(name)) {
            return symbols.get(name).getType();
        }
        return null;
    }

    void printSymbols(PrintWriter writer) {
        for (Map.Entry<String, Symbol> entry : symbols.entrySet()) {
            if (!entry.getValue().isUsed()) {
                writer.println("Advertencia/Error semántico: Identificador '" + entry.getKey()
                        + "' ha sido declarado pero no usado.");
            }
        }
    }

    void reportError(String message) {
        errorWriter.println(message);
    }
}

class Symbol {
    private String name;
    private String type;
    private boolean initialized;
    private boolean used;

    Symbol(String name, String type, boolean initialized) {
        this.name = name;
        this.type = type;
        this.initialized = initialized;
        this.used = false;
    }

    String getType() {
        return type;
    }

    boolean isInitialized() {
        return initialized;
    }

    void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    boolean isUsed() {
        return used;
    }

    void setUsed(boolean used) {
        this.used = used;
    }
}

class SemanticAnalyzer extends segundopractico.compiladoresBaseListener {
    private SymbolTable symbolTable;
    private PrintWriter errorWriter;

    SemanticAnalyzer(PrintWriter errorWriter) {
        this.symbolTable = new SymbolTable(errorWriter);
        this.errorWriter = errorWriter;
    }

    @Override
    public void enterDeclaracion(segundopractico.compiladoresParser.DeclaracionContext ctx) {
        String type = ctx.tipo().getText();
        String name = ctx.ID().getText();
        boolean isInitialized = ctx.expresion() != null;
        symbolTable.addSymbol(name, type, isInitialized);

        if (isInitialized) {
            String exprType = getExpresionType(ctx.expresion());
            if (exprType == null) {
                symbolTable.reportError(
                        "Error semántico: Tipos de datos incompatibles en declaración de '" + name + "'.");
            } else if (!isTypeCompatible(type, exprType)) {
                symbolTable.reportError("Error semántico: Tipos de datos incompatibles en declaración de '" + name
                        + "'.");
            }
        }
    }

    @Override
    public void enterParametros(segundopractico.compiladoresParser.ParametrosContext ctx) {
        for (segundopractico.compiladoresParser.ParametroContext param : ctx.parametro()) {
            String type = param.tipo().getText();
            String name = param.ID().getText();
            // Evitar la adición de parámetros como duplicados en la tabla de símbolos
            if (!symbolTable.containsSymbol(name)) {
                symbolTable.addSymbol(name, type, true); // Los parámetros están inicializados por definición
            }
        }
    }

    @Override
    public void enterAsignacion(segundopractico.compiladoresParser.AsignacionContext ctx) {
        String name = ctx.ID().getText();
        symbolTable.checkSymbol(name);

        if (ctx.expresion() != null) {
            String exprType = getExpresionType(ctx.expresion());
            String varType = symbolTable.getSymbolType(name);
            if (exprType == null) {
                symbolTable.reportError("Error semántico: Tipos de datos incompatibles en declaración de '" + name
                        + "' de tipo " + varType);
            } else if (varType != null && !isTypeCompatible(varType, exprType)) {
                symbolTable.reportError("Error semántico: Tipos de datos incompatibles en asignación de '" + name
                        + "' de tipo " + varType + " con expresión de tipo " + exprType);
            }
        }

        symbolTable.initializeSymbol(name);
    }

    @Override
    public void enterExpresion(segundopractico.compiladoresParser.ExpresionContext ctx) {
        if (ctx.ID() != null) {
            String name = ctx.ID().getText();
            symbolTable.checkSymbol(name);
        }
    }

    @Override
    public void exitPrograma(segundopractico.compiladoresParser.ProgramaContext ctx) {
        symbolTable.printSymbols(errorWriter);
    }

    private String getExpresionType(segundopractico.compiladoresParser.ExpresionContext ctx) {
        if (ctx.literal() != null) {
            if (ctx.literal().NUMERO() != null) {
                return "int";
            } else if (ctx.literal().DOUBLE_LITERAL() != null) {
                return "double";
            }
        } else if (ctx.ID() != null) {
            return symbolTable.getSymbolType(ctx.ID().getText());
        } else if (ctx.expresion().size() == 2) {
            // Verificar el tipo de ambas subexpresiones y determinar el tipo resultante
            String leftType = getExpresionType(ctx.expresion(0));
            String rightType = getExpresionType(ctx.expresion(1));
            if (leftType == null || rightType == null) {
                return null;
            }
            if (leftType.equals("double") || rightType.equals("double")) {
                return "double";
            } else {
                return "int";
            }
        }
        return null;
    }

    private boolean isTypeCompatible(String varType, String exprType) {
        if (varType.equals(exprType)) {
            return true;
        }
        if (varType.equals("double") && exprType != null && (exprType.equals("int") || exprType.equals("double"))) {
            return true;
        }
        return false;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }
}

class CustomErrorListener extends BaseErrorListener {
    private PrintWriter errorWriter;

    CustomErrorListener(PrintWriter errorWriter) {
        this.errorWriter = errorWriter;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
            Object offendingSymbol,
            int line,
            int charPositionInLine,
            String msg,
            RecognitionException e) {
        String errorType = "Error sintáctico";
        if (msg.contains("missing ';'")) {
            errorType = "Error sintáctico: Falta de un punto y coma";
        } else if (msg.contains("missing '('")) {
            errorType = "Error sintáctico: Falta de apertura de paréntesis";
        } else if (msg.contains("mismatched input")) {
            errorType = "Error sintáctico: Formato incorrecto en lista de declaración de variables";
        }
        errorWriter.println(errorType + " en línea " + line + ":" + charPositionInLine + " - " + msg);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromFileName("input/entrada.txt");

        compiladoresLexer lexer = new compiladoresLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        compiladoresParser parser = new compiladoresParser(tokens);

        try (PrintWriter errorWriter = new PrintWriter(new FileWriter("errores.txt"))) {
            parser.removeErrorListeners();
            parser.addErrorListener(new CustomErrorListener(errorWriter));

            ParseTree tree = parser.programa();
            ParseTreeWalker walker = new ParseTreeWalker();
            SemanticAnalyzer analyzer = new SemanticAnalyzer(errorWriter);
            walker.walk(analyzer, tree);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
