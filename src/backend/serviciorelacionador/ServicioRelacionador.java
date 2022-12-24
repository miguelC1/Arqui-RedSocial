package backend.serviciorelacionador;

import java.util.List;

public class ServicioRelacionador {
    private ListaInteresRelacionados listaInteresRelacionados;

    public ServicioRelacionador(String nombreCsv){
        listaInteresRelacionados= new ListaInteresRelacionados(nombreCsv);
    }

    public void agregarInteresRelacionado(int idInteres, int idRelacionado){
        listaInteresRelacionados.agregarInteresRelacionado(idInteres, idRelacionado);
    }

    public List<Integer> listarInteresesRelacionados (int idRelacionado){
        return listaInteresRelacionados.listarInteresesRelacionados(idRelacionado);
    }

}
