package backend.serviciointereses;

import java.util.ArrayList;
import java.util.List;

public class ListaIntereses {
    private GestorDeArchivoIntereses archivoInteres;
    private List<Interes> listaDeIntereses;
    private int id;
    public ListaIntereses() {
        listaDeIntereses = new ArrayList<>();
        archivoInteres = new GestorDeArchivoIntereses("Interes");
        cargarDatosLista();
        id=0;
    }

    public int agregarInteres(String nombreInteres) {
        String[] datosInteres = archivoInteres.leerDatosCSV();
        if (!verificarSiExisteInteres(nombreInteres)) {
            id = datosInteres.length + 1;
            Interes actual = new Interes(id, nombreInteres);
            listaDeIntereses.add(actual);
            archivoInteres.escribirDatosEnCSV(id + "," + nombreInteres);
        } else {
            System.out.println("YA EXISTE INTERES");
        }
        return id;
    }


    public Interes buscarInteres(int idInteres) {
        Interes res = null;
        String []datos = archivoInteres.leerDatosCSV();
        if(datos.length!=0) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                int idI=Integer.parseInt(cad[0]);
                if(idInteres==idI){
                    res= new Interes(idI,cad[1]);
                }
            }
        }
        return res;
    }

    public List<Integer> listarIntereses() {
        List<Integer>lista=new ArrayList<>();
        String []datos = archivoInteres.leerDatosCSV();
        if(datos.length!=0) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                lista.add(Integer.parseInt(cad[0]));
            }
        }
        return lista;
    }

    private void cargarDatosLista() {
        String[] datos = archivoInteres.leerDatosCSV();
        if (datos.length!=0) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                int idI = Integer.parseInt(cad[0]);
                Interes interes = new Interes(idI, cad[1]);
                listaDeIntereses.add(interes);
            }
        }
    }
    private boolean verificarSiExisteInteres(String nombre){
        boolean res=false;
        String [] datos= archivoInteres.leerDatosCSV();
        for (String dato: datos){
            String [] cad=dato.split(",");
            if(nombre.equals(cad[1])){
                res=true;
                id=Integer.parseInt(cad[0]);
            }
        }
        return  res;
    }

}
