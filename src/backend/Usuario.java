package backend;


public class Usuario {
    private int id;
    private String nombre;
    public Usuario(int id, String nombre){
        this.id=id;
        this.nombre=nombre;
    }
    public  Usuario(){
    }

    public String getNombre() {
        return nombre;
    }
    public int getId() {
        return id;
    }
/*
    private void setNombre(String nombre) {
        this.nombre = nombre;
    }



    private void setId(int id) {
         this.id=id;
    }
    public void agregarId() {
        this.id = getId();
    }
    public String formatoCSV(){
        String cad= ""+id+" ,"+nombre;
        return cad;
    }

    public Usuario crearObjetoActual(int id, String nombre){
        Usuario n=new Usuario();
        n.setId(id);
        n.setNombre(nombre);
        return  n;
    }
*/
}

