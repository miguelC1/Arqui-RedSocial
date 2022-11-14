package backend;
import java.time.LocalDateTime;

public class Publicacion {
    private int id;
    private int idUsuario;
    private String contenido;
    private  String fecha;
    public Publicacion(int idPublicacion, int idUsuario, String contenido, String fecha ){
        this.id=idPublicacion;
        this.idUsuario=idUsuario;
        this.contenido=contenido;
        this.fecha=fecha;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public String getContenido() {
        return contenido;
    }
    public String getFecha() {
        return fecha;
    }

}
