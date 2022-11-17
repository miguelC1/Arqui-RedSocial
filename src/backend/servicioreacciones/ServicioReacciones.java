package backend.servicioreacciones;
import backend.servicioreacciones.GestorDeArchivos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioReacciones {
    GestorDeArchivos archivo;
    List<Reacciones> listaReacciones;

    public ServicioReacciones(){
        archivo= new GestorDeArchivos("Reacciones");
        listaReacciones=new ArrayList<>();
        cargarDatosLista();
    }

    public void agregarReacciones(int idPublicacion, int idUsuario, Emocion reaccion) {
        String [] pro=archivo.leerDatosCSV();
        String r= reaccion.name();
        if (pro[0].equals("")){
            archivo.escribirDatosEnCSV(idPublicacion+","+r+","+idUsuario);
            listaReacciones.add(new Reacciones(idPublicacion,r,idUsuario));
        }else {
            if(verifivarReaccionUsuario(idPublicacion,idUsuario)){
                eliminarReacccion(idPublicacion, idUsuario);
                listaReacciones.add(new Reacciones(idPublicacion,r,idUsuario));
                actualizarDatos(converiEnCadena());
            }
            else{
                archivo.escribirDatosEnCSV(idPublicacion+","+reaccion+","+idUsuario);
                listaReacciones.add(new Reacciones(idPublicacion,r,idUsuario));
            }

        }
    }


    public Map<Emocion,Integer> listarResumenReacciones(int idPublicacion) {
        Map<Emocion,Integer> map= new HashMap<>();
        for (int i=0; i<Emocion.values().length; i++){
            int cantidad=contarReaccionesConIdyNombre(idPublicacion,Emocion.values()[i].name());
            Emocion actual=Emocion.values()[i];
            map.put(actual,cantidad);
        }

        return map;
    }


    private void eliminarReacccion(int idP, int idU) {
        for (int i=0; i< listaReacciones.size(); i++){
            Reacciones rec=listaReacciones.get(i);
            if(rec.getIdPublicacion()==idP && rec.getIdUsuario()==idU){
                listaReacciones.remove(i);
            }
        }

    }


    private List<Reacciones> listarReacciones() {
        return listaReacciones;
    }

    private void actualizarDatos(String [] nombre) {
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

    private int contarReaccionesConIdyNombre(int idP, String nombre){
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

}
