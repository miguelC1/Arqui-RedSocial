package backend.serviciointereses;


import java.util.List;


public class ServicioIntereses {
    private ListaIntereses listaIntereses;

    public ServicioIntereses() {
        listaIntereses= new ListaIntereses();
    }
    public int registrarInteres(String nombreInteres) {
        int res=listaIntereses.agregarInteres(nombreInteres);
        return res;
    }

    public Interes buscarInteres(int idInteres) {
        Interes res = listaIntereses.buscarInteres(idInteres);
        return res;
    }

    public List<Integer> listarIntereses() {
        return listaIntereses.listarIntereses();
    }

}
