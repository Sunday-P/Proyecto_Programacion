package Main;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import ProyectoTiendaVideoJuegos.*;

public class menu {
    
    private Scanner leer = new Scanner(System.in);
    //private Tienda tienda = new Tienda(System.in);

    //Metodos
    public void iniciar(){
        int opcion = -1;

        IRepositorio <Producto> repositorioProducto = new Repositorio<>("producto.dat");
        //Producto.setProducto((List<Producto>) repositorioProducto.consultar())

        do {
            System.out.println("---Tienda de VideoJuegos JP---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Alquilar producto");
            System.out.println("3. Mostrar disponibles");
            System.out.println("4. Registrar cliente");
            System.out.println("0. Salir");
            System.out.print("> ");
            opcion = leer.nextInt();
        } while (opcion != 0);
    }




    public void agregarProducto(){

    }

    public void alquilarProducto(){

    }

    public void mostrarDisponibles(){

    }

    public void registrarCliente(){

    }

}
