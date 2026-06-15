package ProyectoTiendaVideoJuegos;
public class StockInsuficienteException extends Exception { //esta clase hereda de Exception, por eso es una extension

    public StockInsuficienteException(String mensaje) { //recibe el mensaje de error
        super(mensaje); //Se lo pasa al padre (Exception)
    }

}
