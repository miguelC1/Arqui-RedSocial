package backend;

import java.util.ArrayList;
import java.util.List;

public class ServicioUsuario {
    GestorDeArchivos archivo;
    List<Usuario>listaUsuarios;

    public ServicioUsuario(){
        archivo= new GestorDeArchivos("Usuarios");
        listaUsuarios= new ArrayList<>();
        cargarDatosLista();
        mostraLista();
    }

    public void agregarUsuario(String nombre) {
        String [] pro=archivo.leerDatosCSV();
        if (pro[0].equals("")){
            int id=1;
            archivo.escribirDatosEnCSV(id+","+nombre);
            listaUsuarios.add(new Usuario(id,nombre));
        }else {
            int id=pro.length+1;
            archivo.escribirDatosEnCSV(id+","+nombre);
            listaUsuarios.add(new Usuario(id,nombre));
        }
    }

    public void eliminarUsuario(String nombre) {
        for (int i=0; i<listaUsuarios.size(); i++){
            Usuario usu=listaUsuarios.get(i);
            if(usu.getNombre().equals(nombre)){
                listaUsuarios.remove(i);
            }
        }

    }

    public String buscarUsuario(int id) {
        String res="";
        for (int i=0; i<listaUsuarios.size(); i++){
            Usuario usu=listaUsuarios.get(i);
            if(usu.getId()==id){
                res=usu.getNombre();
            }
        }
        return res;
    }

    public List<Integer> getUsuarios() {
        List<Integer>lista=new ArrayList<>();
        String []datos=archivo.leerDatosCSV();
        for (String dato: datos){
            String [] cad=dato.split(",");
            lista.add(Integer.parseInt(cad[0]));

        }
        return lista;
    }

    public void actualizarDatos(String [] nombre) {
        archivo.escribirDeCerroEnCSV(nombre);
    }

    private void cargarDatosLista() {
        String [] datos=archivo.leerDatosCSV();
        if(!datos[0].equals("")) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                int idA = Integer.parseInt(cad[0]);
                listaUsuarios.add(new Usuario(idA, cad[1]));
            }
        }
    }
    private void mostraLista() {
        for (Usuario dato : listaUsuarios) {
            System.out.println("son  "+dato.getId()+" "+dato.getNombre());
        }

    }


/*
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
    }*/
}
