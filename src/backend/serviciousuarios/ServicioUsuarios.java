package backend.serviciousuarios;

import java.util.List;

public class ServicioUsuarios {
    ListaUsuarios listaUsuarios;

    public ServicioUsuarios(){
       listaUsuarios= new ListaUsuarios();
    }

    public int agregarUsuario(String nombre) {
        int res=listaUsuarios.agregarUsuario(nombre);
        return  res;
    }

    public void eliminarUsuario(String nombre) {
       listaUsuarios.eliminarUsuario(nombre);
    }

    public Usuario buscarUsuario(int id) {
        Usuario usuario=listaUsuarios.buscarUsuario(id);
        return usuario;
    }

    public List<Integer> listarUsuarios() {
        return listaUsuarios.listarUsuarios();
    }

    public void cambiarAUsuario(int id) {
        listaUsuarios.cambiarAUsuario(id);
    }


}
