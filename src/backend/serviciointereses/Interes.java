package backend.serviciointereses;

public class Interes {
    private int id;
    private String nombreInteres;

    public Interes(int id,String  nombre){
        this.id=id;
        this.nombreInteres =nombre;
    }
    public String getNombreInteres() {
        return nombreInteres;
    }
}
