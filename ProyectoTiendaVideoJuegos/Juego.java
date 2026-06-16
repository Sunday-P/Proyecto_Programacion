package ProyectoTiendaVideoJuegos;

public class Juego extends Producto{
    
    private String plataforma;
    private String genero;
    
    //Constructor
    public Juego(int id, String titulo, double precio, int stock, String plataforma, String genero) {
        super(id, titulo, precio, stock);
        this.plataforma = plataforma;
        this.genero = genero;
    }

    //Getters and Setters
    public String getPlataforma() {
        return plataforma;
    }
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

   //Metodos
   @Override
   public void mostrarInfo(){
       System.out.println("---Informacion del Juego---"); 
       System.out.println("ID: " + getId());
       System.out.println("Título: " + getTitulo());
       System.out.println("Precio: " + getPrecio());
       System.out.println("Stock: " + getStock());
       System.out.println("Plataforma: " + getPlataforma());
       System.out.println("Genero: " + getGenero());
    }

    @Override
    public String getCategoria(){
        return "Juego";
    }
    @Override
    public void vender() throws StockInsuficienteException {
        super.vender();
    }

    @Override
    public void alquilar() throws StockInsuficienteException {
        super.alquilar();
        System.out.println("Juego Alquilado: " + getTitulo());
    }

}
