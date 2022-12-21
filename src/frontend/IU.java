package frontend;
import backend.serviciointerespublicacion.ServicioInteresPublicacion;
import backend.serviciointeresusuario.ServicioInteresUsuario;
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
    private ServicioInteresPublicacion servicioInteresPublicacion;
    private ServicioInteresUsuario servicioInteresUsuario;
    private Usuario usuario;
    private Mensajes mensaje;
    private List<String>listaUsurioConvertidos;
    private int idU;
    private int idP;
    public IU(ServicioPublicaciones servicioPublicaciones, ServicioReacciones servicioReacciones, ServicioUsuarios servicioUsuarios) {
        sc=new Scanner(System.in);
        mensaje= new Mensajes();
        this.servicioPublicaciones =servicioPublicaciones;
        this.servicioReacciones =servicioReacciones;
        this.servicioUsuarios =servicioUsuarios;
        this.listaUsurioConvertidos= new ArrayList<>();
        this.servicioIntereses=new ServicioIntereses();
        this.servicioInteresPublicacion= new ServicioInteresPublicacion();
        this.servicioInteresUsuario=new ServicioInteresUsuario();
    }

    public  void iniciar(){
        mensaje.textoIniciar();
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
        mensaje.textoIniciarSeSion();
        mensaje.textoCrearUsuario();
        idU= servicioUsuarios.agregarUsuario(leerEntreadaTeclado());
        usuario= servicioUsuarios.buscarUsuario(idU);
        if(usuario.esUsuario()){
            menuUsuario();
        }else{
            menuCandidato();
        }
    }

    private void cerrarSesion() {
        mensaje.textoCerrarSesion();
        iniciar();
    }

    private void crearPublicacion (){
        mensaje.textoCrearPublicacion();
        String texto= leerEntreadaTeclado();
        idP= servicioPublicaciones.agregarPublicacion(idU,texto);
    }

    private  void publicarNuevaPublicacon(){
        if(usuario.esUsuario()){
           mensaje.textoPublicarPublicaconConInteresoSinInteres();
            int caso=sc.nextInt();
            if(caso==1){
                crearPublicacion();
                asociarpublicacionAInteres();
            }else if(caso==2){
                crearPublicacion();
            }
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

    private void menuUsuario(){
        int caso;
        if(verificarCantidadInteresesDeUsuario()){
            agregarIntereses();
        }
        mostrarMuroPublicaciones();
        mensaje.TextosMenuUsuario();
        while ((caso=sc.nextInt())!=0){
            if(caso==1){
                publicarNuevaPublicacon();
                mostrarMuroPublicaciones();
            }else if(caso==2){
                reaccionarPublicacion();
                mostrarMuroPublicaciones();
            }else if(caso==3){
                cerrarSesion();
            }
            mensaje.TextosMenuUsuario();
        }
    }

    private void menuCandidato(){
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
        mensaje.textoReaccionarPublicacion();
        int nP=sc.nextInt();
        mensaje.textoMostrarReacciones();
        int nR=sc.nextInt();
        System.out.println(nR);
        nR=nR-1;
        servicioReacciones.agregarReaccion(nP,idU,Emocion.values()[nR]);

    }
    public void TextosMenuCandidato(){
        mensaje.TextosMenuCandidato(verificarRealizoPublicacion());
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
         mensaje.textoMostrarTextosAgregarIntereses();
         int caso;
         String interes;
         boolean bandera=true;
         while (bandera){
             caso= sc.nextInt();
             if(caso==1){
                 System.out.println("AGRESAR INTERES");
                 interes= leerEntreadaTeclado();
                 int id=servicioIntereses.registrarInteres(interes);
                 servicioInteresUsuario.agregarInteresUsuario(id,idU);
                 bandera=verificarCantidadInteresesDeUsuario();
                 if(bandera){
                     mensaje.textoMostrarTextosAgregarIntereses();
                 }
             }else if(caso==2){
                bandera=false;
             }
         }
     }

    private void asociarpublicacionAInteres() {
        Publicacion publicacion=servicioPublicaciones.buscarPublicacion(idP);
        mostrarPublicacion(publicacion,idP);
        System.out.println("SELECCIONAR INTERES");
        listarInteres();
        int num=sc.nextInt();
        servicioInteresPublicacion.agregarInteresPublicacion(num,idP);

    }

    private void listarInteres(){
        List<Integer> lista=servicioIntereses.listarIntereses();
        for(int i=0; i<lista.size(); i++){
            System.out.println(i+1+") "+servicioIntereses.buscarInteres(lista.get(i)).getNombreInteres());
        }
        System.out.println("0) Saltar Paso");
    }
    private void tieneInteres(int idP){
        List<Integer> lista=servicioInteresPublicacion.listarInteresesPorPublicacion(idP);
        System.out.print("INTERES: ");
        for(int i=0; i<lista.size(); i++){
           System.out.print(" "+servicioIntereses.buscarInteres(lista.get(i)));
        }
    }

    private boolean verificarCantidadInteresesDeUsuario(){
        boolean res= false;
        int cantidad=servicioInteresUsuario.listarInteresesPorUsuario(idU).size();
        System.out.println("cantidad de interes USuario "+cantidad);
        if(cantidad<3){
            res=true;
        }
        System.out.println(res);
        return res;
    }

}
