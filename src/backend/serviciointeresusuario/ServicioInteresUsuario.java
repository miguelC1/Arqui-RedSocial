package backend.serviciointeresusuario;

import java.util.List;

public class ServicioInteresUsuario {
    private ListaInteresUsuario listaInteresUsuario;

    public ServicioInteresUsuario(){
        listaInteresUsuario=new ListaInteresUsuario();
    }

    public void agregarInteresUsuario(int idInteres, int idUsuario) {
        listaInteresUsuario.agregarInteresUsuario(idInteres,idUsuario);
    }

    public List<Integer> listarInteresesPorUsuario(int idUsuario){
        return listaInteresUsuario.listarInteresesPorUsuario(idUsuario);
    }

}
