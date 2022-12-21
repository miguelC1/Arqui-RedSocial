package backend.serviciointerespublicacion;

import backend.serviciointereses.GestorDeArchivoIntereses;

import java.util.ArrayList;
import java.util.List;

public class ListaInteresPublicacion {
    private List<InteresPublicacion> listaDeInteresesDePublicacion;
    private GestorDeArchivoInteresPublicacion archivoInteresPublicacion;

    public ListaInteresPublicacion() {
        listaDeInteresesDePublicacion = new ArrayList<>();
        archivoInteresPublicacion = new GestorDeArchivoInteresPublicacion("InteresPublicacion");
        cargarDatosLista();
    }

    public void agregarInteresPublicacion(int idInteres, int idPublicacion) {
        listaDeInteresesDePublicacion.add(new InteresPublicacion(idInteres, idPublicacion));
        archivoInteresPublicacion.escribirDatosEnCSV(idInteres + "," + idPublicacion);
    }

    public List<Integer> listarInteresesPorPublicacion(int idPublicacion) {
        List<Integer>lista=new ArrayList<>();
        String []datos = archivoInteresPublicacion.leerDatosCSV();
        if(datos.length!=0) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                int idP= Integer.parseInt(cad[1]);
                if(idP==idPublicacion){
                    int idI=Integer.parseInt(cad[0]);
                    lista.add(idI);
                }
            }
        }
        return lista;
    }
    private void cargarDatosLista() {
        String[] datos = archivoInteresPublicacion.leerDatosCSV();
        if (datos.length!=0) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                int idI = Integer.parseInt(cad[0]);
                int idP= Integer.parseInt(cad[1]);
                listaDeInteresesDePublicacion.add(new InteresPublicacion(idI, idP));
            }
        }
    }
}