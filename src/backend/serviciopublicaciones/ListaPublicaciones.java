package backend.serviciopublicaciones;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListaPublicaciones {
    private GestorDeArchivoPublicacion archivo;
    private List<Publicacion> listaPublicaciones;
    private String fecha;

    public ListaPublicaciones() {
        fecha="";
        listaPublicaciones = new ArrayList<>();
        archivo = new GestorDeArchivoPublicacion("Publicaciones");
        cargarDatosLista();
    }

    public int agregarPublicacion(int idUsuario, String contenido) {
        String[] datos = archivo.leerDatosCSV();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        this.fecha=""+ LocalDateTime.now().format(formatter);
        int res=0;
        if (datos.length==0) {
            int id = 1;
            Publicacion actual = new Publicacion(id, idUsuario, contenido, fecha);
            listaPublicaciones.add(actual);
            archivo.escribirDatosEnCSV(id + "," + idUsuario + "," + "\""+contenido+"\"" + "," + actual.getFecha());
            res=id;
        } else {
            int id = datos.length + 1;
            Publicacion actual = new Publicacion(id, idUsuario, contenido, fecha);
            listaPublicaciones.add(actual);
            archivo.escribirDatosEnCSV(id + "," + idUsuario + "," + "\""+contenido+"\"" + "," + actual.getFecha());
            res=id;
        }
        return res;
    }

    public Publicacion buscarPublicacion(int idPublicacion) {
        Publicacion res = null;
        List<Integer> lista=listarPublicaciones();
        Collections.reverse(lista);
        for (int i = 0; i < lista.size(); i++) {
            int idP=lista.get(i);
            if (idP==idPublicacion) {
                res = listaPublicaciones.get(i);
            }
        }
        return res;
    }

    public List<Integer> listarPublicaciones() {
        List<Integer>lista=new ArrayList<>();
        String []datos=archivo.leerDatosCSV();
        if(datos.length!=0) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                lista.add(Integer.parseInt(cad[0]));
            }
        }
        Collections.reverse(lista);
        return lista;
    }


    private void actualizarDatos(String[] nombre) {
        archivo.escribirDeCerroEnCSV(nombre);
    }

    private void cargarDatosLista() {
        String[] datos = archivo.leerDatosCSV();
        if (datos.length!=0) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                String contenido = contruirContenido(cad);
                contenido=contenido.replace("\"","");
                int idP = Integer.parseInt(cad[0]);
                int idU = Integer.parseInt(cad[1]);
                Publicacion publi = new Publicacion(idP, idU, contenido,cad[cad.length-1]);
                listaPublicaciones.add(publi);
            }
        }
    }

    private String contruirContenido(String[] cad) {
        String res = "";
        for (int i = 2; i < cad.length - 1; i++) {
            if (i == cad.length - 2) {
                res += cad[i];
            } else {
                res += cad[i] + ",";
            }
        }
        return res;
    }

    public int buscarIdPublicacionPorIDUsuario(int idU){
        int res=0;
        System.out.println("buscarIdPublicacionPorIDUsuario");
        String[] datos = archivo.leerDatosCSV();
        if (datos.length!=0) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                if(cad[1].equals(""+idU)){
                    res = Integer.parseInt(cad[0]);
                    break;
                }
            }
        }
        return res;
    }
}
