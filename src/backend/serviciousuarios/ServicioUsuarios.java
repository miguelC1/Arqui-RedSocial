package backend.serviciousuarios;

import backend.serviciousuarios.GestorDeArchivos;

import java.util.ArrayList;
import java.util.List;

public class ServicioUsuarios {
    GestorDeArchivos archivo;
    List<Usuario> listaDeUsuarios;

    public ServicioUsuarios(){
        archivo= new GestorDeArchivos("Usuarios");
        listaDeUsuarios = new ArrayList<>();
        //usuarioPorDefecto();
        cargarDatosLista();
        mostraLista();
    }

    private void usuarioPorDefecto(){
        int idActual=archivo.existeNombre("Maria Jimenes");
        if(idActual==0){
            archivo.escribirDatosEnCSV("1,Maria Jimenes");
        }
    }

    public int agregarUsuario(String nombre) {
        int res=archivo.existeNombre(nombre);
        String [] pro=archivo.leerDatosCSV();
        int id=0;
        if (pro[0].equals("")){
            id=1;
            archivo.escribirDatosEnCSV(id+","+nombre);
            listaDeUsuarios.add(new Usuario(id,nombre));
            res=id;
        }else {
            if(res==0){
                id = pro.length+1;
                archivo.escribirDatosEnCSV(id+","+nombre);
                listaDeUsuarios.add(new Usuario(id,nombre));
                res=id;
            }
        }
        return  res;
    }

    public void eliminarUsuario(String nombre) {
        for (int i = 0; i< listaDeUsuarios.size(); i++){
            Usuario usu= listaDeUsuarios.get(i);
            if(usu.getNombre().equals(nombre)){
                listaDeUsuarios.remove(i);
            }
        }

    }

    public Usuario buscarUsuario(int id) {
        Usuario usuario=null;
        for (int i=0; i<listarUsuarios().size(); i++){
            int idU= listarUsuarios().get(i);
            if(idU==id){
                usuario=new Usuario(id, listaDeUsuarios.get(i).getNombre());
            }
        }
        return usuario;
    }

    public List<Integer> listarUsuarios() {
        List<Integer>lista=new ArrayList<>();
        String []datos=archivo.leerDatosCSV();
        if(!datos[0].equals(""))
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
                listaDeUsuarios.add(new Usuario(idA, cad[1]));
            }
        }
    }
    private void mostraLista() {
        for (Usuario dato : listaDeUsuarios) {
            // System.out.println("son  "+dato.getId()+" "+dato.getNombre());
        }

    }
}