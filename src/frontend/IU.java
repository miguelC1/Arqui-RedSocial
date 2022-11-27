package frontend;

import backend.serviciopublicaciones.Publicacion;
import backend.serviciopublicaciones.ServicioPublicaciones;
import backend.servicioreacciones.Emocion;
import backend.servicioreacciones.ServicioReacciones;
import backend.serviciousuarios.ServicioUsuarios;
import backend.serviciousuarios.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class IU {
    Scanner sc;
    ServicioUsuarios servicioUsuarios;
    ServicioReacciones servicioReacciones;
    ServicioPublicaciones servicioPublicaciones;
    Usuario usuario;
    int idU;
    public IU(ServicioPublicaciones servicioPublicaciones, ServicioReacciones servicioReacciones, ServicioUsuarios servicioUsuarios) {
        sc=new Scanner(System.in);
        this.servicioPublicaciones =servicioPublicaciones;
        this.servicioReacciones =servicioReacciones;
        this.servicioUsuarios =servicioUsuarios;
        //usuario= this.servicioUsuarios.buscarUsuario(1);
        idU=1;
    }



    public  void iniciar(){
        System.out.println("INICIAR SESION");
        crearUsuario();
        menu();
    }

    public void crearUsuario(){
        System.out.print("Nombre Usuario: ");
        idU= servicioUsuarios.agregarUsuario(leerEntreada());
        usuario= servicioUsuarios.buscarUsuario(idU);
        mostrarMuroPublicaciones();
    }

    public void crearPublicacion (){
        System.out.println("=========TEXTO PUBLICACION======");
        System.out.println("Ingrese Contenido: ");
        String texto=leerEntreada();
        servicioPublicaciones.agregarPublicacion(idU,texto);
        //nuevaP.setFecha("27/oct/2022");
    }

    public void cambiarUsuario(){
        mostrarListaUsuarios();
        System.out.println("ELEGIR UN USUARIO");
        int n=sc.nextInt();
        usuario= servicioUsuarios.buscarUsuario(n);
        idU=n;
    }

    public void opcionUsuario(){
        System.out.println("1) CREAR NUEVO USUARIO");
        System.out.println("2) CAMBIAR USUARIO");
        int nu=sc.nextInt();
        if(nu==1){
            System.out.print("Nombre: ");
            crearUsuario();
        }
        else {
            mostrarListaUsuarios();
            System.out.println("ELEGIR UN USUARIO");
            int n=sc.nextInt();
            usuario= servicioUsuarios.buscarUsuario(n);
            idU=n;
        }
    }
    private void mostrarMuroPublicaciones(){
        List<Integer>listaP= servicioPublicaciones.listarPublicaciones();
        for(int i=listaP.size()-1; i>=0; i--){
            System.out.println("(== PUBLICACION "+(i+1)+" ==)");
            Publicacion publicacion= servicioPublicaciones.buscarPublicacion(listaP.get(i));
            mostrarPublicacion(publicacion, listaP.get(i));
        }
    }


    public void mostrarListaUsuarios(){
        List<Integer>listaU= servicioUsuarios.listarUsuarios();

        for(int i=0; i< listaU.size(); i++){
            System.out.println(listaU.get(i)+") "+ servicioUsuarios.buscarUsuario(listaU.get(i)).getNombre());
        }
        System.out.println();
    }

    private void mostrarPublicacion(Publicacion publicacion, int idP){
        System.out.println ("*********************************RED SOCIAL***************************************************");
        lineaContorno();
        String cad=servicioUsuarios.buscarUsuario(publicacion.getIdUsuario()).getNombre();
        mostrarLaterales(cad);
        //DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        //LocalDateTime fecha= LocalDateTime.parse(publicacion.getFecha());
        //String fechaString = FOMATTER.format(fecha);
        mostrarLaterales(publicacion.getFecha());
        System.out.println ("**==========================================================================================**");
        mostrarLaterales(publicacion.getContenido());
        System.out.println ("**==========================================================================================**");
        mostrarReacciones(idP);
        lineaContorno();
        System.out.println();

    }
    public void mostrarReacciones(){
        System.out.println("1)Like  2)Love  3)Sad  4)Happy  5)Mad  6)Surprise  7)Care  8)Indifferent  9)Explain ");
        System.out.println();
    }
    public static void lineaContorno(){
        System.out.println ("**********************************************************************************************");
    }
    private void listaE(String [] expr){
        System.out.print ("**");
        for (int i=0; i<expr.length; i++){
            System.out.print (""+expr[i]+" ");
        }
        System.out.print ("**");
        System.out.println ("");
    }
    private String leerEntreada(){
        String cad="";
        cad=sc.nextLine();
        if(!cad.equals("")){
            return cad;
        }
        else {
            return sc.nextLine();
        }

    }
    private void mostrarLaterales(String texto){
        System.out.print ("** "+texto);
        for (int i=0; i<89-texto.length(); i++){
            System.out.print (" ");
        }
        System.out.println("**");
    }

    public void menu(){
        int caso;
        mostrarMenuIntereses();
        while ((caso=sc.nextInt())!=0){
            if(caso==1){
                crearPublicacion();
                mostrarMuroPublicaciones();
            }else if(caso==2){
                reaccionarPublicacion();
                mostrarMuroPublicaciones();
            }else if(caso==3){
                crearUsuario();
            }else if(caso==5) {
                cambiarUsuario();
            }else if(caso==6){
                mostrarFormaCSVPublicaciones();
            } else if(caso==6){
                mostrarListaUsuarios();
            }
            mostrarMenuIntereses();
        }
    }
    private void reaccionarPublicacion(){
        System.out.println("NUMERO DE PUBLICACION");
        int nP=sc.nextInt();
        System.out.println("SELECCIONAR UNA REACCIONAR");
        mostrarReacciones();
        int nR=sc.nextInt();
        System.out.println(nR);
        nR=nR-1;
        //String reaccionar=Emocion.values()[nR].toString();
        servicioReacciones.agregarReaccion(nP,idU,Emocion.values()[nR]);

    }
    public void mostrarMenuIntereses(){
        System.out.println("//// MENU de OPERACIONES /////");
       // System.out.println("1) Ver Publicaciones");
        System.out.println("1) Crear Nueva Publicacion");
        System.out.println("2) Reaccionar Publicacion");
        System.out.println("3) Cambiar Usuario");
        System.out.println("0) Cerrar Sesion");
    }

    public void mostrarFormaCSVPublicaciones(){
        List<Integer> lista= servicioPublicaciones.listarPublicaciones();
        for (int i=0; i< lista.size(); i++){
            Publicacion act= servicioPublicaciones.buscarPublicacion(lista.get(i));
            System.out.println(" = "+act.getIdUsuario()+" = "+ act.getContenido()+" = "+ act.getFecha());
        }
    }
   

    public void mostrarReacciones(int idP){
        Emocion emocion= Emocion.Like;
        int i=1;
        Map<Emocion,Integer>cantidad= servicioReacciones.listarResumenReacciones(idP);
        for(Emocion e: Emocion.values()){
            Integer nu=cantidad.get(e);
            if(nu!=null && nu!=0){
                System.out.print(e.toString()+"("+nu+")");
                i++;
            }
        }
        System.out.println();
    }



}
