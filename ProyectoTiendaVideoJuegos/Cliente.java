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
       
        //verificamos el stock 
        if (p.getStock() > 0) { 
            this.saldo -= p.getPrecio(); 
            p.setStock(p.getStock() - 1); //Disminuimos el stock del producto
            p.setEstado(EstadoProducto.ALQUILADO); //llamamos al enum
            this.productosAlquilados.add(p); 

            if (p.getStock() == 0) {
            p.setEstado(EstadoProducto.SIN_STOCK);
            }

            System.out.println("Producto alquilado: " + p.getTitulo());
        } else {
            throw new StockInsuficienteException("No hay stock disponible para el producto: " + p.getTitulo());
        }
       
    }

    public void comprarProducto(Producto p) throws StockInsuficienteException {
        if (p.getStock() > 0) {
            this.saldo -= p.getPrecio(); 
            p.setStock(p.getStock() - 1); 

            if (p.getStock() == 0) {
            p.setEstado(EstadoProducto.SIN_STOCK);
            }
            System.out.println("Compra Exitosa: " + p.getTitulo());
        } else {
        throw new StockInsuficienteException("No hay stock para comprar " + p.getTitulo());
        }
    }

    public void devolverProducto(Producto p) {  //no leva excepcion porque el cliente no puede devolver un producto no alquilado
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
