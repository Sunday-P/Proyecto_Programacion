import java.io.Serializable;

//Agregamos abstract a la clase Persona
public abstract class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    //Definimos atributos
    private int dni;
    private String nombre;
    private String apellido;

    //Constructor
     public Persona(int dni, String nombre, String apellido) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    //Metodos getter y setter
    public int getDni() {
        return dni;
    }
   
    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    //Metodos
    public void mostrarInfo(){
        System.out.println("DNI: " + dni + ", Nombre: " + nombre + ", Apellido: " + apellido);
    }

    //Comparar y equala
    

}
