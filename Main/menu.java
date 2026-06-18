package Main;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import ProyectoTiendaVideoJuegos.*;

public class menu {
    
    private Scanner leer;
    private Tienda tienda;
    private Repositorio<Producto> repoProductos;
    private Repositorio<Persona> repoPersonas; //Para persisitir clientes
    
    public menu() {
        this.leer = new Scanner(System.in);
        this.tienda = new Tienda();
        //Inicializamos los archivos fisicos
        this.repoProductos = new Repositorio<>("productos.dat");
        this.repoPersonas = new Repositorio<>("personas.dat");
    }
    
    
    //Metodos
    public void iniciar(){
        int opcion = 0;

        // REQUERIMIENTO: Cargar datos al iniciar
        List<Producto> cargaInicial = repoProductos.consultar();
        cargaInicial.forEach(p -> tienda.agregarProducto(p));

        List<Persona> cargaClientes = repoPersonas.consultar();
        cargaClientes.forEach(persona -> tienda.registrarPersona(persona));

        do {
            System.out.println("---Tienda de VideoJuegos JP---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Alquilar producto");
            System.out.println("3. Mostrar disponibles");
            System.out.println("4. Registrar cliente");
            System.out.println("5. Mostrar Info del Cliente");
            System.out.println("6. Vender Producto");
            System.out.println("7. Devolver Producto");
            System.out.println("8. Clientes con Alquileres");
            System.out.println("9. Catalogo ordenado por Titulos");
            System.out.println("10. Guardar");
            System.out.println("11. Salir");
            System.out.println("------------------");
            System.out.print("Seleccione una Opción: ");
            

            if (leer.hasNextInt()){
                    opcion = leer.nextInt();
                    leer.nextLine();
                    //Agregamos un leer.nextLine() para que limpie el buffer despues del leer.nextInt()
            } else{
                System.out.println("Debe Ingresar una opcion numerica valida");
                leer.nextLine();
                continue;
            }

            
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
                    break;
                case 5:
                    System.out.println("---Mostrar Info del Cliente---");
                    mostrarInfoCliente();
                    break;
                case 6:
                    System.out.println("---Vender Producto---");
                    gestionOp(true);
                    break;
                case 7:
                    System.out.println("---Devolver Producto---");
                    devolviendoProducto();
                    break;
                case 8:
                    mostrarClientesConDeudas();
                    break;
                case 9:
                    System.out.println(""); 
                    tienda.mostrarOrdenados(); //Esto imprime un msj de Catalogo ordenado por titulo de la A a la Z
                    break;
                case 10:
                    // REQUERIMIENTO: Guardar la lista completa antes de cerrar
                    // Se usa serialización (ObjectOutputStream) para volcar al disco.
                    repoProductos.guardar(tienda.getProductos());
                    repoPersonas.guardar(tienda.getListaPersonas());
                    System.out.println("Datos Guardados en el archivo");
                    break;
                case 11:
                    System.out.println("¡Hasta Luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
            if (opcion!=11) { //El if es para que cuando eligamos salir, no te tire el print de "Presione ENTER..."
                System.out.println("\nPresione ENTER para continuar...");
                leer.nextLine();    
            }
        } while (opcion != 11);
        leer.close();
    }
    
    private void agregarProducto(){
        int tipo;
        do{
        System.out.println("\n--- Nuevo Producto ---");
        System.out.println("Tipo: 1. Juego | 2. Consola | 3. Accesorio");
        tipo = leer.nextInt(); leer.nextLine();

            if (tipo < 1 || tipo > 3) {
                System.out.println("Numero Invalido Ingrese nuevamente");
            }
            
        }while (tipo <1 || tipo>3); //Un validador para la entrada de TIPO

        int id = tienda.getProductos() // 1. Trae la lista de productos guardados (List<Producto>)
                       .stream()        // 2. Abre un "flujo de datos" para procesar los productos uno por uno
                       .mapToInt(p -> p.getId()) // 3. Transforma el flujo de objetos 'Producto' a un flujo de puros números enteros (IDs)
                       .max()           // 4. Analiza todos los números y encuentra cuál es el más grande (el ID máximo)
                       .orElse(0)       // 5. Si la lista estaba vacía (primer producto), devuelve 0 como valor por defecto
                       + 1;             // 6. Al ID más alto encontrado (o al 0) le suma 1 para generar el nuevo ID único
        //Una forma para que el ID sea autoincrementable
        System.out.print("Título: "); String titulo = leer.nextLine();
        System.out.print("Precio: "); double precio = leer.nextDouble();
        System.out.print("Stock: "); int stock = leer.nextInt(); leer.nextLine();

        Producto nuevo = null;
        switch (tipo) {
            case 1: {
                System.out.print("Plataforma: "); String plat = leer.nextLine();
                System.out.print("Género: "); String gen = leer.nextLine();
                nuevo = new Juego(id, titulo, precio, stock, plat, gen);
                break;
            }
            case 2: {
                System.out.print("Marca: "); String marca = leer.nextLine();
                System.out.print("Modelo: "); String mod = leer.nextLine();
                nuevo = new Consola(id, titulo, precio, stock, marca, mod);
                break;
            }
            case 3:{
                System.out.print("Tipo accesorio: "); String tAcc = leer.nextLine();
                System.out.print("Compatible con: "); String comp = leer.nextLine();
                nuevo = new Accesorio(id, titulo, precio, stock, tAcc, comp);
                break;
            }
            default: {
                System.out.println("Tipo inválido.");
                break;
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
        leer.nextLine(); //limpia el buffer para que no se salte el proximo String

        Persona p = tienda.buscarCliente(dni); // Búsqueda rápida en el HashMap
        
        if (p instanceof Cliente) { // Verificamos el tipo de objeto 
            Cliente cliente = (Cliente) p; //Casteo manual, 'p' como CLiente

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
        leer.nextLine();

        // 3. Instanciamos el Cliente y lo mandamos a la Tienda
        Cliente nuevo = new Cliente(dni, nombre, apellido, saldo);
        tienda.registrarPersona(nuevo); 

        System.out.println("¡Cliente registrado con éxito en el sistema!");
    }

    public void cargarPersonas(List<Persona> lista) {
    if (lista != null) {
        // Recorremos la lista cargada y registramos cada persona en la tienda
        lista.forEach(tienda::registrarPersona);
    }
    }

    public void devolviendoProducto(){
        System.out.print("Ingrese DNI del cliente: ");
        int dni = leer.nextInt();
        leer.nextLine(); // Limpiamos el buffer del entero

        Persona p = tienda.buscarCliente(dni); // Buscamos en la tienda
        
        if (p instanceof Cliente) { 
            Cliente cliente = (Cliente) p; // Casteo a Cliente para acceder a sus métodos

            System.out.print("Ingrese título del producto a devolver: ");
            String titulo = leer.nextLine();
            Producto prod = tienda.buscarProducto(titulo); 

            if (prod != null) {
                // ¡Aquí llamamos a tu método de la derecha de la imagen!
                cliente.devolverProducto(prod); 
            } else {
                System.out.println("El producto no existe en el catálogo.");
            }
        } else {
            System.out.println("Cliente no encontrado en el sistema.");
        }
    }

    public void mostrarInfoCliente(){
        System.out.print("Ingrese DNI del cliente: ");
        int dni = leer.nextInt();
        leer.nextLine(); // Limpiamos el buffer del entero

        Persona p = tienda.buscarCliente(dni); // Buscamos en la tienda

        if (p instanceof Cliente){
            Cliente cliente = (Cliente) p; // Casteo a Cliente para acceder a sus métodos
            System.out.println("---Informacion del Cliente---");
            cliente.mostrarInfo();    
        }else{
            System.out.println("Cliente no encontrado en el sistema.");
        }
    }

    public void mostrarClientesConDeudas() {
        System.out.println("\n--- CLIENTES CON ALQUILERES ACTIVOS ---");

        // Invocamos el método de Tienda que devuelve el HashSet 
        Set<Persona> clientesUnicos = tienda.obtenerClientesActivos();

        if (clientesUnicos.isEmpty()) {
        System.out.println("No hay clientes con alquileres en este momento.");
        } else {
        // REQUERIMIENTO: Uso de referencia a método (::)
        clientesUnicos.forEach(Persona::mostrarInfo);
    }
    }
}
