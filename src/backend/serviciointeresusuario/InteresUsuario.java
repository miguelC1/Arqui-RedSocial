package backend.serviciointeresusuario;

public class InteresUsuario {
    private int idInteres;
    private int idUsuario;
    public InteresUsuario(int idInteres, int idUsuario){
        this.idInteres= idInteres;
        this.idUsuario=idUsuario;
    }

    public int getIdInteres() {
        return idInteres;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
}
