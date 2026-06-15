package Main;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import ProyectoTiendaVideoJuegos.*;

public class menu {
    
    private Scanner leer = new Scanner(System.in);
    private Tienda tienda = new Tienda();

    //Metodos
    public void iniciar(){
        int opcion = -1;

        IRepositorio <Producto> repositorioProducto = new Repositorio<>("producto.dat");
        List<Producto> productos = (List<Producto>) repositorioProducto.consultar();
        IRepositorio <Persona> repositorioPersona = new Repositorio<>("persona.dat");
        List<Persona> personas = (List<Persona>) repositorioPersona.consultar();

        do {
            System.out.println("---Tienda de VideoJuegos JP---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Alquilar producto");
            System.out.println("3. Mostrar disponibles");
            System.out.println("4. Registrar cliente");
            System.out.println("5. Vender Producto");
            System.out.println("6. Catalogo ordenado por Titulos");
            System.out.println("0. Salir");
            System.out.print("------------------");
            System.out.println("Seleccione una Opción: ");
            
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
                    alquilarProducto();
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
                    venderProducto();
                case 6:
                    System.out.println("---Catalogo ordenado por Titulos---");
                    tienda.mostrarOrdenados();
                    break;
                case 0:
                    System.out.println("---Saliendo del Sistema---");
                    break;                    
                default:
                    System.out.println("Opción Invalida");
                    break;
            }
        } while (opcion != 0);
        repositorioProducto.guardar(productos);
        repositorioPersona.guardar(personas);
        leer.close();
    }




    private void agregarProducto(){
        int opcion = -1;

        System.out.println("---Agregar Producto---");
        System.out.println("1. Juego");
        System.out.println("2. Consola");
        System.out.println("3. Accesorio");
        System.out.println("Seleccione una de las Opciones: ");
        opcion = leer.nextInt();

        while (opcion!=1 || opcion!=2 || opcion!=3){
            System.out.println("Error--Ingrese una Opcion Valida: ");
        }
            
        
    }

    public void alquilarProducto(){

    }

    public void mostrarDisponibles(){

    }

    public void registrarCliente(){

    }

    public void venderProducto(){

    }
}
