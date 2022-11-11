package backend;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServicioReaccion {
    GestorDeArchivos archivo;
    List<Reacciones> listaReacciones;

    public ServicioReaccion(){
        archivo= new GestorDeArchivos("Reacciones");
        listaReacciones=new ArrayList<>();
        cargarDatosLista();
    }

    public void agregarReacciones(int idP,String nombre, int idU) {
        String [] pro=archivo.leerDatosCSV();
        if (pro[0].equals("")){
            archivo.escribirDatosEnCSV(idP+","+nombre+","+idU);
            listaReacciones.add(new Reacciones(idP,nombre,idU));
        }else {
            if(verifivarReaccionUsuario(idP,idU)){
                eliminarReacccion(idP, idU);
                listaReacciones.add(new Reacciones(idP,nombre,idU));
                actualizarDatos(converiEnCadena());
            }
            else{
                archivo.escribirDatosEnCSV(idP+","+nombre+","+idU);
                listaReacciones.add(new Reacciones(idP,nombre,idU));
            }

        }
    }

    public void eliminarReacccion(int idP, int idU) {
        for (int i=0; i< listaReacciones.size(); i++){
            Reacciones rec=listaReacciones.get(i);
            if(rec.getIdPublicacion()==idP && rec.getIdUsuario()==idU){
                listaReacciones.remove(i);
            }
        }

    }


    public List<Reacciones> getListaReacciones() {
        return listaReacciones;
    }

    public void actualizarDatos(String [] nombre) {
        archivo.escribirDeCerroEnCSV(nombre);
    }

    private void cargarDatosLista() {
        String [] datos=archivo.leerDatosCSV();
        if(!datos[0].equals("")) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                int idP = Integer.parseInt(cad[0]);
                int idU = Integer.parseInt(cad[2]);
                listaReacciones.add(new Reacciones(idP, cad[1], idU));
            }
        }
    }

    public int contarReaccionesConIdyNombre(int idP, String nombre){
        int res=0;
        for (Reacciones reaccion: listaReacciones) {
            if(reaccion.getIdPublicacion()==idP && reaccion.getNombre().equals(nombre)){
                res++;
            }
        }
        return res;
    }
    private boolean verifivarReaccionUsuario(int idP, int idU){
        boolean res=false;
        for (Reacciones reaccion: listaReacciones) {
            if(reaccion.getIdPublicacion()==idP && reaccion.getIdUsuario()==idU){
                res=true;
                break;
            }
        }
        return res;
    }
    private String [] converiEnCadena(){
        String [] res=new String[listaReacciones.size()];
        int i=0;
        for (Reacciones reaccion: listaReacciones) {
            String cad=reaccion.getIdPublicacion()+","+reaccion.getNombre()+","+ reaccion.getIdUsuario();
            res[i]=cad;
            i++;
        }
        return res;
    }


/*
    public void agregarDatosCSV(int idPublicacion,String nombre, int idUsuario){
        archivo.escribirDatosEnCSV(idPublicacion+","+nombre+","+idUsuario);
    }


    public List<Reacciones> buscarPorNombre(int  idPublicacion){
        List<Reacciones> res=new ArrayList<>();
        Reacciones nuevo;
        String []datos=archivo.leerDatosCSV();
        for (String dato: datos){
            String [] cad=dato.split(",");
            if (cad[0].equals(""+idPublicacion)){
                nuevo=new Reacciones(Integer.parseInt(cad[0]),cad[1],Integer.parseInt(cad[2]));
                res.add(nuevo);
            }
        }
        return res;
    }
    public int contarReacion(int  idPublicacion, String nombre){
        int res=0;
        Reacciones nuevo;
        String []datos=archivo.leerDatosCSV();
        for (String dato: datos){
            String [] cad=dato.split(",");
            if(cad[0].equals(""+idPublicacion) && cad[2].equals(nombre)){
                res++;
            }
        }
        return res;
    }

    public List<Reacciones> obtenerTodasReacciones(){
        List<Reacciones> res=new ArrayList<>();
        Reacciones nuevo;
        String []datos=archivo.leerDatosCSV();
        for (String dato: datos){
            String [] cad=dato.split(",");
            nuevo=new Reacciones(Integer.parseInt(cad[0]),cad[1],Integer.parseInt(cad[2]));
            res.add(nuevo);
        }
        return res;
    }
*/
}
