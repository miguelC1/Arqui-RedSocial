package backend.servicioreacciones;


import java.io.*;
import java.nio.file.Files;


public class GestorDeArchivoReaccion {
    private String nombre;

    public GestorDeArchivoReaccion(String nombre){
        this.nombre=nombre+".csv";
        crearArchivo(this.nombre);
    }

    private void crearArchivo(String nombre){
        try {
            File file = new File(nombre);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void escribirDatosEnCSV(String contenido){
        try{
            PrintWriter escritor = new PrintWriter(new FileWriter(nombre,true));
            escritor.printf(contenido + "\n");
            escritor.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void escribirDeCerroEnCSV(String [] contenido){
        try{
            PrintWriter escritor = new PrintWriter(new FileWriter(nombre));
            for (String fila: contenido){
                escritor.printf(fila + "\n");
            }
            escritor.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public String[] leerDatosCSV(){
        String cont = readFile(nombre);
        String [] res=cont.split("\\n");
        if(res==null){
            res= new String[0];
        }
        if(res[0].equals("")){
            res= new String[0];
        }
        return res;
    }

    public static String readFile(String filePath) {
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
}