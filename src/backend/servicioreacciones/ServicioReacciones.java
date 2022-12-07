package backend.servicioreacciones;


import java.util.Map;

public class ServicioReacciones {
    private ListaReacciones listaReacciones;

    public ServicioReacciones(){
        listaReacciones = new ListaReacciones();
    }

    public void agregarReaccion(int idPublicacion, int idUsuario, Emocion reaccion) {
        listaReacciones.agregarReaccion(idPublicacion,idUsuario, reaccion);
    }

    public Map<Emocion,Integer> listarResumenReacciones(int idPublicacion) {
        return  listaReacciones.listarResumenReacciones(idPublicacion);
    }
}
