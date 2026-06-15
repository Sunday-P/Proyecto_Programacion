package ProyectoTiendaVideoJuegos;

public class Vendedor extends Persona{

    private static final long serialVersionUID = 1L; //Persona es serializable, por lo que Vendedor también debe serlo
    private int legajo;

    //Constructor con super
    public Vendedor(int dni, String nombre, String apellido, int legajo) {
        super(dni, nombre, apellido);
        this.legajo = legajo;
    }

    //Getters and Setters
    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

     
    //metodos
    @Override
    public void mostrarInfo() {
        System.out.println("Vendedor: " + getNombre() + " " + getApellido() + " | Legajo: " + legajo);
    }

    public void registrarProducto(Producto p){
        System.out.println("Vendedor " + getNombre() + "registró el producto: " + p.getTitulo());
    }
}
