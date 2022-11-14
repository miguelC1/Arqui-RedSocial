package frontend;

import backend.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class IU {
    Scanner sc;
    ServicioUsuarios servicioUsuario;
    ServicioReacciones servicioReaccion;
    ServicioPublicaciones servicioPublicacion;
    Usuario usuario;
    int idU;
    public IU(ServicioPublicaciones servicioPublicaciones, ServicioReacciones servicioReacciones, ServicioUsuarios servicioUsuarios) {
        sc=new Scanner(System.in);
        servicioPublicacion=servicioPublicaciones;
        servicioReaccion=servicioReacciones;
        servicioUsuario=servicioUsuarios;
        usuario=servicioUsuario.buscarUsuario(1);
        idU=1;
    }



    public  void iniciar(){
        publicacionDefecto();
        menu();
    }

    public void crearUsuario(){
        servicioUsuario.agregarUsuario(leerEntreada());
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
            mostrarUsuarios();
            System.out.println("ELEGIR UN USUARIO");
            int n=sc.nextInt();
            usuario=servicioUsuario.buscarUsuario(n);
            idU=n;
        }
    }
    private void mostrarMuro(){
        List<Integer>listaP=servicioPublicacion.listarPublicaciones();
        System.out.println("mostarMuro"+ listaP.size());
        for(int i=0; i< listaP.size(); i++){
            System.out.println("(== "+(i+1)+" ==)");
            Publicacion publicacion=servicioPublicacion.buscarPublicacion(listaP.get(i));
            mostrarPublicidad(publicacion);
        }
    }

    public void crearPublicacion (){
        System.out.println("=========TEXTO PUBLICACION======");
        System.out.println("Ingrese Contenido: ");
        String texto=leerEntreada();
        servicioPublicacion.agregarPublicacion(idU,texto);
        //nuevaP.setFecha("27/oct/2022");
    }
    public void mostrarUsuarios(){
        List<Integer>listaU= servicioUsuario.listarUsuarios();

        for(int i=0; i< listaU.size(); i++){
            System.out.println(listaU.get(i)+") "+ servicioUsuario.buscarUsuario(listaU.get(i)));
        }
        System.out.println();
    }

    private void mostrarPublicidad (Publicacion publicacion){
        System.out.println ("*********************************RED SOCIAL***************************************************");
        System.out.println ("**********************************************************************************************");
        mostrar(usuario.getNombre());
        DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy  hh:mm a");
        LocalDateTime fecha= LocalDateTime.parse(publicacion.getFecha());
        String fechaString = FOMATTER.format(fecha);
        mostrar(fechaString);
        System.out.println ("**==========================================================================================**");
        mostrar (publicacion.getContenido());
        System.out.println ("**==========================================================================================**");
        mostrarReacciones();
        linea();
        System.out.println();

    }
    public void mostrarReacciones(){
        Emocion emocion= Emocion.Like;
        int i=1;
        for(Emocion e: Emocion.values()){
            System.out.print(i+")"+e.toString()+"  ");
            i++;
        }
        System.out.println();
    }
    public static void linea(){
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
    private void mostrar(String texto){
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
                mostrarMuro();
            }
            else if (caso==2){
                crearPublicacion();
                mostrarMuro();
            }else if(caso==3){
                reaccionarPublicacion();
            }else if(caso==4){
                opcionUsuario();
            }else if(caso==5) {
                mostrarPublicaciones();
            }else if(caso==6){
                mostrarUsuarios();
            }else if(caso==7){
                mostrarCSVReaciones();
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
        System.out.println("numero REaccion"+nR);
        servicioReaccion.agregarReacciones(nP,idU,Emocion.values()[nR]);
    }
    public void mostrarMenuIntereses(){
        System.out.println("MENU de OPERACIONES");
        System.out.println("1) Ver Publicidad");
        System.out.println("2) Crear Publicidad");
        System.out.println("3) Reaccionar Publicidad");
        System.out.println("4) cambiar Usuario");
        /*System.out.println("5) listar Publicaiones");
        System.out.println("6) listar Usuario");
        System.out.println("7) listar Reaciones");*/
    }

    public void mostrarPublicaciones(){
        List<Integer> lista=servicioPublicacion.listarPublicaciones();
        for (int i=0; i< lista.size(); i++){
            Publicacion act=servicioPublicacion.buscarPublicacion(lista.get(i));
            System.out.println(" = "+act.getIdUsuario()+" = "+ act.getContenido()+" = "+ act.getFecha());
        }
    }
    public void mostrarCSVReaciones(){
        List<Reacciones> lista=servicioReaccion.listarReacciones();
        for (int i=0; i< lista.size(); i++){
            System.out.println(lista.get(i).getIdPublicacion()+" "+ lista.get(i).getNombre()+" "+lista.get(i).getIdUsuario());
        }
    }

    private void publicacionDefecto(){
       servicioUsuario.agregarUsuario("Maria Jimenes");

      // servicioPublicacion.agregarPublicacion(1,"El Pique Macho y el arroz, no le va, no? ");

        //p1.setFecha(LocalDateTime.parse("2022-11-06T19:12:04.213293600"));
        //servicioPublicacion.agregarDatosCSV(p1.getId(),p1.getIdUsuario(), p1.getContenido(),p1.getFecha());
    }


}
