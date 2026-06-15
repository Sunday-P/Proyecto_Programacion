package ProyectoTiendaVideoJuegos;

public interface IVendible {

    void vender() throws StockInsuficienteException;

    void alquilar() throws StockInsuficienteException;

}
