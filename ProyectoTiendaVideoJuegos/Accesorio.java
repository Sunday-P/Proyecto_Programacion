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

    @Override
    public void vender() throws StockInsuficienteException {
        super.vender();
        System.out.println("Venta del Accesorio Realizada");
        System.out.println("Accesorio: " + getTitulo());
    }
    //metodo alquilar vacio porque no se pueden alquilar accesorios, solo venderlos
    @Override
    public void alquilar() throws StockInsuficienteException {
        if (getStock() > 0) {
            setStock(getStock() - 1); // Bajamos el stock de forma segura
        
            // CORRECCIÓN: Solo cambiamos el estado si ya no quedan unidades
            if (getStock() == 0) {
                setEstado(EstadoProducto.ALQUILADO); 
        }
        System.out.println("Alquiler procesado exitosamente: " + getTitulo());
    } else {
        throw new StockInsuficienteException("No hay ejemplares disponibles para alquilar: " + getTitulo());
    }
}

}
