package ProyectoTiendaVideoJuegos;
import java.io.Serializable;

//Agregamos abstract a la clase Producto
public abstract class Producto implements Serializable, IVendible {

    private static final long serialVersionUID = 1L;

    private int id;
    private String titulo;
    private double precio;
    private int stock;
    
    private EstadoProducto estado; //solo puede tomar valores definidos en el enum EstadoProducto


    //Constructor
    public Producto(int id, String titulo, double precio, int stock) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
        this.stock = stock;
        this.estado = EstadoProducto.DISPONIBLE; //Inicializamos el estado del producto como disponible
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

    public EstadoProducto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }

    //Metodos
    public abstract String getCategoria(); //Metodo abstracto para dsp

    public void mostrarInfo(){
        System.out.println("ID: " + id + " | Categoría: " + getCategoria());
        System.out.println("Título: " + titulo + " | Precio: $" + precio);
        System.out.println("Precio: " + precio);
        System.out.println("Stock: " + stock + " | Estado: " + estado);
    }

    
}
