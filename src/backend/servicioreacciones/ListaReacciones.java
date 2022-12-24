package backend.servicioreacciones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaReacciones {
    List<Reaccion> listaDeReacciones;
    private GestorDeArchivoReaccion archivo;
    public ListaReacciones(){
        archivo= new GestorDeArchivoReaccion("Reacciones");
        listaDeReacciones =new ArrayList<>();
        cargarDatosLista();
    }

    public void agregarReaccion(int idPublicacion, int idUsuario, Emocion reaccion) {
        String nombreReaccion= reaccion.name();
        if(verificarReaccionUsuario(idPublicacion,idUsuario)){
            eliminarReacccion(idPublicacion, idUsuario);
            listaDeReacciones.add(new Reaccion(idPublicacion,nombreReaccion,idUsuario));
            actualizarDatos(transformarListaEnArregloDeCadenas());
        } else{
            archivo.escribirDatosEnCSV(idPublicacion+","+reaccion+","+idUsuario);
            listaDeReacciones.add(new Reaccion(idPublicacion,nombreReaccion,idUsuario));
        }
    }

    public Map<Emocion,Integer> listarResumenReacciones(int idPublicacion) {
        Map<Emocion,Integer> map= new HashMap<>();
        for (int i=0; i<Emocion.values().length; i++){
            int cantidad=contarReaccionesConIdyNombre(idPublicacion,Emocion.values()[i].name());
            Emocion actual=Emocion.values()[i];
            map.put(actual,cantidad);
        }
        return map;
    }

    private void eliminarReacccion(int idP, int idU) {
        for (int i = 0; i< listaDeReacciones.size(); i++){
            Reaccion rec= listaDeReacciones.get(i);
            if(rec.getIdPublicacion()==idP && rec.getIdUsuario()==idU){
                listaDeReacciones.remove(i);
            }
        }
    }

    private void actualizarDatos(String [] nombre) {
        archivo.escribirDeCerroEnCSV(nombre);
    }

    private void cargarDatosLista() {
        String [] datos=archivo.leerDatosCSV();
        if(datos.length!=0 && !datos[0].equals("")) {
            for (String dato : datos) {
                String[] cad = dato.split(",");
                int idP = Integer.parseInt(cad[0]);
                int idU = Integer.parseInt(cad[2]);
                listaDeReacciones.add(new Reaccion(idP, cad[1], idU));
            }
        }
    }

    private int contarReaccionesConIdyNombre(int idP, String nombre){
        int res=0;
        for (Reaccion reaccion: listaDeReacciones) {
            if(reaccion.getIdPublicacion()==idP && reaccion.getNombre().equals(nombre)){
                res++;
            }
        }
        return res;
    }
    private boolean verificarReaccionUsuario(int idP, int idU){
        boolean res=false;
        for (Reaccion reaccion: listaDeReacciones) {
            if(reaccion.getIdPublicacion()==idP && reaccion.getIdUsuario()==idU){
                res=true;
                break;
            }
        }
        return res;
    }

    private String [] transformarListaEnArregloDeCadenas(){
        String [] res=new String[listaDeReacciones.size()];
        int i=0;
        for (Reaccion reaccion: listaDeReacciones) {
            String cad=reaccion.getIdPublicacion()+","+reaccion.getNombre()+","+ reaccion.getIdUsuario();
            res[i]=cad;
            i++;
        }
        return res;
    }

}
