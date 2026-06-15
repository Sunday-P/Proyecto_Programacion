package ProyectoTiendaVideoJuegos;
import java.io.Serializable;

//Agregamos abstract a la clase Producto
public abstract class Producto implements Serializable, IVendible {

    private static final long serialVersionUID = 1L;

    private int id;
    private String titulo;
    private double precio;
    private int stock;
    
    //private EstadoProducto estado; //solo puede tomar valores definidos en el enum EstadoProducto


    //Constructor
    public Producto(int id, String titulo, double precio, int stock) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
        this.stock = stock;
    }

    //Metodos getter y setter
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    //Metodos
    public void mostrarInfo(){
        System.out.println("---Informacion del Producto---"); 
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Precio: " + precio);
        System.out.println("Stock: " + stock);
    }

    public String getCategoria(){
        return "Producto";
    }
}
