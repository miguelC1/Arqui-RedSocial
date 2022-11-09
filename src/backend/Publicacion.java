package backend;

import java.time.LocalDateTime;

public class Publicacion {
    private static int id;
    private int idUsuario;
    private String contenido;
    private  LocalDateTime fecha;
    public Publicacion( Usuario usuario, String contenido ){
        this.id++;
        this.contenido=contenido;
        this.fecha= LocalDateTime.now();
    }
    public Publicacion(){
        this.fecha= LocalDateTime.now();
    }

    public Publicacion creacionObjetoActual(int id, int idUsuario, String contenido, LocalDateTime fecha){
        Publicacion actual= new Publicacion();
        actual.setId(id);
        actual.setIdUsuario(idUsuario);
        actual.setContenido(contenido);
        actual.setFecha(fecha);
        return actual;
    }

    public int getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void agregarId() {
        this.id = getId();
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
