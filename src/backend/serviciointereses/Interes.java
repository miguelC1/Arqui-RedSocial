package backend.serviciointereses;

public class Interes {
    private int id;
    private String nombreInteres;

    public Interes(int id,String  nombreInteres){
        this.id=id;
        this.nombreInteres =nombreInteres;
    }
    public String getNombreInteres() {
        return nombreInteres;
    }
}
