package backend;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServicioReaccion {
    GestorDeArchivos archivo;
    public ServicioReaccion(){
        archivo= new GestorDeArchivos("Reacciones");
    }

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

}
