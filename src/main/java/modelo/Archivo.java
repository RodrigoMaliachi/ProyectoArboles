package modelo;

import java.io.*;

public class Archivo {
    private String linea;
    private String partes[] = null;
    public void leerArchivoCSV(String nombreArchivo){

        try{
            BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
            while((linea = lector.readLine())!=null){
                partes = linea.split(",");
                imprimirLinea();
                System.out.println("prueba"+partes[1]+"\n");
            }
            lector.close();
            linea = null;
            partes = null;
        }catch (Exception e){

        }
    }

    public void imprimirLinea(){
        for (int i = 0; i<partes.length; i++){
            System.out.println(partes[i]+"  |  ");
        }
    }



}

