package backend.serviciousuarios;

import java.util.ArrayList;
import java.util.List;

public class ListaUsuarios {
    private GestorDeArchivoUsuario archivo;
    private List<Usuario> listaDeUsuarios;

    public ListaUsuarios(){
        archivo= new GestorDeArchivoUsuario("Usuarios");
        listaDeUsuarios = new ArrayList<>();
        cargarDatosLista();
    }
    public int agregarUsuario(String nombre) {
        int res=archivo.existeNombre(nombre);
        String [] datos=archivo.leerDatosCSV();
        int id=0;
        if (datos[0].length()==0){
            id=1;
            archivo.escribirDatosEnCSV(id+","+nombre+","+TipoUsuario.CANDIDATO.name());
            //listaUsuarios.add(new Usuario(id,nombre));
            res=id;
        }else {
            if(res==0){
                id = datos.length+1;
                archivo.escribirDatosEnCSV(id+","+nombre+","+ TipoUsuario.CANDIDATO.name());
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
        actualizarDatos(converiEnCadena());
    }

    public Usuario buscarUsuario(int id) {
        Usuario usuario=null;
        for (int i=0; i<listarUsuarios().size(); i++){
            int idU= listarUsuarios().get(i);
            if(idU==id){
                usuario=listaDeUsuarios.get(i);
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
    public void cambiarAUsuario(int id) {
        Usuario usuario=buscarUsuario(id);
        for(int i=0; i< listaDeUsuarios.size(); i++){
            if(listaDeUsuarios.get(i).getNombre().equals(usuario.getNombre())){
                usuario.cambiarAUsuario();
                listaDeUsuarios.set(i,usuario);
            }
        }
        actualizarDatos(converiEnCadena());
    }


    private void actualizarDatos(String [] nombre) {
        archivo.escribirDeCerroEnCSV(nombre);
    }

    private void cargarDatosLista() {
        String [] datos=archivo.leerDatosCSV();
        if(!datos[0].equals("")) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                int idA = Integer.parseInt(cad[0]);
                Usuario nuevo;
                if(TipoUsuario.USUARIO.name().equals(cad[2])){
                    nuevo=new Usuario(idA, cad[1]);
                    nuevo.cambiarAUsuario();
                    listaDeUsuarios.add(nuevo);
                }
                else{
                    listaDeUsuarios.add(new Usuario(idA, cad[1]));
                }

            }
        }
    }

    private String [] converiEnCadena(){
        String [] res=new String[listaDeUsuarios.size()];
        List<Integer>listaIdUSU=listarUsuarios();
        int i=0;

        for (Usuario usuario: listaDeUsuarios) {
            String cad="";
            if(usuario.esUsuario()){
                cad=listaIdUSU.get(i)+","+usuario.getNombre()+","+TipoUsuario.USUARIO.name();
            }else{
                cad=listaIdUSU.get(i)+","+usuario.getNombre()+","+TipoUsuario.CANDIDATO.name();
            }
            res[i]=cad;
            i++;
        }
        return res;
    }

}
