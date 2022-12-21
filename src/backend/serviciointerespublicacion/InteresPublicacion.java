package backend.serviciointerespublicacion;

public class InteresPublicacion {
    private int idInteres;
    private int idPublicacion;
    public InteresPublicacion(int idInteres, int idPublicacion){
        this.idInteres=idInteres;
        this.idPublicacion=idPublicacion;
    }

    public int getIdInteres() {
        return idInteres;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }
}
