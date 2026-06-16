package Main;
import java.io.File;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import ProyectoTiendaVideoJuegos.*;

public class menu {
    
    private Scanner leer;
    private Tienda tienda;
    private Repositorio<Producto> repoProductos;
    private Repositorio<Persona> repoPersonas;
    
    public menu() {
        this.leer = new Scanner(System.in);
        this.tienda = new Tienda();
        this.repoProductos = new Repositorio<>("productos.dat");
        this.repoPersonas = new Repositorio<>("personas.dat");
    }
    
    
    //Metodos
    public void iniciar(){
        int opcion = 0;

        List<Producto> cargaInicial = repoProductos.consultar();
        cargaInicial.forEach(p -> tienda.agregarProducto(p));

        do {
            System.out.println("---Tienda de VideoJuegos JP---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Alquilar producto");
            System.out.println("3. Mostrar disponibles");
            System.out.println("4. Registrar cliente");
            System.out.println("5. Vender Producto");
            System.out.println("6. Catalogo ordenado por Titulos");
            System.out.println("7. Salir y Guardar");
            System.out.println("------------------");
            System.out.print("Seleccione una Opción: ");
            
            if (leer.hasNextInt()){
                    opcion = leer.nextInt();
            } else{
                System.out.println("Debe Ingresar una opcion valida");
                continue;
            }
            leer.nextLine();


            switch (opcion) {
                case 1:
                    System.out.println("---Agregar Producto---");
                    agregarProducto();
                    break;
                case 2:
                    System.out.println("---Alquilar Producto---");
                    gestionOp(false);
                    break;
                case 3:
                    System.out.println("---Mostrar Disponibles---");
                    mostrarDisponibles();
                    break;
                case 4:
                    System.out.println("---Registrar Cliente---");
                    registrarCliente();
                case 5:
                    System.out.println("---Vender Producto---");
                    gestionOp(true);
                case 6:
                    System.out.println("---Catalogo ordenado por Titulos---");
                    tienda.mostrarOrdenados();
                    break;
                case 7:
                    // REQUERIMIENTO: Guardar la lista completa antes de cerrar
                    // Se usa serialización (ObjectOutputStream) para volcar al disco.
                    repoProductos.guardar(tienda.getProductos());
                    System.out.println("Datos persistidos en el archivo. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 7);
        leer.close();
    }
    
    private void agregarProducto(){
        System.out.println("\n--- Nuevo Producto ---");
        System.out.print("ID: "); int id = leer.nextInt(); leer.nextLine();
        System.out.print("Título: "); String titulo = leer.nextLine();
        System.out.print("Precio: "); double precio = leer.nextDouble();
        System.out.print("Stock: "); int stock = leer.nextInt(); leer.nextLine();

        System.out.println("Tipo: 1. Juego | 2. Consola | 3. Accesorio");
        int tipo = leer.nextInt(); leer.nextLine();

        Producto nuevo = null;
        switch (tipo) {
            case 1 -> {
                System.out.print("Plataforma: "); String plat = leer.nextLine();
                System.out.print("Género: "); String gen = leer.nextLine();
                nuevo = new Juego(id, titulo, precio, stock, plat, gen);
            }
            case 2 -> {
                System.out.print("Marca: "); String marca = leer.nextLine();
                System.out.print("Modelo: "); String mod = leer.nextLine();
                nuevo = new Consola(id, titulo, precio, stock, marca, mod);
            }
            case 3 -> {
                System.out.print("Tipo accesorio: "); String tAcc = leer.nextLine();
                System.out.print("Compatible con: "); String comp = leer.nextLine();
                nuevo = new Accesorio(id, titulo, precio, stock, tAcc, comp);
            }
        }

        if (nuevo != null) {
            tienda.agregarProducto(nuevo);
            System.out.println("Producto sumado al catálogo.");
        }
    }

    public void gestionOp(boolean esVenta) {
        System.out.print("Ingrese DNI del cliente: ");
        int dni = leer.nextInt();
        leer.nextLine();
        
        Persona p = tienda.buscarCliente(dni); // Búsqueda rápida en el HashMap
        
        if (p instanceof Cliente cliente) { // Verificamos el tipo de objeto 
            System.out.print("Ingrese título del producto: ");
            String titulo = leer.nextLine();
            Producto prod = tienda.buscarProducto(titulo); // Búsqueda con Streams 

            if (prod != null) {
                // REQUERIMIENTO: Bloque try-catch para manejar la excepción personalizada 
                try {
                    if (esVenta) {
                        cliente.comprarProducto(prod); // Delega la lógica de stock 
                    } else {
                        cliente.alquilerProducto(prod);
                    }
                } catch (StockInsuficienteException e) {
                    // Muestra el mensaje configurado en la excepción
                    System.err.println("ERROR: " + e.getMessage());
                } finally {
                    // REQUERIMIENTO: Incluir un bloque finally que se ejecute siempre
                    System.out.println("--- Transacción finalizada ---");
                }
            } else {
                System.out.println("El producto no existe en el catálogo.");
            }
        }else {
            System.out.println("Cliente no encontrado en el sistema.");
        }
    }


    public void mostrarDisponibles(){
        tienda.mostrarDisponibles();
    }

    public void mostrarOrdenados(){
        tienda.mostrarOrdenados();
    }

    public void registrarCliente(){
        System.out.println("\n--- Alta de Nuevo Cliente ---");

        // 1. Pedimos los datos básicos de la Persona
        System.out.print("DNI: ");
        int dni = leer.nextInt();
        leer.nextLine(); // Limpiar buffer 

        System.out.print("Nombre: ");
        String nombre = leer.nextLine();

        System.out.print("Apellido: ");
        String apellido = leer.nextLine();

        // 2. Pedimos el dato específico del Cliente (Saldo)
        System.out.print("Saldo inicial: ");
        double saldo = leer.nextDouble();

        // 3. Instanciamos el Cliente y lo mandamos a la Tienda
        Cliente nuevo = new Cliente(dni, nombre, apellido, saldo);
        tienda.registrarPersona(nuevo); 

        System.out.println("¡Cliente registrado con éxito en el sistema!");
    }
}
