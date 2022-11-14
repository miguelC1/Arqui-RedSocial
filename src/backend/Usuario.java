package backend;



public class Usuario {
    private int id;
    private String nombre;
    public Usuario(int id, String nombre){
        this.id=id;
        this.nombre=nombre;
    }
    public String getNombre() {
        return nombre;
    }

}

