package ProyectoTiendaVideoJuegos;

public class Cliente extends Persona {

    private static final long serialVersionUID = 1L;

    private String[] productosAlquilados; //Array para almacenar los productos alquilados por el cliente
    private double saldo;

    
    //Getters and Setters
    public String[] getProductosAlquilados() {
        return productosAlquilados;
    }
    public void setProductosAlquilados(String[] productosAlquilados) {
        this.productosAlquilados = productosAlquilados;
    }

    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    //Constructor
    public Cliente(int dni, String nombre, String apellido, String[] productosAlquilados, double saldo) {
        super(dni, nombre, apellido); //superclase
        this.productosAlquilados = productosAlquilados; //inicializamos el array de productos alquilados
        this.saldo = saldo;
    } 

    //Metodos
    @Override
    public void mostrarInfo() {
        System.out.println("DNI: " + getDni() + ", Nombre: " + getNombre() + ", Apellido: " + getApellido());
        System.out.println("Saldo Disponible: $" + getSaldo());
        System.out.println("Productos Alquilados:" + String.join(", ", getProductosAlquilados())); 
        //join para mostrar los productos alquilados separados por comas
    }


    //private String alquilerProducto(Producto producto) {
    //    
    //}

}
