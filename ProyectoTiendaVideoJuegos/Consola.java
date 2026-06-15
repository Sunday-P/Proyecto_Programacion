package ProyectoTiendaVideoJuegos;

public class Consola extends Producto{
    
    private String marca;
    private String modelo;
    
    //Constructor
    public Consola(int id, String titulo, double precio, int stock, String marca, String modelo) {
    super(id, titulo, precio, stock);
    this.marca = marca;
    this.modelo = modelo;
    }

    //Getters and Setters
    public String getMarca() {
    return marca;
    }
   public void setMarca(String marca) {
    this.marca = marca;
   }

   public String getModelo() {
    return modelo;
   }
   public void setModelo(String modelo) {
    this.modelo = modelo;
   }

   //Metodos
   @Override
   public void mostrarInfo(){
       System.out.println("---Informacion de la Consola---"); 
       System.out.println("ID: " + getId());
       System.out.println("Título: " + getTitulo());
       System.out.println("Precio: " + getPrecio());
       System.out.println("Stock: " + getStock());
       System.out.println("Marca: " + getMarca());
       System.out.println("Modelo: " + getModelo());
    }

    @Override
    public String getCategoria(){
        return "Consola";
    }

    public void vender() throws StockInsuficienteException {
        if (getStock() > 0) {
            setStock(getStock() - 1);

            if (getStock() == 0) {
            setEstado(EstadoProducto.SIN_STOCK);
            }

            System.out.println("Venta procesada: " + getTitulo());
        } else {
            throw new StockInsuficienteException("No se puede vender. Stock agotado para el producto: " + getTitulo());
        }

    }

    public void alquilar() throws StockInsuficienteException {
        if (getStock() > 0) {
            setStock(getStock() - 1);
            setEstado(EstadoProducto.ALQUILADO);

            if (getStock() == 0) {
            setEstado(EstadoProducto.SIN_STOCK);
            }

            System.out.println("Alquiler procesado: " + getTitulo());
        } else {
            throw new StockInsuficienteException("No se puede alquilar. Stock agotado para el producto: " + getTitulo());
        }

    }
   
    
}

