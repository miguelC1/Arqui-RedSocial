package backend;
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


    public void eliminarReacccion(int idP, int idU) {
        for (int i=0; i< listaReacciones.size(); i++){
            Reacciones rec=listaReacciones.get(i);
            if(rec.getIdPublicacion()==idP && rec.getIdUsuario()==idU){
                listaReacciones.remove(i);
            }
        }

    }


    public List<Reacciones> listarReacciones() {
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
