package backend.serviciorelacionador;

public class InteresRelacionado {
    private final int idInteres;
    private final int idRelacionado;

    public InteresRelacionado(int idInteres, int idRelacionado) {
        this.idInteres = idInteres;
        this.idRelacionado = idRelacionado;
    }
    public int getIdInteres(){
        return this.idInteres;
    }
    public int getIdRelacionado(){
        return this.idRelacionado;
    }
    public String toString(){
        return this.idInteres + "," + idRelacionado;
    }


}
