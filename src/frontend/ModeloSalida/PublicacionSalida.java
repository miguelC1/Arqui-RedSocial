package frontend.ModeloSalida;

public class PublicacionSalida {
    String nombre;
    String contenido;
    String fecha;
    String emociones;
    String intereses;
    public PublicacionSalida(String  nombre, String publicacion, String fecha){
        this.nombre=nombre;
        this.contenido =publicacion;
        this.fecha=fecha;

    }

    public String getContenido() {
        return contenido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }


    public String getEmociones() {
        return emociones;
    }

    public void agregarEmociones(String emociones) {
        this.emociones = emociones;
    }

    public String getIntereses() {
        return intereses;
    }

    public void agregarIntereses(String intereses) {
        this.intereses = intereses;
    }
}
