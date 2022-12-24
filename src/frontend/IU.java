package frontend;

import backend.serviciorelacionador.ServicioRelacionador;
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
    private ServicioRelacionador servicioInteresPublicacion;
    private ServicioRelacionador servicioInteresUsuario;
    private Usuario usuario;
    private Mensajes mensaje;
    private List<String>listaUsurioConvertidos;
    private int idU;
    private int idP;
    public IU(ServicioPublicaciones servicioPublicaciones, ServicioReacciones servicioReacciones,
              ServicioUsuarios servicioUsuarios,ServicioIntereses servicioIntereses,
              ServicioRelacionador servicioInteresUsuario,ServicioRelacionador servicioInteresPublicacion) {
        sc=new Scanner(System.in);
        mensaje= new Mensajes();
        this.servicioPublicaciones =servicioPublicaciones;
        this.servicioReacciones =servicioReacciones;
        this.servicioUsuarios =servicioUsuarios;
        this.servicioIntereses=servicioIntereses;
        this.servicioInteresPublicacion= servicioInteresPublicacion;
        this.servicioInteresUsuario=servicioInteresUsuario;
        this.listaUsurioConvertidos= new ArrayList<>();
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
                asociarInteresAPublicacion();
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
        mostrarInteres(idP);
        mostrarLaterales(publicacion.getContenido());
        System.out.println ("**==========================================================================================**");
        mostrarReacciones(idP);
        System.out.println ("**********************************************************************************************");
        System.out.println();

    }
    private String leerEntreadaTeclado(){
        String cad="";
        cad=sc.nextLine();
        if(cad.equals("")){
            cad= sc.nextLine();
            while(cad.equals("")){
                System.out.println("Ingresar texto");
                cad= sc.nextLine();
            }
        }
        return cad;
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
        if(!tieneInteresesUsuario()){
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
        mensaje.textoSeleccionarPublicacion();
        int nP=sc.nextInt();
        if(!reacionarMismaPublicacion(nP)) {
            mensaje.textoMostrarReacciones();
            int nR = sc.nextInt();
            nR = nR - 1;
            servicioReacciones.agregarReaccion(nP, idU, Emocion.values()[nR]);
        }else{
            System.out.println("No Puedes Reaccionar tu misma publicacion");
            reaccionarPublicacion();
        }
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
            mensaje.textoMensajeCantidadosConvertidos(listaUsurioConvertidos.get(i));
        }
     }
     private void agregarIntereses(){
         System.out.println("AGRESAR INTERES");
         int caso;
         String interes;
         interes= leerEntreadaTeclado();
         int idNuevo=servicioIntereses.registrarInteres(interes);
         servicioInteresUsuario.agregarInteresRelacionado(idNuevo,idU);
         tieneCantidadInteresesDeUsuario();
         boolean bandera=true;
         while (bandera){
             mensaje.textoMostrarTextosAgregarIntereses();
             caso= sc.nextInt();
             if(caso==1){
                 System.out.println("AGRESAR INTERES");
                 interes= leerEntreadaTeclado();
                 int id=servicioIntereses.registrarInteres(interes);
                 servicioInteresUsuario.agregarInteresRelacionado(id,idU);
                 bandera= tieneCantidadInteresesDeUsuario();
             }else if(caso==2){
                bandera=false;
             }
         }
     }

    private void asociarInteresAPublicacion() {
        Publicacion publicacion=servicioPublicaciones.buscarPublicacion(idP);
        mostrarPublicacion(publicacion,idP);
        listarInteres();
        mensaje.textoAsociarInteresAPublicacion();
        String entrada=leerEntreadaTeclado();
        String  [] intereses= entrada.split(",");
        for(int i=0; i< intereses.length; i++){
            int num=servicioIntereses.registrarInteres(intereses[i]);
            servicioInteresPublicacion.agregarInteresRelacionado(num,idP);
        }
    }

    private void listarInteres(){
        List<Integer> lista=servicioIntereses.listarIntereses();
        for(int i=0; i<lista.size(); i++){
            System.out.println("| "+servicioIntereses.buscarInteres(lista.get(i)).getNombreInteres());
        }
    }
    private void mostrarInteres(int idP){
        List<Integer> lista=servicioInteresPublicacion.listarInteresesRelacionados(idP);
        System.out.print("INTERES: => ");
        for(int i=0; i<lista.size(); i++){
           System.out.print(" "+servicioIntereses.buscarInteres(lista.get(i)).getNombreInteres()+",");
        }
        System.out.println();
    }

    private boolean tieneCantidadInteresesDeUsuario(){
        boolean res= false;
        int cantidad=servicioInteresUsuario.listarInteresesRelacionados(idU).size();
        System.out.println("Cantidad de Interes Propio  "+cantidad);
        if(cantidad<3){
            res=true;
        }
        return res;
    }

    private boolean reacionarMismaPublicacion(int idP){
        boolean res=false;
        Publicacion publi=servicioPublicaciones.buscarPublicacion(idP);
        if(idU==publi.getIdUsuario()){
            res=true;
        }
        return res;
    }


    private boolean tieneInteresesUsuario() {
        boolean res= false;
        int cantidad=servicioInteresUsuario.listarInteresesRelacionados(idU).size();
        if(cantidad!=0){
            res= true;
        }
        return  res;
    }

}
