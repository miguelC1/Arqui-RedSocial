package backend.serviciointereses;

public class Interes {
    private int id;
    private String nombre;
    private String fecha;

    public Interes(int id,String  nombre,String fecha){
        this.id=id;
        this.nombre=nombre;
        this.fecha=fecha;

    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }
}
