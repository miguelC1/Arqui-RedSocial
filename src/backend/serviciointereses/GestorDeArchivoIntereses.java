package backend.serviciointereses;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Scanner;


public class GestorDeArchivoIntereses {
    private String nombre;

    public GestorDeArchivoIntereses(String nombre){
        this.nombre=nombre+".csv";
        crearArchivo(this.nombre);
    }

    private void crearArchivo(String nombre){
        File archivo = new File(nombre);
        try {
            FileWriter escritor = new FileWriter(archivo,true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void escribirDatosEnCSV(String contenido){
        try(PrintWriter escritor = new PrintWriter(new FileWriter(nombre,true))) {
            escritor.printf(contenido + "\n");
            escritor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //escribir en el csv2todo un arreglo
    public void escribirDeCerroEnCSV(String [] contenido){
        try(PrintWriter escritor = new PrintWriter(new FileWriter(nombre))) {
            for (String fila: contenido){
                escritor.printf(fila + "\n");
            }
            escritor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] leerDatosCSV(){
        String [] res=leerDatos();
        if(res[0].equals("")){
           res=new String[0] ;
        }
        return res;
    }
    private String[] leerDatos(){
        String cont = readFile(nombre);
        return cont == null ? null : cont.split("\\r?\\n");
    }

    private static String readFile(String filePath) {
        File file = new File(filePath);
        if(!file.isFile() || file.isDirectory()) {
            return null;
        }
        try {
            String cont = new String(Files.readAllBytes(file.toPath()));
            return cont;
        } catch(Throwable e) {
            return null;
        }
    }

    //nos devuelve la posicion si existe toda la fila
    public int existeDato(String dato){
        File archivo = new File(this.nombre);
        Scanner entrada = null;
        String linea;
        int res = 0;
        try {
            entrada = new Scanner(archivo);
            while (entrada.hasNext()) {
                linea = entrada.nextLine();
                String [] data=linea.split(",");
                if (linea.equals(dato)) {
                    res = Integer.parseInt(data[0]);
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (entrada != null) {
                entrada.close();
            }
        }
        return res;
    }

}