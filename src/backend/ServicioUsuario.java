package backend;

import java.util.ArrayList;
import java.util.List;

public class ServicioUsuario {
    GestorDeArchivos archivo;

    public ServicioUsuario(){
        archivo= new GestorDeArchivos("Usuarios");
    }

    public void agregarDatosCSV(String nombre){
        String [] pro=archivo.leerDatosCSV();
        if (pro[0].equals("")){
            int id=1;
            archivo.escribirDatosEnCSV(id+","+nombre);
        }else {
            int id=pro.length+1;
            archivo.escribirDatosEnCSV(id+","+nombre);
        }
    }

    public String buscarIdUsuario(int idUsuario){
        String res=null;
        String []datos=archivo.leerDatosCSV();
        for (String dato: datos){
            String [] cad=dato.split(",");
            if (cad[0].equals(""+idUsuario)){
                res=cad[1];
            }
        }
        return res;
    }
    public List<Usuario> obtenerTodosUsuarios(){
        Usuario act=new Usuario();
        List<Usuario> lista=new ArrayList<>();
        String []datos=archivo.leerDatosCSV();
        for (String dato: datos){
            String [] cad=dato.split(",");
            act=act.crearObjetoActual(Integer.parseInt(cad[0]), cad[1]);
            lista.add(act);
        }
        return lista;
    }
}
