package backend.serviciointereses;


import java.util.List;


public class ServicioIntereses {
    private ListaIntereses listaIntereses;

    public ServicioIntereses() {
        listaIntereses= new ListaIntereses();
    }

    public int agregarInteres(String interes) {
        int res=listaIntereses.agregarInteres(interes);
        return res;
    }

    public void agregarPublicacionAinteres(int idInteres ,int idPublicacion) {
        listaIntereses.agregarInteresPublicaciones(idInteres, idPublicacion);
    }

    public void agregarInteresUsuarios(int idInteres,int idUsuario){
        listaIntereses.agregarInteresUsuarios(idInteres,idUsuario);
    }
    public int buscaIDInteresPorPublicacion(int idPublicacion){
        return listaIntereses.buscaIDInteresPorPublicacion(idPublicacion);
    }

    public Interes buscarInteres(int idInteres) {
        Interes res = listaIntereses.buscarInteres(idInteres);
        return res;
    }

    public List<Integer> listarIntereses() {
        return listaIntereses.listarIntereses();
    }
}
