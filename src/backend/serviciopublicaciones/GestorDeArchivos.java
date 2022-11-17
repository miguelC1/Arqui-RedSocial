package backend.serviciopublicaciones;

import com.sun.source.tree.BreakTree;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;


public class GestorDeArchivoss {
    private String nombre;

    public GestorDeArchivoss(String nombre){
        this.nombre=nombre+".csv";
        crearArchivo(this.nombre);
    }
    private void crearArchivo(String nombre){
        File archivo = new File(nombre);
        try {
            //System.out.println("ya sta creado");
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
        String cont = readFile(nombre);
        //System.out.println(""+cont);
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

    public int existeNombre(String nombre){
        File archivo = new File(this.nombre);
        Scanner entrada = null;
        String linea;
        int res = 0;
        try {
            entrada = new Scanner(archivo);
            while (entrada.hasNext()) {
                linea = entrada.nextLine();
                String [] data=linea.split(",");
                if (data[1].equals(nombre)) {
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