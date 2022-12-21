package backend.serviciointeresusuario;

import backend.serviciointereses.GestorDeArchivoIntereses;

import java.util.ArrayList;
import java.util.List;

public class ListaInteresUsuario {

    private List<InteresUsuario> listaDeInteresesDeUsuario;
    private GestorDeArchivoInteresUsuario archivoInteresUsuaio;

    public ListaInteresUsuario() {
        listaDeInteresesDeUsuario = new ArrayList<>();
        archivoInteresUsuaio = new GestorDeArchivoInteresUsuario("InteresUsuario");
        cargarDatosLista();
    }

    public void agregarInteresUsuario(int idInteres, int idUsuario) {
        listaDeInteresesDeUsuario.add(new InteresUsuario(idInteres, idUsuario));
        archivoInteresUsuaio.escribirDatosEnCSV(idInteres + "," + idUsuario);
    }

    public List<Integer> listarInteresesPorUsuario(int idUsuario) {
        List<Integer>lista=new ArrayList<>();
        String []datos = archivoInteresUsuaio.leerDatosCSV();
        if(datos.length!=0) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                int idP= Integer.parseInt(cad[1]);
                if(idP==idUsuario){
                    int idI=Integer.parseInt(cad[0]);
                    lista.add(idI);
                }
            }
        }
        return lista;
    }
    private void cargarDatosLista() {
        String[] datos = archivoInteresUsuaio.leerDatosCSV();
        if (datos.length!=0) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                int idI = Integer.parseInt(cad[0]);
                int idP= Integer.parseInt(cad[1]);
                listaDeInteresesDeUsuario.add(new InteresUsuario(idI, idP));
            }
        }
    }
}

