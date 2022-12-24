package backend.serviciorelacionador;

import java.util.ArrayList;
import java.util.List;

public class ListaInteresRelacionados {
    private List<InteresRelacionado> listaDeInteresesRelacionados;
    private GestorDeArchivoInteresRelacionador archivoInteresRelacionador;

    public ListaInteresRelacionados(String nombreCsv) {
        listaDeInteresesRelacionados = new ArrayList<>();
        archivoInteresRelacionador = new GestorDeArchivoInteresRelacionador(nombreCsv);
        cargarDatosLista();
    }

    public void agregarInteresRelacionado(int idInteres, int idRelacionado){
        InteresRelacionado interesR= new InteresRelacionado(idInteres, idRelacionado);
        archivoInteresRelacionador.escribirDatosEnCSV(interesR.toString());
        listaDeInteresesRelacionados.add(interesR);
    }

    public List<Integer> listarInteresesRelacionados (int idRelacionado){
        List<Integer>lista=new ArrayList<>();
        String []datos = archivoInteresRelacionador.leerDatosCSV();
        if(datos.length!=0) {
            for (InteresRelacionado dato : listaDeInteresesRelacionados) {
                if(dato.getIdRelacionado()==idRelacionado){
                    lista.add(dato.getIdInteres());
                }
            }
        }
        return lista;
    }

    private void cargarDatosLista() {
        String[] datos = archivoInteresRelacionador.leerDatosCSV();
        if (datos.length!=0) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                int idI = Integer.parseInt(cad[0]);
                int idR= Integer.parseInt(cad[1]);
                listaDeInteresesRelacionados.add(new InteresRelacionado(idI, idR));
            }
        }
    }
}