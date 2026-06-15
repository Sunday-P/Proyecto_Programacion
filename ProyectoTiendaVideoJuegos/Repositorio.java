package ProyectoTiendaVideoJuegos;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repositorio<O> implements IRepositorio<O>{
    
    private  File archivo;

    public Repositorio (String nombreArchivo){
        this.archivo = new File(nombreArchivo);
    }

    //Metodos
    @Override
    public void guardar(List<O> objeto){
        
        try (
                //Guardamos los objetos completos en el archivo 
                //creamos el objeto para guardar objetos en el archivo
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))
        ) {
        //Lo escribimos en el archivo
            oos.writeObject(objeto);

        } catch (IOException e) { 
        //si ocurre un error lo muestra por la consola
            e.printStackTrace();
        }
    }

    @Override
    public List<O> consultar() {

        if (!archivo.exists()) { //verificamos que el archivo exista, si no existe saldra el print informando que no existe
            System.out.println("No existe ningun archivo");
        }

        try (//creamos el objeto para leer los objetos serializados
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))
        ) {
            return (List<O>) ois.readObject(); //devuelve la lista guardada

        } catch ( IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}