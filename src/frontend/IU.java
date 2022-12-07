package frontend;
import backend.serviciopublicaciones.Publicacion;
import backend.serviciopublicaciones.ServicioPublicaciones;
import backend.servicioreacciones.Emocion;
import backend.servicioreacciones.ServicioReacciones;
import backend.serviciousuarios.ServicioUsuarios;
import backend.serviciousuarios.Usuario;

import java.util.*;
public class IU {
    private Scanner sc;
    private ServicioUsuarios servicioUsuarios;
    private ServicioReacciones servicioReacciones;
    private ServicioPublicaciones servicioPublicaciones;
    private Usuario usuario;
    private Usuario usuarioCovertido;
    private List<String>listaUsurioConvertidos;
    private int idU;
    public IU(ServicioPublicaciones servicioPublicaciones, ServicioReacciones servicioReacciones, ServicioUsuarios servicioUsuarios) {
        sc=new Scanner(System.in);
        this.servicioPublicaciones =servicioPublicaciones;
        this.servicioReacciones =servicioReacciones;
        this.servicioUsuarios =servicioUsuarios;
        listaUsurioConvertidos= new ArrayList<>();
    }

    public  void iniciar(){
        System.out.println("INICIAR SESION");
        crearUsuario();
    }

    private void crearUsuario(){
        System.out.print("Nombre Usuario: ");
        idU= servicioUsuarios.agregarUsuario(leerEntreadaTeclado());
        usuario= servicioUsuarios.buscarUsuario(idU);
        if(usuario.esUsuario()){
            menuUsuario();
        }else{

            menuCandidato();
        }
        mostrarMuroPublicaciones();
    }

    public void crearPublicacion (){
        System.out.println("=========TEXTO PUBLICACION======");
        System.out.println("Ingrese Contenido: ");
        String texto= leerEntreadaTeclado();
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
        Publicacion publicacion=null;
        for(int i=0; i<listaP.size(); i++){
            System.out.println("(== PUBLICACION "+listaP.get(i)+" ==)");
            publicacion= servicioPublicaciones.buscarPublicacion(listaP.get(i));
            if(verificarCantidadReaccione(listaP.get(i))){
                String nombreCandidato = (servicioUsuarios.buscarUsuario(publicacion.getIdUsuario())).getNombre();
                if(!listaUsurioConvertidos.contains(nombreCandidato)){
                    listaUsurioConvertidos.add(nombreCandidato);
                }
                servicioUsuarios.cambiarAUsuario(publicacion.getIdUsuario());
            }
            mostrarPublicacion(publicacion, listaP.get(i));
        }
        mensajeCantidadosConvertidos();
    }


    public void mostrarListaUsuarios(){
        List<Integer>listaU= servicioUsuarios.listarUsuarios();
        for(int i=0; i< listaU.size(); i++){
            System.out.println(listaU.get(i)+") "+ servicioUsuarios.buscarUsuario(listaU.get(i)).getNombre());
        }
        System.out.println();
    }

    private void mostrarPublicacion(Publicacion publicacion, int idP){
        //System.out.println ("______________________________________________________________________________________________");
        System.out.println ("*********************************RED SOCIAL***************************************************");
        System.out.println ("**********************************************************************************************");
        String cad=servicioUsuarios.buscarUsuario(publicacion.getIdUsuario()).getNombre();
        mostrarLaterales(cad);
        mostrarLaterales(publicacion.getFecha());
        System.out.println ("**==========================================================================================**");
        mostrarLaterales(publicacion.getContenido());
        System.out.println ("**==========================================================================================**");
        mostrarReacciones(idP);
        System.out.println ("**********************************************************************************************");
        System.out.println();

    }
    public void mostrarReacciones(){
        System.out.println("1)Like  2)Love  3)Sad  4)Happy  5)Mad  6)Surprise  7)Care  8)Indifferent  9)Explain ");
        System.out.println();
    }

    private String leerEntreadaTeclado(){
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
        System.out.print ("## "+texto);
        for (int i=0; i<89-texto.length(); i++){
            System.out.print (" ");
        }
        System.out.println("##");
    }

    public void menuUsuario(){
        int caso;
        mostrarMuroPublicaciones();
        TextosMenu();
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
            TextosMenu();
        }
    }

    public void menuCandidato(){
        int caso;
        mostrarMuroPublicaciones();
        TextosMenuCandidato();
        while ((caso=sc.nextInt())!=0){
            if(caso==1){
                crearPublicacion();
                mostrarMuroPublicaciones();
            }else if(caso==2){
                reaccionarPublicacion();
                //usuarioInput.reaccionar();
                mostrarMuroPublicaciones();
            }else if(caso==3){
                crearUsuario();
            }
            TextosMenuCandidato();
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
    public void TextosMenu(){
        System.out.println("///// MENU DE OPERACIONES DE USUARIO/////");
        System.out.println("// 1) Crear Nueva Publicacion          //");
        System.out.println("// 2) Reaccionar Publicacion           //");
        System.out.println("// 3) Cambiar Usuario                  //");
        System.out.println("// 0) Cerrar Sesion                    //");
        System.out.println("/////////////////////////////////////////");
    }
    public void TextosMenuCandidato(){
        System.out.println("///// MENU DE OPERACIONES DE CANDIDATO/////");
        if(!verificarRealizoPublicacion()){
            System.out.println("// 1) Crear Nueva Publicacion            //");
        }
        System.out.println("// 2) Reaccionar Publicacion             //");
        System.out.println("// 3) Cambiar Usuario                    //");
        System.out.println("// 0) Cerrar Sesion                      //");
        System.out.println("/////////////////////////////////////////");
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

    private boolean verificarRealizoPublicacion(){
        boolean res=false;
        List<Integer> lista= servicioPublicaciones.listarPublicaciones();
        for(int i=0; i<lista.size(); i++){
            Publicacion publicacion =servicioPublicaciones.buscarPublicacion(lista.get(i));
            if(publicacion.getIdUsuario()==idU){
                res=true;
            }
        }
        return res;
    }
    private boolean verificarCantidadReaccione(int idP){
        boolean res=false;
        int cantidad=0;
        Map<Emocion,Integer> map= servicioReacciones.listarResumenReacciones(idP);
        for (Emocion clave:map.keySet()) {
            int valor = map.get(clave);
            cantidad=cantidad+valor;
        }
        if(cantidad>2){
            res=true;
        }
        return res;
    }

     private void mensajeCantidadosConvertidos(){
        for (int i=0; i<listaUsurioConvertidos.size(); i++){
            System.out.println("#########################################################################################################");
            System.out.println( "La publicacion de "+listaUsurioConvertidos.get(i)+" |tuvo mas de 3 Reacciones|,  "+listaUsurioConvertidos.get(i)+" pasa ser USUARIO");
            System.out.println("#########################################################################################################");
        }
     }
}
