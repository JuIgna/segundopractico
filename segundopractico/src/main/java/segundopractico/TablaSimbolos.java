package segundopractico;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

class TablaSimbolos {
    private Map<String, Simbolo> simbolos = new HashMap<>();
    private PrintWriter escritorErrores;

    TablaSimbolos(PrintWriter escritorErrores) {
        this.escritorErrores = escritorErrores;
    }

    void agregarSimbolo(String nombre, String tipo, boolean estaInicializado) {
        if (simbolos.containsKey(nombre)) {
            reportarError("Error semántico: Identificador '" + nombre + "' ya ha sido declarado.");
        } else {
            simbolos.put(nombre, new Simbolo(nombre, tipo, estaInicializado));
        }
    }

    boolean contieneSimbolo(String nombre) {
        return simbolos.containsKey(nombre);
    }

    void verificarSimbolo(String nombre) {
        if (!simbolos.containsKey(nombre)) {
            reportarError("Error semántico: Identificador '" + nombre + "' no ha sido declarado.");
        } else if (!simbolos.get(nombre).isInicializado()) {
            reportarError("Error semántico: Identificador '" + nombre + "' no ha sido inicializado.");
        } else {
            simbolos.get(nombre).setUsado(true);
        }
    }

    void inicializarSimbolo(String nombre) {
        if (simbolos.containsKey(nombre)) {
            simbolos.get(nombre).setInicializado(true);
        }
    }

    String getTipoSimbolo(String nombre) {
        if (simbolos.containsKey(nombre)) {
            return simbolos.get(nombre).getTipo();
        }
        return null;
    }

    void imprimirSimbolos(PrintWriter escritor) {
        for (Map.Entry<String, Simbolo> entrada : simbolos.entrySet()) {
            if (!entrada.getValue().isUsado()) {
                escritor.println("Advertencia/Error semántico: Identificador '" + entrada.getKey()
                        + "' ha sido declarado pero no usado.");
            }
        }
    }

    void reportarError(String mensaje) {
        escritorErrores.println(mensaje);
    }
}