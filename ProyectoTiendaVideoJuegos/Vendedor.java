package ProyectoTiendaVideoJuegos;

public class Vendedor extends Persona{

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

}
