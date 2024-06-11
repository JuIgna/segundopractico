package segundopractico;


class Simbolo {
    private String nombre;
    private String tipo;
    private boolean inicializado;
    private boolean usado;

    Simbolo(String nombre, String tipo, boolean inicializado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.inicializado = inicializado;
        this.usado = false;
    }

    String getTipo() {
        return tipo;
    }

    boolean isInicializado() {
        return inicializado;
    }

    void setInicializado(boolean inicializado) {
        this.inicializado = inicializado;
    }

    boolean isUsado() {
        return usado;
    }

    void setUsado(boolean usado) {
        this.usado = usado;
    }
    
}