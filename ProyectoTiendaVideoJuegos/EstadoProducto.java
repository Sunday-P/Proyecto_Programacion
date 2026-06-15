package ProyectoTiendaVideoJuegos;


public enum EstadoProducto {
    DISPONIBLE (1),
    ALQUILADO (2),
    SIN_STOCK (3);

    private final int codigo; //final para que no se pueda modificar el valor una vez asignado

    private EstadoProducto(int codigo) {
        this.codigo = codigo; 
    }

    public int getCodigo() {
        return codigo;
    }
}
