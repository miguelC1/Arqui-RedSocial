import backend.serviciointereses.ServicioIntereses;
import backend.serviciopublicaciones.GestorDeArchivoPublicacion;
import backend.serviciopublicaciones.ServicioPublicaciones;
import backend.servicioreacciones.GestorDeArchivoReaccion;
import backend.servicioreacciones.ServicioReacciones;
import backend.serviciorelacionador.ServicioRelacionador;
import backend.serviciousuarios.ServicioUsuarios;
import frontend.IU;

public class Main {
    public static void main (String[] args){
        ServicioPublicaciones servicioPublicaciones  = new ServicioPublicaciones();
		ServicioReacciones servicioReacciones = new ServicioReacciones();
    	ServicioUsuarios servicioUsuarios = new ServicioUsuarios();
        ServicioIntereses servicioIntereses = new ServicioIntereses();
        ServicioRelacionador servicioInteresUsuario = new ServicioRelacionador("InteresUsuario");
        ServicioRelacionador servicioInteresPublicacion = new ServicioRelacionador("InteresPublicacion");

        IU iu = new IU(servicioPublicaciones, servicioReacciones,
                       servicioUsuarios, servicioIntereses,
                       servicioInteresUsuario,servicioInteresPublicacion);

        iu.iniciar();
    }
}
