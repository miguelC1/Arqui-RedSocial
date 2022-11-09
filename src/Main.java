import backend.GestorDeArchivos;
import frontend.Reacion;
import frontend.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.System.*;

public class Main {
    public static void main (String[] args){

        UI ui=new UI();
        ui.iniciar();

        /*for (int i=0; i<Reacion.values().length; i++) {
            String re=Reacion.values()[i].toString();
            System.out.println(re);
        }*/

    }
}
