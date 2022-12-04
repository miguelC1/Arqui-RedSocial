package backend.serviciopublicaciones;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ServicioPublicaciones {

    GestorDeArchivos archivo;
    List<Publicacion> listaPublicaciones;
    String  fecha;

    public ServicioPublicaciones() {
        fecha="";
        listaPublicaciones = new ArrayList<>();
        archivo = new GestorDeArchivos("Publicaciones");
        //agregarPublicacionDefecto();
        cargarDatosLista();
    }

    public int agregarPublicacion(int idUsuario, String contenido) {
        String[] pro = archivo.leerDatosCSV();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.fecha=""+LocalDateTime.now().format(formatter);

        System.out.println(fecha);
        int res=0;
        if (pro[0].equals("")) {
            int id = 1;
            Publicacion actual = new Publicacion(id, idUsuario, contenido, fecha);
            listaPublicaciones.add(actual);
            archivo.escribirDatosEnCSV(id + "," + idUsuario + "," + "\""+contenido+"\"" + "," + actual.getFecha());
            res=id;
        } else {
            int id = pro.length + 1;
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
                //System.out.println(idP+" buscando"+idPublicacion+" ===> "+listaPublicaciones.get(i).getContenido());
                res = listaPublicaciones.get(i);
            }
        }
        return res;
    }

    public List<Integer> listarPublicaciones() {
        List<Integer>lista=new ArrayList<>();
        String []datos=archivo.leerDatosCSV();
        if(!datos[0].equals("")) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                lista.add(Integer.parseInt(cad[0]));
            }
        }
        Collections.reverse(lista);
        //Collections.reverse(listaPublicaciones);
        return lista;
    }


    private void actualizarDatos(String[] nombre) {
        archivo.escribirDeCerroEnCSV(nombre);
    }

    private void cargarDatosLista() {
        String[] datos = archivo.leerDatosCSV();
        if (!datos[0].equals("")) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                String contenido = contruirContenido(cad);
                contenido=contenido.replace("\"","");
                // LocalDateTime f = LocalDateTime.parse(cad[cad.length - 1]);
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

    private void agregarPublicacionDefecto() {

        String contenido="1,1,\"El Pique Macho y el arroz, no le va, no?\",2022-11-06T19:12:04.213293600";
        if(archivo.existeDato(contenido)==0) {
            archivo.escribirDatosEnCSV(contenido);
        }
    }

}

