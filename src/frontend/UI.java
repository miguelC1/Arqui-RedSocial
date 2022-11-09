package frontend;

import backend.*;

import java.time.format.DateTimeFormatter;
import java.util.*;
public class UI {
    Scanner sc;
    RedSocial redSocial;
    ArrayList<Usuario>listaUsuario;
    public UI(){
        sc=new Scanner(System.in);
        redSocial =new RedSocial();
        listaUsuario=new ArrayList<>();
    }

    public  void iniciar(){
        menu();
    }
    public void mostrarUsuarios(){
        List<Usuario>listaU= redSocial.obtenerListasDeUsuarios();
        int i=1;
        for(Usuario usu: listaU){
            System.out.println(i+") "+ usu.getNombre());
            i++;
        }
        System.out.println();
    }
    public void crearUsuario(){
        redSocial.crearUsuario(leerEntreada());
    }
    public void opcionUsuario(){
        System.out.println("usuario actual "+ redSocial.usuarioAtual().getNombre());
        System.out.println("1) CREAR NUEVO USUARIO");
        System.out.println("2) CAMBIAR USUARIO");
        int nu=sc.nextInt();
        if(nu==1){
            System.out.print("Nombre: ");
            crearUsuario();
        }
        else {
            mostrarUsuarios();
            List<Usuario>listaU= redSocial.obtenerListasDeUsuarios();
            System.out.println("ELEGIR UN USUARIO");
            int n=sc.nextInt();
            redSocial.cambiarUsuario(listaU.get(n-1));
        }
    }
    private void mostrarMuro(){
        List<Publicacion>listaP=redSocial.obtenerListasDePublicaciones();
        System.out.println("mostarMuro"+ listaP.size());
        for(int i=0; i< listaP.size(); i++){
            System.out.println("(== "+(i+1)+" ==)");
            mostrarPublicidad(listaP.get(i));
        }
    }

    public void crearPublicacion (Usuario usuario){
        System.out.println("=========TEXTO PUBLICACION======");
        System.out.println("Ingrese Contenido: ");
        String texto=leerEntreada();
        redSocial.crearPublicacion(texto);
        //nuevaP.setFecha("27/oct/2022");
    }

    private void mostrarPublicidad (Publicacion publicacion){
        System.out.println ("*********************************RED SOCIAL***************************************************");
        System.out.println ("**********************************************************************************************");
        mostrar(redSocial.usuarioAtual().getNombre());
        DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy  hh:mm a");
        String fechaString = FOMATTER.format(publicacion.getFecha());
        mostrar(fechaString);
        System.out.println ("**==========================================================================================**");
        mostrar (publicacion.getContenido());
        System.out.println ("**==========================================================================================**");
        mostrarReacciones();
        linea();
        System.out.println();

    }
    public void mostrarReacciones(){
        Reacion reacion= Reacion.Like;
        int i=1;
        for(Reacion r: Reacion.values()){
            System.out.print(i+")"+r.toString()+"  ");
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
                Usuario u1= redSocial.usuarioAtual();
                crearPublicacion(u1);
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
        System.out.println("numero REaccion"+nR);
        String reaccionar=Reacion.values()[nR].toString();
        System.out.println("numero REaccion"+nR);
        redSocial.crearReacion(nP,reaccionar,redSocial.usuarioAtual().getId());
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
        List<Publicacion> lista=redSocial.obtenerListasDePublicaciones();
        for (int i=0; i< lista.size(); i++){
            Publicacion act=lista.get(i);
            System.out.println(act.getId()+" = "+act.getIdUsuario()+" = "+ act.getContenido()+" = "+ act.getFecha());
        }
    }
    public void mostrarCSVReaciones(){
        List<Reacciones> lista=redSocial.obtenerListasReacciones();
        for (int i=0; i< lista.size(); i++){
            System.out.println(lista.get(i).getId()+" "+ lista.get(i).getNombre()+" "+lista.get(i).getIdUsuario());
        }
    }


}
