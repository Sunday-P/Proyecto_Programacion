package ProyectoTiendaVideoJuegos;

public class Accesorio extends Producto {

    private String tipo;
    private String compatible;
    
    //Constructor
    public Accesorio (int id, String titulo, double precio, int stock, String tipo, String compatible) {
        super(id, titulo, precio, stock);
        this.tipo = tipo;
        this.compatible = compatible;
    }

    //Getters and Setters
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCompatible() {
        return compatible;
    }
    public void setCompatible(String compatible) {
        this.compatible = compatible;
    }

    //Metodos
    @Override
    public void mostrarInfo(){
        System.out.println("---Informacion del Accesorio--"); 
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Precio: " + getPrecio());
        System.out.println("Stock: " + getStock());
        System.out.println("Tipo: " + getTipo());
        System.out.println("Compatible: " + getCompatible());
    }
    @Override
    public String getCategoria(){
        return "Accesorio";
    }

    public void vender(){

    }

    public void alquilar(){

    }
}
