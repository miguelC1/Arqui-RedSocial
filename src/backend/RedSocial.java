package backend;

import java.time.LocalDateTime;
import java.util.List;

public class RedSocial {
    private Usuario usuario;
    ServicioPublicacion servicioPublicacion;
    ServicioUsuario servicioUsuario;
    ServicioReaccion servicioReaccion;
    //private List<Publicacion> listaPublicaciones;
    //private List<Usuario> listaUsuarios;
    //private List<Reacciones> listaReacciones;
   // Archivo aPublicaion=new Archivo("Publicaciones");
    //Archivo aUsuario= new Archivo("Usuarios");

    public RedSocial(Usuario usuario){
        this.usuario=usuario;
        //listaPublicaciones=new ArrayList<>();
        //listaUsuarios=new ArrayList<>();
        //listaReacciones=new ArrayList<>();
        servicioReaccion= new ServicioReaccion();
        servicioPublicacion =new ServicioPublicacion();
        servicioUsuario=new ServicioUsuario();
        //agregarPublicacion(publicacionDefecto());
        //lecturaArchivos();
        publicacionDefecto();
    }
    public RedSocial(){
        servicioReaccion= new ServicioReaccion();
        servicioPublicacion =new ServicioPublicacion();
        servicioUsuario=new ServicioUsuario();
        publicacionDefecto();
        /*listaPublicaciones=new ArrayList<>();
        listaUsuarios=new ArrayList<>();
        listaReacciones=new ArrayList<>();
        agregarPublicacion(publicacionDefecto());
        this.usuario=listaUsuarios.get(0);
        lecturaArchivos();*/
    }

    public void crearUsuario(String nombreCompleto){
        Usuario nuevo=new Usuario(nombreCompleto);
        servicioUsuario.agregarDatosCSV(nuevo.getNombre());
        //listaUsuarios.add(nuevo);
    }

    public void crearPublicacion(String contenido){
        Publicacion nuevo= new Publicacion(usuarioAtual(), contenido);
        nuevo.agregarId();
        System.out.println("REd Social "+usuarioAtual().getId());
        nuevo.setIdUsuario(usuarioAtual().getId());
        System.out.println("REd Social idagregadiUs "+nuevo.getIdUsuario());
        servicioPublicacion.agregarDatosCSV(nuevo.getId(),nuevo.getIdUsuario(), nuevo.getContenido(),nuevo.getFecha());

    }

    public void crearReacion(int idPublicaion,String nombre,int idUsuario){
        Reacciones nuevo=new Reacciones(idPublicaion, nombre, idUsuario);
        servicioReaccion.agregarDatosCSV(nuevo.getId(), nuevo.getNombre(),nuevo.getIdUsuario());
    }
     public List obtenerListasDePublicaciones() {
        return servicioPublicacion.obtenerTodasPublicaciones();
    }
    public List obtenerListasDeUsuarios() {
        return servicioUsuario.obtenerTodosUsuarios();
    }

    public List obtenerListasReacciones() {
        return servicioReaccion.obtenerTodasReacciones();
    }
    public void agregarPublicacion(Publicacion nuevaP){
        servicioPublicacion.agregarDatosCSV(nuevaP.getId(),nuevaP.getIdUsuario(),nuevaP.getContenido(), nuevaP.getFecha());
    }

    public  Usuario usuarioAtual() {
        return this.usuario;
    }
    public  void cambiarUsuario(Usuario usuario) {
        this.usuario=usuario;
    }

    private void publicacionDefecto(){
        Usuario usu=new Usuario("Maria Jimenes");
        //usu.agregarId();
        //servicioUsuario.agregarDatosCSV(usu.getId(), usu.getNombre());
        servicioUsuario.agregarDatosCSV(usu.getNombre());
        cambiarUsuario(usu);
        Publicacion p1=new Publicacion(usu,"El Pique Macho y el arroz, no le va, no? ");
        p1.agregarId();
        p1.setIdUsuario(usu.getId());
        p1.setFecha(LocalDateTime.parse("2022-11-06T19:12:04.213293600"));
        servicioPublicacion.agregarDatosCSV(p1.getId(),p1.getIdUsuario(), p1.getContenido(),p1.getFecha());
    }



}
