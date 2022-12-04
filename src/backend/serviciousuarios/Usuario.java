package backend.serviciousuarios;



public class Usuario {
    private int id;
    private String nombre;
    private TipoUsuario estado;
    public Usuario(int id, String nombre){
        this.id=id;
        this.nombre=nombre;
        this.estado=TipoUsuario.CANDIDATO;
    }
    public String getNombre() {
        return nombre;
    }
    public boolean esUsuario(){
        boolean res=false;
        if(estado.equals(TipoUsuario.USUARIO)){
            res=true;
        }
        return res;
    }
    public void cambiarAUsuario(){
        this.estado=TipoUsuario.USUARIO;
    }




    private TipoUsuario getTipoUsuario(){
        return estado;
    }

}

