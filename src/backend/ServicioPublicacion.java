package backend;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServicioPublicacion {

    GestorDeArchivos archivo;
    public ServicioPublicacion(){
        archivo= new GestorDeArchivos("Publicaciones");
    }

    public void agregarDatosCSV(int id,int idU,String contenido, LocalDateTime fecha){
        archivo.escribirDatosEnCSV(id+","+idU+","+contenido+","+fecha);
    }

    public String buscarPorIdPublicacionNombre(int idPublicacion){
        String res="";
        String []datos=archivo.leerDatosCSV();
        for (String dato: datos){
            String [] cad=dato.split(",");
            if (cad[0].equals(""+idPublicacion)){
                res=cad[1];
            }
        }
        return res;
    }
    public Publicacion buscarPorIdPublicacionObejto(int idPublicacion){
        Publicacion res= new Publicacion();
        String []datos=archivo.leerDatosCSV();
        for (String dato: datos){
            String [] cad=dato.split(",");
            if (cad[0].equals(""+idPublicacion)){
                int id=Integer.parseInt(cad[0]);
                int idU=Integer.parseInt(cad[1]);
                LocalDateTime f= LocalDateTime.parse(cad[3]);
                res.creacionObjetoActual(id, idU, cad[2],f);
            }
        }
        return res;
    }

    public List<Publicacion> obtenerTodasPublicaciones(){
        Publicacion act=new Publicacion();
        List<Publicacion> lista=new ArrayList<>();
        String []datos=archivo.leerDatosCSV();

        for (String dato: datos){
            String [] cad=dato.split(",");
            String contenido=contruirContenido(cad);
            LocalDateTime f=LocalDateTime.parse(cad[cad.length-1]);

            act=act.creacionObjetoActual(Integer.parseInt(cad[0]), Integer.parseInt(cad[1]),contenido,f);
            lista.add(act);
        }
        return lista;
    }
    private String contruirContenido(String [] cad){
        String res ="";
        for (int i=2; i<cad.length-1; i++){
            if (i== cad.length-2) {
               res+=cad[i];
            }else {
                res+=cad[i]+",";
            }
        }
        return res;
    }
}
