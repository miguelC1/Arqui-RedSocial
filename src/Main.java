import backend.serviciopublicaciones.GestorDeArchivoPublicacion;
import backend.serviciopublicaciones.ServicioPublicaciones;
import backend.servicioreacciones.GestorDeArchivoReaccion;
import backend.servicioreacciones.ServicioReacciones;
import backend.serviciousuarios.ServicioUsuarios;
import frontend.IU;

public class Main {
    public static void main (String[] args){
        ServicioPublicaciones servicioPublicaciones  = new ServicioPublicaciones();
		ServicioReacciones servicioReacciones = new ServicioReacciones();
    	ServicioUsuarios servicioUsuarios = new ServicioUsuarios();
        IU iu = new IU(servicioPublicaciones, servicioReacciones, servicioUsuarios);

        iu.iniciar();
        /*GestorDeArchivoReaccion reaccion= new GestorDeArchivoReaccion("corma");
        for(int  i=0; i<reaccion.leerDatosCSV().length; i++)
            System.out.println(reaccion.leerDatosCSV()[i]);
*/
    }
}
