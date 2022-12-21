package frontend;

import backend.serviciointereses.ServicioIntereses;
import backend.serviciopublicaciones.Publicacion;

import backend.serviciopublicaciones.ServicioPublicaciones;
import backend.servicioreacciones.Emocion;
import backend.serviciousuarios.Usuario;
import frontend.ModeloSalida.PublicacionSalida;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Mensajes {
    Scanner sc;
    private ServicioPublicaciones servicioPublicaciones;
    public Mensajes() {
        sc = new Scanner(System.in);
        servicioPublicaciones= new ServicioPublicaciones();
    }
    public  void textoIniciar(){
        System.out.println("1) INICIAR SESION");
        System.out.println("2) FINALIZAR");
    }

    public void textoIniciarSeSion(){
        System.out.println("************************");
        System.out.println("** INICIAR SESION     **");
        System.out.println("************************");
    }

    public void textoCerrarSesion() {
        System.out.println("###############################CERRAR SESION ##############################");
        System.out.println("___________________________________________________________________________");
    }

    public void textoCrearUsuario(){
        System.out.print("Nombre Usuario: ");
    }

    public void textoCrearPublicacion (){
        System.out.println("=========TEXTO PUBLICACION======");
        System.out.println("Ingrese Contenido: ");
    }

    public void textoPublicarPublicaconConInteresoSinInteres() {
        System.out.println("1) crear puclicacion con 1 Interes");
        System.out.println("2) crear sin puclicacion Interes");
    }

    public void textoMostrarReacciones(){
        System.out.println("1)Like  2)Love  3)Sad  4)Happy  5)Mad  6)Surprise  7)Care  8)Indifferent  9)Explain ");
        System.out.println();
    }

    public void TextosMenuUsuario(){
        System.out.println("///// MENU DE OPERACIONES DE USUARIO/////");
        System.out.println("// 1) Crear Nueva Publicacion          //");
        System.out.println("// 2) Reaccionar Publicacion           //");
        System.out.println("// 3) Cerrar Sesion                  //");
        System.out.println("// 0)==> Terminar Ejecucion           //");
        System.out.println("/////////////////////////////////////////");
    }
    public void TextosMenuCandidato(boolean realizoP){
        System.out.println("///// MENU DE OPERACIONES DE CANDIDATO/////");
        if(!realizoP){
            System.out.println("// 1) Crear Nueva Publicacion            //");
        }
        System.out.println("// 2) Reaccionar Publicacion             //");
        System.out.println("// 3) Cerrar Sesion                      //");
        System.out.println("// 0) ==>Terminar Ejecucion              //");
        System.out.println("/////////////////////////////////////////");
    }

    public void textoMostrarTextosAgregarIntereses(){
        System.out.println("========INTERES==========");
        System.out.println("1) AGREGAR INTERES");
        System.out.println("2) SALTAR OPCION");
    }

    public void textoReaccionarPublicacion() {
        System.out.println("NUMERO DE PUBLICACION");
        System.out.println("SELECCIONAR UNA REACCION");
    }
}
