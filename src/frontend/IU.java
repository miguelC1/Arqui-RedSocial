package frontend;
import backend.serviciointereses.ServicioIntereses;
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
    private ServicioIntereses servicioIntereses;
    private Usuario usuario;
    private List<String>listaUsurioConvertidos;
    private int idU;
    private int idP;
    public IU(ServicioPublicaciones servicioPublicaciones, ServicioReacciones servicioReacciones, ServicioUsuarios servicioUsuarios) {
        sc=new Scanner(System.in);
        this.servicioPublicaciones =servicioPublicaciones;
        this.servicioReacciones =servicioReacciones;
        this.servicioUsuarios =servicioUsuarios;
        listaUsurioConvertidos= new ArrayList<>();
        this.servicioIntereses=new ServicioIntereses();
    }

    public  void iniciar(){
        System.out.println("1) INICIAR SESION");
        System.out.println("2) FINALIZAR");
        int caso;
        while ((caso=sc.nextInt())!=0){
            if(caso==1){
                iniciarSeSion();
            }else if(caso==2){
                break;
            }
        }

    }

    private void iniciarSeSion(){
        System.out.println("INICIAR SESION");
        crearUsuario();
        usuario= servicioUsuarios.buscarUsuario(idU);
        if(usuario.esUsuario()){
            menuUsuario();
        }else{
            menuCandidato();
        }
    }

    private void cerrarSesion() {
        System.out.println("###############################CERRAR SESION ##############################");
        System.out.println("___________________________________________________________________________");
        iniciar();
    }

    private void crearUsuario(){
        System.out.print("Nombre Usuario: ");
        idU= servicioUsuarios.agregarUsuario(leerEntreadaTeclado());
    }

    private void crearPublicacion (){
        System.out.println("=========TEXTO PUBLICACION======");
        System.out.println("Ingrese Contenido: ");
        String texto= leerEntreadaTeclado();
       idP= servicioPublicaciones.agregarPublicacion(idU,texto);
    }

    private  void publicarPublicacon(){
        if(usuario.esUsuario()){
            System.out.println("1) crear puclicacion con 1 Interes");
            System.out.println("2) crear sin puclicacion Interes");
            int caso=sc.nextInt();
            if(caso==1){
                crearPublicacion();
                asociarpublicacionAInteres();
            }else if(caso==2){
                crearPublicacion();
            }
        }
    }

    public void cambiarUsuario(){
        mostrarListaUsuarios();
        System.out.println("ELEGIR UN USUARIO");
        int n=sc.nextInt();
        usuario= servicioUsuarios.buscarUsuario(n);
        idU=n;
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
        System.out.println ("*********************************RED SOCIAL***************************************************");
        System.out.println ("**********************************************************************************************");
        String cad=servicioUsuarios.buscarUsuario(publicacion.getIdUsuario()).getNombre();
        mostrarLaterales(cad);
        mostrarLaterales(publicacion.getFecha());
        System.out.println ("**==========================================================================================**");
        mostrarLaterales(publicacion.getContenido());
        System.out.println ("**==========================================================================================**");
        tieneInteres(idP);
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
        agregarIntereses();
        mostrarMuroPublicaciones();
        TextosMenu();
        while ((caso=sc.nextInt())!=0){
            if(caso==1){
                publicarPublicacon();
                mostrarMuroPublicaciones();
            }else if(caso==2){
                reaccionarPublicacion();
                mostrarMuroPublicaciones();
            }else if(caso==3){
                cerrarSesion();
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
                mostrarMuroPublicaciones();
            }else if(caso==3){
                cerrarSesion();
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
        servicioReacciones.agregarReaccion(nP,idU,Emocion.values()[nR]);

    }
    public void TextosMenu(){
        System.out.println("///// MENU DE OPERACIONES DE USUARIO/////");
        System.out.println("// 1) Crear Nueva Publicacion          //");
        System.out.println("// 2) Reaccionar Publicacion           //");
        System.out.println("// 3) Cerrar Sesion                  //");
        System.out.println("// 0)==> Terminar Ejecucion           //");
        System.out.println("/////////////////////////////////////////");
    }
    public void TextosMenuCandidato(){
        System.out.println("///// MENU DE OPERACIONES DE CANDIDATO/////");
        if(!verificarRealizoPublicacion()){
            System.out.println("// 1) Crear Nueva Publicacion            //");
        }
        System.out.println("// 2) Reaccionar Publicacion             //");
        System.out.println("// 3) Cerrar Sesion                     //");
        System.out.println("// 0) ==>Terminar Ejecucion           //");
        System.out.println("/////////////////////////////////////////");
    }

    public void mostrarReacciones(int idP){
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
     private void agregarIntereses(){
         mostrarTextosIntereses();
         int caso;
         String interes;
         boolean bandera=true;
         while (bandera){
             caso= sc.nextInt();
             if(caso==1){
                 System.out.println("AGRESAR INTERES");
                 interes= leerEntreadaTeclado();
                 int id=servicioIntereses.agregarInteres(interes);
                 servicioIntereses.agregarInteresUsuarios(id,idU);
                 mostrarTextosIntereses();
             }else if(caso==2){
                bandera=false;
             }
         }
     }

     private void mostrarTextosIntereses(){
         System.out.println("========INTERES==========");
         System.out.println("1) AGREGAR INTERES");
         System.out.println("2) SALTAR OPCION");
     }

     private int buscarIdPublicacionNuevoUsuario(int idU){
        int res=0;
        res=servicioPublicaciones.buscarIdPublicacionPorIDUsuario(idU);
        return res;
     }

    private void asociarpublicacionAInteres() {
        System.out.println("el IDE  PUBLICA " +idP);
        Publicacion publicacion=servicioPublicaciones.buscarPublicacion(idP);
        mostrarPublicacion(publicacion,idP);
        System.out.println("SELECCIONAR INTERES");
        listarInteres();
        int num=sc.nextInt();
        servicioIntereses.agregarPublicacionAinteres(num,idP);

    }

    private void listarInteres(){
        List<Integer> lista=servicioIntereses.listarIntereses();
        System.out.println("0) Saltar Paso");
        for(int i=0; i<lista.size(); i++){
            System.out.println(i+1+") "+servicioIntereses.buscarInteres(lista.get(i)).getNombre());
        }
    }
    private void tieneInteres(int idP){
        int nu=servicioIntereses.buscaIDInteresPorPublicacion(idP);
        if(nu!=0){
            System.out.println ("                                                                   INTERES "+servicioIntereses.buscarInteres(nu).getNombre());
        }

    }
}
