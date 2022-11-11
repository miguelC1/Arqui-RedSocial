package backend;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServicioPublicacion {

    GestorDeArchivos archivo;
    List<Publicacion> listaPublicaciones;

    public ServicioPublicacion() {
        listaPublicaciones = new ArrayList<>();
        archivo = new GestorDeArchivos("Publicaciones");
        cargarDatosLista();
    }

    public void agregarPublicacion(int idU, String contenido) {
        String[] pro = archivo.leerDatosCSV();
        if (pro[0].equals("")) {
            int id = 1;
            Publicacion actual = new Publicacion(id, idU, contenido);
            listaPublicaciones.add(actual);
            archivo.escribirDatosEnCSV(id + "," + idU + "," + contenido + "," + actual.getFecha());
        } else {
            int id = pro.length + 1;
            Publicacion actual = new Publicacion(id, idU, contenido);
            listaPublicaciones.add(actual);
            archivo.escribirDatosEnCSV(id + "," + idU + "," + contenido + "," + actual.getFecha());
        }
    }

    public void eliminarPublicacion(int idP) {
        for (int i = 0; i < listaPublicaciones.size(); i++) {
            Publicacion publicacion = listaPublicaciones.get(i);
            if (publicacion.getId() == idP) {
                listaPublicaciones.remove(i);
            }
        }

    }

    public Publicacion buscarPublicacion(int idP) {
        Publicacion res = null;
        for (int i = 0; i < listaPublicaciones.size(); i++) {
            Publicacion publicacion = listaPublicaciones.get(i);
            if (publicacion.getId() == idP) {
                res = listaPublicaciones.get(i);
            }
        }
        return res;
    }

    public List<Publicacion> getListaPublicaciones() {
        return listaPublicaciones;
    }

    public void actualizarDatos(String[] nombre) {
        archivo.escribirDeCerroEnCSV(nombre);
    }

    private void cargarDatosLista() {
        String[] datos = archivo.leerDatosCSV();
        if (!datos[0].equals("")) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                String contenido = contruirContenido(cad);
                LocalDateTime f = LocalDateTime.parse(cad[cad.length - 1]);
                int idP = Integer.parseInt(cad[0]);
                int idU = Integer.parseInt(cad[1]);
                Publicacion publi = new Publicacion(idP, idU, contenido);
                publi.setFecha(f);
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


}

