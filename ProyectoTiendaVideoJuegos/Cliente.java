package ProyectoTiendaVideoJuegos;

import java.util.ArrayList;

public class Cliente extends Persona {

    private static final long serialVersionUID = 1L;
    private ArrayList<Producto> productosAlquilados; //Array para almacenar los productos alquilados por el cliente
    private double saldo;

    
    //Getters and Setters
    public ArrayList<Producto> getProductosAlquilados() {
        return productosAlquilados;
    }
    public void setProductosAlquilados(ArrayList<Producto> productosAlquilados) {
        this.productosAlquilados = productosAlquilados;
    }

    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }



    //Constructor
    public Cliente(int dni, String nombre, String apellido, double saldo) {
        super(dni, nombre, apellido); //superclase
        this.productosAlquilados = new ArrayList<>(); //inicializamos el array de productos alquilados
        this.saldo = saldo;
    } 


    //Metodos
    @Override
    public void mostrarInfo() {
        System.out.println("Cliente: " + getNombre() + " " + getApellido() + " | DNI: " + getDni());
        System.out.println("Saldo Disponible: $" + getSaldo());
        mostrarProductos(); 
        System.out.println(); 

    }

    public void alquilerProducto(Producto p) throws StockInsuficienteException { //throws indica que este metodo lanza una excepcion de tipo StockInsuficienteException

        // 1. El cliente verifica su saldo
        if (this.saldo >= p.getPrecio()) {
        
            // 2. Le pedimos al producto que intente alquilarse
            p.alquilar();
        
            // 3. Si hubo stock, descontamos saldo y agregamos a la lista del cliente
            this.saldo -= p.getPrecio();
            this.productosAlquilados.add(p);
        
            System.out.println("Producto alquilado con éxito: " + p.getTitulo());
            System.out.println("Saldo restante: $" + this.saldo);
        
        } else {
         System.out.println("Saldo insuficiente para alquilar el producto.");
        }
    }

    public void comprarProducto(Producto p) throws StockInsuficienteException {
        // 1. El cliente verifica su propio saldo
        if (this.saldo >= p.getPrecio()) {
        
            // 2. Le pedimos al producto que intente venderse a sí mismo (él bajará su stock)
            p.vender(); 
        
            // 3. Si p.vender() NO lanzó excepción, significa que había stock. Ahora descontamos el saldo.
            this.saldo -= p.getPrecio();
            System.out.println("Compra Exitosa: " + p.getTitulo());
            System.out.println("Saldo restante: $" + this.saldo);
        
        } else {
            System.out.println("Saldo insuficiente para comprar el producto: " + p.getTitulo());
        }
    }
    
    public void devolverProducto(Producto p) {  //no lleva excepcion porque el cliente no puede devolver un producto no alquilado
        if (this.productosAlquilados.contains(p)) { 
            p.setStock(p.getStock() + 1); 
            p.setEstado(EstadoProducto.DISPONIBLE);
            this.productosAlquilados.remove(p); 

            System.out.println("Producto devuelto: " + p.getTitulo());

        } else {
            System.out.println("El cliente no tiene este producto alquilado: " + p.getTitulo());
        }

    }

    public void mostrarProductos() {
        System.out.println("Productos alquilados por " + getNombre() + ":");
        if (productosAlquilados.isEmpty()) { 
            System.out.println("No hay productos alquilados.");
        } else {
            for (Producto p : productosAlquilados) {
                System.out.println(" - " + p.getTitulo());
            }
        }
    }

}
