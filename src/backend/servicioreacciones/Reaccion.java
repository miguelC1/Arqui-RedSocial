package backend.servicioreacciones;

public class Reaccion {
    private Integer idPublicacion;
    private String nombre;
    private  Integer idUsuario;

    public Reaccion(int idP, String nombre, int idU){
        idPublicacion =idP;
        this.nombre=nombre;
        idUsuario =idU;
    }

    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

}
