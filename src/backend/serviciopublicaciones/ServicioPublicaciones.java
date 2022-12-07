package backend.serviciopublicaciones;

import java.util.List;

public class ServicioPublicaciones {

    private ListaPublicaciones listaPublicaciones;

    public ServicioPublicaciones() {
       listaPublicaciones= new ListaPublicaciones();
    }

    public int agregarPublicacion(int idUsuario, String contenido) {
        int res=listaPublicaciones.agregarPublicacion(idUsuario,contenido);
        return res;
    }

    public Publicacion buscarPublicacion(int idPublicacion) {
        Publicacion res = listaPublicaciones.buscarPublicacion(idPublicacion);
        return res;
    }

    public List<Integer> listarPublicaciones() {
        return listaPublicaciones.listarPublicaciones();
    }


}

