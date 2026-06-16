package ProyectoTiendaVideoJuegos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;   
import java.util.Comparator;

public class Tienda {
    private ArrayList<Producto> productos; //Almacena los productos disponibles en la tienda
    private HashMap<Integer, Persona> personas;


    //Un constructor para inicializar la tienda con una lista de productos y un mapa de clientes
    public Tienda() {
        this.productos = new ArrayList<>();
        this.personas = new HashMap<>(); //nos permite buscar clientes por su DNI
    }

    //Metodos de registro
    public ArrayList<Producto> getProductos(){
        return productos;
    }

    public void registrarPersona(Persona p) {
        personas.put(p.getDni(), p);
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }


    // Requerimiento: Buscar producto por título usando Stream.filter() + findFirst()
    public Producto buscarProducto(String titulo) {
        return productos.stream() //Convertimos la lista en un flujo de datos
                .filter(p -> p.getTitulo().equalsIgnoreCase(titulo)) //filtramos ignorando mayusculas y minusculas
                .findFirst() //buscamos el primer producto que coincida con el título
                .orElse(null); //retorna un null si no encuentra el producto
    }


    //Requerimiento: Filtrar productos disponibles y retornar una nueva lista con collect
    public List<Producto> obtenerDisponibles() {
        return productos.stream()
                .filter(p -> p.getEstado() == EstadoProducto.DISPONIBLE) //filtramos por la constante del enum
                .collect(Collectors.toList()); //agrupamos
    }


    //Busca una persona directamente en el HashMap usando la eficiencia del índice
    public Persona buscarCliente(int dni) {
        return personas.get(dni); //recorremos usando la clave
    }

    //Metodo para mostrar por consola:
    public void mostrarDisponibles() {
        System.out.println("--- Catálogo de Productos Disponibles ---");
        productos.stream()
                 .filter(p -> p.getEstado() == EstadoProducto.DISPONIBLE)
                 .forEach(p -> p.mostrarInfo()); // Ejecuta el metodo mostrarInfo() de cada producto disponible //Extender clase por el mostrarInfo?
    }

    //Mostrar catalogo ordenado por titulo usando Steam.sorted() + lambda //
    public void mostrarOrdenados() {
        System.out.println("--- Catálogo Ordenado por Titulo (A - Z) ---");   
        productos.stream()  
                .sorted((p1, p2) -> p1.getTitulo().compareToIgnoreCase(p2.getTitulo()))//Ordenamos ignorando mayusculas y minusculas (Lambda)
                .forEach(p -> p.mostrarInfo()); //reuso el codigo del metodo mostrarInfo() de cada producto
    
                //.sorted() es una op usada para ordenar los elementos de una lista/coleccion segun un criterio definido por el programador.
    }


    public void ordenarCatalogoFisicamente() {
        //Usamos Collections.sort con un comparador externo
        Collections.sort(productos, Comparator.comparing(Producto::getTitulo));
        System.out.println("La lista interna de productos ha sido ordenada.");
    }

}

    
