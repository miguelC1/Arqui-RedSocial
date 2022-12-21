package backend.serviciointerespublicacion;

import java.util.List;

public class ServicioInteresPublicacion {
    private ListaInteresPublicacion listaInteresPublicacion;

    public ServicioInteresPublicacion() {
        listaInteresPublicacion= new ListaInteresPublicacion();
    }

    public void agregarInteresPublicacion(int idInteres, int idPublicacion) {
        listaInteresPublicacion.agregarInteresPublicacion(idInteres,idPublicacion);
    }

    public List<Integer> listarInteresesPorPublicacion(int idPublicacion){
        return listaInteresPublicacion.listarInteresesPorPublicacion(idPublicacion);
    }

}
