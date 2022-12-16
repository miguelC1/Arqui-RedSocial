package backend.serviciointereses;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ListaIntereses {
    private GestorDeArchivoIntereses archivoInteres;
    private GestorDeArchivoIntereses archivoInteresUsuarios;
    private GestorDeArchivoIntereses archivoInteresPublicacion;
    private List<Interes> listaDeIntereses;
    private String fecha;

    public ListaIntereses() {
        listaDeIntereses = new ArrayList<>();
        archivoInteres = new GestorDeArchivoIntereses("Interes");
        archivoInteresUsuarios = new GestorDeArchivoIntereses("InteresUsuarios");
        archivoInteresPublicacion = new GestorDeArchivoIntereses("InteresPublicacion");
        cargarDatosLista();

    }

    public int agregarInteres(String nombreInteres) {
        String[] datosInteres = archivoInteres.leerDatosCSV();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.fecha=""+ LocalDateTime.now().format(formatter);
        int id=1;
        if (datosInteres.length==0) {
            Interes actual = new Interes(id,nombreInteres, fecha);
            listaDeIntereses.add(actual);
            archivoInteres.escribirDatosEnCSV(id + "," + nombreInteres + "," + actual.getFecha());
        } else {
            if(!verificarSiExisteInteres(nombreInteres)){
                id = datosInteres.length + 1;
                Interes actual = new Interes(id,nombreInteres, fecha);
                listaDeIntereses.add(actual);
                archivoInteres.escribirDatosEnCSV(id + "," + nombreInteres + "," + actual.getFecha());
            }else {
                System.out.println("YA EXISTE INTERES");
            }
        }
        return id;
    }


    public Interes buscarInteres(int idInteres) {
        Interes res = null;
        List<Integer> lista= listarIntereses();
        for (int i = 0; i < lista.size(); i++) {
            int idP=lista.get(i);
            if (idP==idInteres) {
                res = listaDeIntereses.get(i);
            }
        }
        return res;
    }

    public List<Integer> listarIntereses() {
        List<Integer>lista=new ArrayList<>();
        String []datos= archivoInteres.leerDatosCSV();
        if(datos.length!=0) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                lista.add(Integer.parseInt(cad[0]));
            }
        }
        return lista;
    }


    private void actualizarDatos(String[] nombre) {
        archivoInteres.escribirDeCerroEnCSV(nombre);
    }

    private void cargarDatosLista() {
        String[] datos = archivoInteres.leerDatosCSV();
        if (datos.length!=0) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                int idI = Integer.parseInt(cad[0]);
                Interes interes = new Interes(idI, cad[1],cad[2]);
                listaDeIntereses.add(interes);
            }
        }
    }

    public void agregarInteresUsuarios(int idInteres,int idU){
        String[] datosInteresUsuarios= archivoInteresUsuarios.leerDatosCSV();
        if (datosInteresUsuarios.length==0) {
            archivoInteresUsuarios.escribirDatosEnCSV(idInteres + "," + idU);
        } else {
            archivoInteresUsuarios.escribirDatosEnCSV(idInteres + "," + idU);
        }
    }

    public void agregarInteresPublicaciones(int idInteres,int idP){
        String[] datosInteresPublicaciones= archivoInteresPublicacion.leerDatosCSV();
        if (datosInteresPublicaciones.length==0) {
            archivoInteresPublicacion.escribirDatosEnCSV(idInteres + "," + idP);
        } else {
            archivoInteresPublicacion.escribirDatosEnCSV(idInteres + "," + idP);
        }
    }
    private boolean verificarSiExisteInteres(String nombre){
        boolean res=false;
        for (Interes interes: listaDeIntereses){
            if(interes.getNombre().equals(nombre)){
                res=true;
            }
        }
        return  res;
    }

    public int buscaIDInteresPorPublicacion(int idPublicacion){
        int res=0;
        String[] datos = archivoInteresPublicacion.leerDatosCSV();
        if (datos.length!=0) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                if(cad[1].equals(""+idPublicacion)){
                    res = Integer.parseInt(cad[0]);
                    break;
                }
            }
        }
        return res;
    }
}
