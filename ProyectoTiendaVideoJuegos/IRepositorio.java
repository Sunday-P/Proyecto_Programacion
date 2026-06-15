package ProyectoTiendaVideoJuegos;
import java.util.List;

public interface IRepositorio <O>{

    void guardar(List<O> objetos);

    List<O> consultar();
}
